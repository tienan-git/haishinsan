package jp.acepro.haishinsan.controller.campaign.google;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.io.ByteSource;

import jp.acepro.haishinsan.db.entity.GoogleCampaignManage;
import jp.acepro.haishinsan.db.entity.Issue;
import jp.acepro.haishinsan.dto.google.GoogleCampaignDto;
import jp.acepro.haishinsan.dto.google.GoogleIssueDto;
import jp.acepro.haishinsan.dto.google.GoogleTemplateDto;
import jp.acepro.haishinsan.enums.GoogleAdType;
import jp.acepro.haishinsan.enums.Operation;
import jp.acepro.haishinsan.form.GoogleIssueInputForm;
import jp.acepro.haishinsan.service.CodeMasterService;
import jp.acepro.haishinsan.service.CodeMasterServiceImpl;
import jp.acepro.haishinsan.service.OperationService;
import jp.acepro.haishinsan.service.dsp.DspSegmentService;
import jp.acepro.haishinsan.service.google.GoogleCampaignService;
import jp.acepro.haishinsan.service.google.GoogleTemplateService;
import jp.acepro.haishinsan.util.ContextUtil;
import jp.acepro.haishinsan.util.ImageUtil;

@Controller
@RequestMapping("/campaign/google")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GoogleIssueController {

	@Autowired
	CodeMasterService codeMasterService;

	@Autowired
	HttpSession session;

	@Autowired
	GoogleCampaignService googleCampaignService;

	@Autowired
	GoogleTemplateService googleTemplateService;

	@Autowired
	DspSegmentService dspSegmentService;

	@Autowired
	OperationService operationService;

	@Autowired
	ImageUtil imageUtil;

	@GetMapping("/adTypeSelect")
	public ModelAndView adTypeSelect(ModelAndView mv) {

		mv.setViewName("campaign/google/typeSelect");
		return mv;
	}

	@GetMapping("/bannerCampaignList")
	@PreAuthorize("hasAuthority('" + jp.acepro.haishinsan.constant.AuthConstant.GOOGLE_CAMPAIGN_VIEW + "')")
	public ModelAndView bannerCampaignList(@ModelAttribute GoogleIssueInputForm googleIssueInputForm) {

		session.setAttribute("adType", GoogleAdType.IMAGE);

		List<GoogleCampaignManage> googleCampaignManageList = googleCampaignService
				.searchGoogleCampaignManageList(GoogleAdType.IMAGE.getValue());

		List<GoogleCampaignDto> googleCampaignDtoList = googleCampaignService.campaignList(googleCampaignManageList);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("campaign/google/bannerCampaignList");
		mv.addObject("googleCampaignDtoList", googleCampaignDtoList);

		return mv;
	}

	@GetMapping("/respCampaignList")
	@PreAuthorize("hasAuthority('" + jp.acepro.haishinsan.constant.AuthConstant.GOOGLE_CAMPAIGN_VIEW + "')")
	public ModelAndView respCampaignList(@ModelAttribute GoogleIssueInputForm googleIssueInputForm) {

		session.setAttribute("adType", GoogleAdType.RESPONSIVE);

		List<GoogleCampaignManage> googleCampaignManageList = googleCampaignService
				.searchGoogleCampaignManageList(GoogleAdType.RESPONSIVE.getValue());

		List<GoogleCampaignDto> googleCampaignDtoList = googleCampaignService.campaignList(googleCampaignManageList);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("campaign/google/respCampaignList");
		mv.addObject("googleCampaignDtoList", googleCampaignDtoList);

		return mv;
	}

	@GetMapping("/textCampaignList")
	@PreAuthorize("hasAuthority('" + jp.acepro.haishinsan.constant.AuthConstant.GOOGLE_CAMPAIGN_VIEW + "')")
	public ModelAndView textCampaignList(@ModelAttribute GoogleIssueInputForm googleIssueInputForm) {

		session.setAttribute("adType", GoogleAdType.TEXT);

		List<GoogleCampaignManage> googleCampaignManageList = googleCampaignService
				.searchGoogleCampaignManageList(GoogleAdType.TEXT.getValue());

		List<GoogleCampaignDto> googleCampaignDtoList = googleCampaignService.campaignList(googleCampaignManageList);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("campaign/google/textCampaignList");
		mv.addObject("googleCampaignDtoList", googleCampaignDtoList);

		return mv;
	}

	@PostMapping("/createIssue")
	@PreAuthorize("hasAuthority('" + jp.acepro.haishinsan.constant.AuthConstant.GOOGLE_CAMPAIGN_MANAGE + "')")
	public ModelAndView createIssue(@Validated GoogleIssueInputForm googleIssueInputForm, BindingResult result) {

		if (googleIssueInputForm.getIdList() == null || googleIssueInputForm.getIdList().isEmpty()) {
			result.reject("E00020");
			GoogleAdType adType = (GoogleAdType) session.getAttribute("adType");
			switch (adType) {
			case IMAGE:
				return bannerCampaignList(googleIssueInputForm);
			case RESPONSIVE:
				return respCampaignList(googleIssueInputForm);
			case TEXT:
				return textCampaignList(googleIssueInputForm);
			default:
				return bannerCampaignList(googleIssueInputForm);
			}
		}
		if (googleIssueInputForm.getIdList().size() > 1) {
			result.reject("E00021");
			GoogleAdType adType = (GoogleAdType) session.getAttribute("adType");
			switch (adType) {
			case IMAGE:
				return bannerCampaignList(googleIssueInputForm);
			case RESPONSIVE:
				return respCampaignList(googleIssueInputForm);
			case TEXT:
				return textCampaignList(googleIssueInputForm);
			default:
				return bannerCampaignList(googleIssueInputForm);
			}
		}

		// コードマスタを読込
		getGoogleAreaList();

		// テンプレートを読込
		List<GoogleTemplateDto> googleTemplateDtoList = getGoogleTemplateList();

		// -------- 優先度一番高いテンプレートで初期値を設定 --------
		if (googleTemplateDtoList != null && googleTemplateDtoList.size() > 0) {
			GoogleTemplateDto googleTemplateDto = googleTemplateDtoList.get(0);
			googleIssueInputForm.setBudget(googleTemplateDto.getBudget());
			googleIssueInputForm.setCampaignName(googleTemplateDto.getCampaignName());
			List<Long> list = googleTemplateDto.getLocationList();
			if (list != null) {
				googleIssueInputForm.setLocationList(new ArrayList<Long>(list));
			}
//	        googleIssueInputForm.setResAdDescription( googleTemplateDto.getResAdDescription() );
//	        googleIssueInputForm.setResAdShortTitle( googleTemplateDto.getResAdShortTitle() );
//	        googleIssueInputForm.setTextAdDescription( googleTemplateDto.getTextAdDescription() );
//	        googleIssueInputForm.setTextAdTitle1( googleTemplateDto.getTextAdTitle1() );
//	        googleIssueInputForm.setTextAdTitle2( googleTemplateDto.getTextAdTitle2() );
//	        googleIssueInputForm.setUnitPriceType( googleTemplateDto.getUnitPriceType() );
		}

		session.setAttribute("campaignId", googleIssueInputForm.getIdList().get(0));
		ModelAndView mv = new ModelAndView();
		mv.setViewName("campaign/google/createIssue");
		return mv;

	}

	@PostMapping("/confirmIssue")
	@PreAuthorize("hasAuthority('" + jp.acepro.haishinsan.constant.AuthConstant.GOOGLE_CAMPAIGN_MANAGE + "')")
	public ModelAndView confirmIssue(@Validated GoogleIssueInputForm googleIssueInputForm, BindingResult result)
			throws IOException {

		String campaignId = (String) session.getAttribute("campaignId");
		GoogleIssueDto googleIssueDto = googleCampaignService.mapToIssue(googleIssueInputForm);
		googleIssueDto.setCampaignId(Long.valueOf(campaignId));

		session.setAttribute("googleIssueDto", googleIssueDto);
		ModelAndView mv = new ModelAndView("campaign/google/confirmIssue");
		mv.addObject("googleIssueDto", googleIssueDto);

		return mv;
	}

	@GetMapping("/completeIssue")
	@PreAuthorize("hasAuthority('" + jp.acepro.haishinsan.constant.AuthConstant.GOOGLE_CAMPAIGN_MANAGE + "')")
	public ModelAndView completeIssue() {

		GoogleIssueDto googleIssueDto = (GoogleIssueDto) session.getAttribute("googleIssueDto");

		Issue issue = googleCampaignService.createIssue(googleIssueDto);

		session.removeAttribute("googleIssueDto");
		session.removeAttribute("campaignId");
		session.removeAttribute("adType");
		ModelAndView mv = new ModelAndView("campaign/google/completeIssue");

		// オペレーションログ記録
		operationService.create(Operation.GOOGLE_ISSUE_CREATE.getValue(), String.valueOf(issue.getIssueId()));
		return mv;
	}

//	@GetMapping("/createCampaign")
//	@PreAuthorize("hasAuthority('" + jp.acepro.haishinsan.constant.AuthConstant.GOOGLE_CAMPAIGN_MANAGE + "')")
//	public ModelAndView createCampaign() {
//
//		// コードマスタを読込
//		getGoogleAreaList();
//
//		// テンプレートを読込
//		List<GoogleTemplateDto> googleTemplateDtoList = getGoogleTemplateList();
//
//		// ＤＳＰＵＲＬを読込
//		List<DspSegmentListDto> dspSegmentDtoList = dspSegmentService.segmentList();
//		// 入力ＦＯＲＭを初期化
//		GoogleCampaignForm googleCampaignForm = new GoogleCampaignForm();
//		// -------- 優先度一番高いテンプレートで初期値を設定 --------
//		googleCampaignForm = GoogleMapper.INSTANCE.map(googleTemplateDtoList.get(0));
//		// -------- 優先度一番高いテンプレートで初期値を設定 --------
//
//		// 正常時レスポンスを作成
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("google/createCampaign");
//		modelAndView.addObject("googleTemplateDtoList", googleTemplateDtoList);
//		modelAndView.addObject("googleCampaignForm", googleCampaignForm);
//		modelAndView.addObject("dspSegmentDtoList", dspSegmentDtoList);
//		return modelAndView;
//	}
//
//	@PostMapping("/createCampaignComplete")
//	@PreAuthorize("hasAuthority('" + jp.acepro.haishinsan.constant.AuthConstant.GOOGLE_CAMPAIGN_MANAGE + "')")
//	public ModelAndView createCampaignComplete(@Validated GoogleCampaignForm googleCampaignForm, BindingResult result) throws IOException {
//
//		// コードマスタを読込
//		getKeywordNameList();
//
//		// 正常時レスポンス表示用オブジェクトを初期化
//		List<String> resAdImageList = new ArrayList<String>();
//		List<String> imageAdImageList = new ArrayList<String>();
//		GoogleCampaignDto googleCampaignDto = new GoogleCampaignDto();
//
//		// キャンペーン作成を実施
//		try {
//			// キャンプーン作成用パラメタ設定（画像以外）
//			googleCampaignDto = GoogleMapper.INSTANCE.map(googleCampaignForm);
//			googleCampaignDto.setResAdImageBytesList(new ArrayList<byte[]>());
//			googleCampaignDto.setImageAdImageBytesList(new ArrayList<byte[]>());
//
//			// キャンプーン作成用パラメタ設定（画像）
//			switch (GoogleAdType.of(googleCampaignForm.getAdType())) {
//			case RESPONSIVE:
//				// 画像枚数チェック
//				if (googleCampaignForm.getResAdImageFileList().size() != 2) {
//					// レスポンシブ広告の画像を同時に２枚アップロードしてください。
//					throw new BusinessException(ErrorCodeConstant.E70005);
//				}
//				List<Integer> widthList = new ArrayList<Integer>();
//				List<Integer> heightList = new ArrayList<Integer>();
//				for (MultipartFile imageFile : googleCampaignForm.getResAdImageFileList()) {
//					String base64Str = imageUtil.getImageBytes(imageFile, 2);
//					StringBuffer data = new StringBuffer();
//					data.append("data:image/jpeg;base64,");
//					data.append(base64Str);
//					resAdImageList.add(data.toString());
//					googleCampaignDto.getResAdImageBytesList().add(getByteArrayFromStream(imageFile.getInputStream()));
//					// ファイルの幅、高さチェック取得
//					InputStream is = imageFile.getInputStream();
//					Image image = ImageIO.read(is);
//					widthList.add(image.getWidth(null));
//					heightList.add(image.getHeight(null));
//				}
//				// 画像サイズチェック
//				if (widthList.get(0).equals(widthList.get(1)) && heightList.get(0).equals(heightList.get(1))) {
//					// レスポンシブ広告の画像は、同時に同じサイズの画像をアップロードしないてください。
//					throw new BusinessException(ErrorCodeConstant.E70006);
//				}
//				break;
//			case IMAGE:
//				for (MultipartFile imageFile : googleCampaignForm.getImageAdImageFileList()) {
//					String base64Str = imageUtil.getImageBytes(imageFile, 3);
//					StringBuffer data = new StringBuffer();
//					data.append("data:image/jpeg;base64,");
//					data.append(base64Str);
//					imageAdImageList.add(data.toString());
//					googleCampaignDto.getImageAdImageBytesList().add(getByteArrayFromStream(imageFile.getInputStream()));
//				}
//				break;
//			case TEXT:
//				break;
//			}
//
//			// キャンプーン作成
//			googleCampaignService.createCampaign(googleCampaignDto, null);
//
//		} catch (BusinessException e) {
//			// 異常時レスポンスを作成
//			result.reject(e.getMessage(), e.getParams(), null);
//
//			// テンプレートを読込
//			List<GoogleTemplateDto> googleTemplateDtoList = getGoogleTemplateList();
//			// ＤＳＰＵＲＬを読込
//			List<DspSegmentListDto> dspSegmentDtoList = dspSegmentService.segmentList();
//			ModelAndView modelAndView = new ModelAndView();
//			modelAndView.setViewName("google/createCampaign");
//			modelAndView.addObject("googleTemplateDtoList", googleTemplateDtoList);
//			modelAndView.addObject("googleCampaignForm", googleCampaignForm);
//			modelAndView.addObject("dspSegmentDtoList", dspSegmentDtoList);
//			return modelAndView;
//		}
//
//		// 正常時レスポンスを作成
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("google/createCampaignComplete");
//		modelAndView.addObject("googleCampaignForm", googleCampaignForm);
//		modelAndView.addObject("resAdImageList", resAdImageList);
//		modelAndView.addObject("imageAdImageList", imageAdImageList);
//
//		// オペレーションログ記録
//		operationService.create(Operation.GOOGLE_CAMPAIGN_CREATE.getValue(), String.valueOf(googleCampaignDto.getCampaignId()));
//		return modelAndView;
//	}
//
//	@GetMapping("/listCampaign")
//	@PreAuthorize("hasAuthority('" + jp.acepro.haishinsan.constant.AuthConstant.GOOGLE_CAMPAIGN_VIEW + "')")
//	public ModelAndView listCampaign() {
//
//		// コードマスタをメモリへロード
//		getGoogleAreaList();
//
//		List<GoogleCampaignInfoDto> googleCampaignInfoDtoList = new ArrayList<GoogleCampaignInfoDto>();
//		googleCampaignInfoDtoList = googleCampaignService.getCampaignList();
//
//		// 正常時レスポンスを作成
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("google/listCampaign");
//		modelAndView.addObject("googleCampaignInfoDtoList", googleCampaignInfoDtoList);
//
//		// オペレーションログ記録
//		operationService.create(Operation.GOOGLE_CAMPAIGN_LIST.getValue(), String.valueOf(""));
//		return modelAndView;
//	}
//
//	@GetMapping("/detailCampaign")
//	@PreAuthorize("hasAuthority('" + jp.acepro.haishinsan.constant.AuthConstant.GOOGLE_CAMPAIGN_VIEW + "')")
//	public ModelAndView detailCampaign(@RequestParam Long campaignId) {
//
//		// コードマスタをメモリへロード
//		getGoogleAreaList();
//
//		// キャンプーン取得
//		GoogleCampaignDetailDto googleCampaignDetailDto = new GoogleCampaignDetailDto();
//		googleCampaignDetailDto = googleCampaignService.getCampaign(campaignId);
//
//		// 正常時レスポンスを作成
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("google/detailCampaign");
//		modelAndView.addObject("googleCampaignDetailDto", googleCampaignDetailDto);
//
//		// オペレーションログ記録
//		operationService.create(Operation.GOOGLE_CAMPAIGN_VIEW.getValue(), String.valueOf(campaignId));
//		return modelAndView;
//	}
//
//	@PostMapping("/deleteCampaign")
//	@PreAuthorize("hasAuthority('" + jp.acepro.haishinsan.constant.AuthConstant.GOOGLE_CAMPAIGN_MANAGE + "')")
//	public ModelAndView deleteCampaign(@RequestParam Long campaignId) {
//
//		// コードマスタをメモリへロード
//		getGoogleAreaList();
//
//		// キャンプーン削除
//		googleCampaignService.deleteCampaign(campaignId);
//
//		// キャンプーン一覧取得
//		List<GoogleCampaignInfoDto> googleCampaignInfoDtoList = new ArrayList<GoogleCampaignInfoDto>();
//		googleCampaignInfoDtoList = googleCampaignService.getCampaignList();
//
//		// 正常時レスポンスを作成
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("google/listCampaign");
//		modelAndView.addObject("googleCampaignInfoDtoList", googleCampaignInfoDtoList);
//
//		// オペレーションログ記録
//		operationService.create(Operation.GOOGLE_CAMPAIGN_DELETE.getValue(), String.valueOf(campaignId));
//		return modelAndView;
//	}

	private void getGoogleAreaList() {
		if (CodeMasterServiceImpl.googleAreaNameList == null) {
			codeMasterService.getGoogleAreaList();
		}
	}

	private List<GoogleTemplateDto> getGoogleTemplateList() {
		List<GoogleTemplateDto> googleTemplateDtoList = googleTemplateService
				.getTemplateList(ContextUtil.getCurrentShop().getShopId());
		return googleTemplateDtoList;
	}

	private byte[] getByteArrayFromStream(final InputStream inputStream) throws IOException {
		return new ByteSource() {
			@Override
			public InputStream openStream() {
				return inputStream;
			}
		}.read();
	}

	private void getKeywordNameList() {
		if (CodeMasterServiceImpl.keywordNameList == null) {
			codeMasterService.getKeywordNameList();
		}
	}
}
