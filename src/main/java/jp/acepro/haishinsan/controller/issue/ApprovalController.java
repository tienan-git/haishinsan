package jp.acepro.haishinsan.controller.issue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jp.acepro.haishinsan.dto.dsp.DspCampaignDto;
import jp.acepro.haishinsan.dto.facebook.FbSwitchRes;
import jp.acepro.haishinsan.dto.google.GoogleSwitchRes;
import jp.acepro.haishinsan.dto.twitter.TwitterSwitchRes;
import jp.acepro.haishinsan.enums.Operation;
import jp.acepro.haishinsan.service.OperationService;
import jp.acepro.haishinsan.service.dsp.DspCampaignService;
import jp.acepro.haishinsan.service.facebook.FacebookService;
import jp.acepro.haishinsan.service.google.GoogleCampaignService;
import jp.acepro.haishinsan.service.issue.IssuesService;
import jp.acepro.haishinsan.service.twitter.TwitterCampaignApiService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/issue/approval")
public class ApprovalController {

    @Autowired
    IssuesService issueService;

    @Autowired
    TwitterCampaignApiService twitterCampaignApiService;

    @GetMapping("/twitter/{campaignId}/{switchFlag}")
    public TwitterSwitchRes switchCampaignStatus(@PathVariable String campaignId, @PathVariable String switchFlag) {

        // キャンペーンステータスを更新
        twitterCampaignApiService.changeAdsStatus(campaignId, switchFlag);

        // 正常時レスポンスを作成
        TwitterSwitchRes res = new TwitterSwitchRes();
        res.setCode("0000");
        res.setMessage("キャンペーンのステータスが更新されました！");
        return res;
    }

    @Autowired
    DspCampaignService dspCampaignService;

    @Autowired
    OperationService operationService;

    @GetMapping("/dsp/{campaignId}/{switchFlag}")
    @PreAuthorize("hasAuthority('" + jp.acepro.haishinsan.constant.AuthConstant.CAMPAIGN_APPROVAL + "')")
    public ModelAndView Creative(@PathVariable Integer campaignId, @PathVariable String switchFlag) {

        dspCampaignService.updateCampaign(campaignId, switchFlag);

        List<DspCampaignDto> dspCampaignDtoList = dspCampaignService.getCampaignList();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dsp/campaignList");
        modelAndView.addObject("dspCampaignDtoList", dspCampaignDtoList);

        // オペレーションログ記録
        operationService.create(Operation.DSP_CAMPAIGN_UPDATE.getValue(), String.valueOf(campaignId));

        return modelAndView;

    }

    @Autowired
    GoogleCampaignService googleCampaignService;

    @GetMapping("/google/{issueId}/{switchFlag}")
    public GoogleSwitchRes switchGoogleIssueStatus(@PathVariable String issueId, @PathVariable String switchFlag) {

        // 案件ステータスを更新
    	googleCampaignService.updateIssueCheckStatus(Long.valueOf(issueId), switchFlag);

        // 正常時レスポンスを作成
        GoogleSwitchRes res = new GoogleSwitchRes();
        res.setCode("0000");
        res.setMessage("Google案件のステータスが更新されました！");

        return res;
    }
    
    @Autowired
    FacebookService facebookService;

    @GetMapping("/facebook/{issueId}/{switchFlag}")
    public FbSwitchRes switchFacebookIssueStatus(@PathVariable String issueId, @PathVariable String switchFlag) {

        // 案件ステータスを更新
    	facebookService.updateIssueCheckStatus(Long.valueOf(issueId), switchFlag);

        // 正常時レスポンスを作成
    	FbSwitchRes res = new FbSwitchRes();
        res.setCode("0000");
        res.setMessage("Facebook案件のステータスが更新されました！");

        return res;
    }
    
    @GetMapping("/instagram/{issueId}/{switchFlag}")
    public FbSwitchRes switchInstagramIssueStatus(@PathVariable String issueId, @PathVariable String switchFlag) {

        // 案件ステータスを更新
    	facebookService.updateIssueCheckStatus(Long.valueOf(issueId), switchFlag);

        // 正常時レスポンスを作成
    	FbSwitchRes res = new FbSwitchRes();
        res.setCode("0000");
        res.setMessage("Instagram案件のステータスが更新されました！");

        return res;
    }
   
}
