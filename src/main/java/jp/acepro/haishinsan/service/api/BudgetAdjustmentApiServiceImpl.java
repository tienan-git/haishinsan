package jp.acepro.haishinsan.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.acepro.haishinsan.ApplicationProperties;
import jp.acepro.haishinsan.dao.GoogleCampaignManageCustomDao;
import jp.acepro.haishinsan.dao.IssueCustomDao;
import jp.acepro.haishinsan.dao.IssueDao;
import jp.acepro.haishinsan.dao.ShopCustomDao;
import jp.acepro.haishinsan.service.CodeMasterService;
import jp.acepro.haishinsan.service.OperationService;
import jp.acepro.haishinsan.service.dsp.DspApiService;
import jp.acepro.haishinsan.service.dsp.DspSegmentService;
import jp.acepro.haishinsan.service.facebook.FacebookService;
import jp.acepro.haishinsan.service.google.GoogleReportService;
import jp.acepro.haishinsan.service.issue.FacebookReportingService;
import jp.acepro.haishinsan.service.issue.TwitterReportingService;
import jp.acepro.haishinsan.service.youtube.YoutubeReportService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BudgetAdjustmentApiServiceImpl implements BudgetAdjustmentApiService {

    @Autowired
    OperationService operationService;

    @Autowired
    CodeMasterService codeMasterService;

    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    DspApiService dspApiService;

    @Autowired
    GoogleReportService googleReportService;

    @Autowired
    FacebookService facebookService;

    @Autowired
    FacebookReportingService facebookReportingService;

    @Autowired
    TwitterReportingService twitterReportingService;

    @Autowired
    YoutubeReportService youtubeReportService;

    @Autowired
    DspSegmentService dspSegmentService;

    @Autowired
    IssueDao issueDao;

    @Autowired
    IssueCustomDao issueCustomDao;

    @Autowired
    ShopCustomDao shopCustomDao;

    @Autowired
    GoogleCampaignManageCustomDao googleCampaignManageCustomDao;

    @Async
    @Override
    @Transactional
    public void executeAsync() {

        // DSP

        // Twitter
        twitterReportingService.changeBudget();

        // Facebook

        // Google

    }

}
