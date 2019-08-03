package jp.acepro.haishinsan.controller.campaign.facebook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jp.acepro.haishinsan.constant.ErrorCodeConstant;
import jp.acepro.haishinsan.db.entity.FacebookCampaignManage;
import jp.acepro.haishinsan.dto.CreativeDto;
import jp.acepro.haishinsan.dto.NonTwitterAdDto;
import jp.acepro.haishinsan.dto.TwitterAdDto;
import jp.acepro.haishinsan.dto.dsp.DspCreativeDto;
import jp.acepro.haishinsan.dto.dsp.DspSegmentListDto;
import jp.acepro.haishinsan.dto.facebook.FbCampaignDto;
import jp.acepro.haishinsan.dto.facebook.FbCreativeDto;
import jp.acepro.haishinsan.dto.facebook.FbTemplateDto;
import jp.acepro.haishinsan.dto.twitter.TwitterAdsDto;
import jp.acepro.haishinsan.dto.twitter.TwitterTweet;
import jp.acepro.haishinsan.enums.CreativeType;
import jp.acepro.haishinsan.enums.FacebookArrangePlace;
import jp.acepro.haishinsan.enums.GoogleAdType;
import jp.acepro.haishinsan.enums.MediaType;
import jp.acepro.haishinsan.enums.Operation;
import jp.acepro.haishinsan.exception.BusinessException;
import jp.acepro.haishinsan.form.CreativeInputForm;
import jp.acepro.haishinsan.form.DspCampaignCreInputForm;
import jp.acepro.haishinsan.form.FbCampaignInputForm;
import jp.acepro.haishinsan.form.FbCreativeInputForm;
import jp.acepro.haishinsan.mapper.CreativeMapper;
import jp.acepro.haishinsan.mapper.FacebookMapper;
import jp.acepro.haishinsan.mapper.CreativeMapper;
import jp.acepro.haishinsan.service.CodeMasterService;
import jp.acepro.haishinsan.service.CodeMasterServiceImpl;
import jp.acepro.haishinsan.service.OperationService;
import jp.acepro.haishinsan.service.dsp.DspApiService;
import jp.acepro.haishinsan.service.dsp.DspCampaignService;
import jp.acepro.haishinsan.service.dsp.DspCreativeService;
import jp.acepro.haishinsan.service.dsp.DspSegmentService;
import jp.acepro.haishinsan.service.facebook.FacebookService;
import jp.acepro.haishinsan.service.google.GoogleCampaignService;
import jp.acepro.haishinsan.service.twitter.TwitterApiService;
import jp.acepro.haishinsan.util.ContextUtil;
import jp.acepro.haishinsan.util.ImageUtil;


@Controller
@RequestMapping("/facebook")
public class FacebookIssueController {

	@Autowired
	HttpSession session;

	@Autowired
	ImageUtil imageUtil;

	@Autowired
	CodeMasterService codeMasterService;

	@Autowired
	FacebookService facebookService;

	@Autowired
	DspSegmentService dspSegmentService;


	@Autowired
	OperationService operationService;

	@Autowired
	MessageSource msg;

	@GetMapping("/campaignList")
	@PreAuthorize("hasAuthority('" + jp.acepro.haishinsan.constant.AuthConstant.FACEBOOK_CAMPAIGN_VIEW + "')")
	public ModelAndView campaignList() {

		List<FacebookCampaignManage> facebookCampaignManageList = facebookService.searchFacebookCampaignManageList();

		List<FbCampaignDto> fbCampaignDtoList = facebookService.campaignList(facebookCampaignManageList);
		// ログインユーザーのロールIDを取得
		Integer roleId = ContextUtil.getRoleId();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("campaign/facebook/campaignList");
		mv.addObject("fbCampaignDtoList", fbCampaignDtoList);
		mv.addObject("roleId", roleId);

		// オペレーションログ記録
		operationService.create(Operation.FACEBOOK_CAMPAIGN_LIST.getValue(), String.valueOf(""));
		return mv;
	}
}
