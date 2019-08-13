package jp.acepro.haishinsan.service.issue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.acepro.haishinsan.dao.IssueCustomDao;
import jp.acepro.haishinsan.dao.IssueDao;
import jp.acepro.haishinsan.db.entity.Issue;
import jp.acepro.haishinsan.db.entity.TwitterCampaignManage;
import jp.acepro.haishinsan.dto.IssuesDto;
import jp.acepro.haishinsan.entity.IssueWithShopWithCorporation;
import jp.acepro.haishinsan.enums.Flag;
import jp.acepro.haishinsan.service.BaseService;
import jp.acepro.haishinsan.util.ContextUtil;

@Service
public class IssuesServiceImpl extends BaseService implements IssuesService {

    @Autowired
    IssueDao issueDao;

    @Autowired
    IssueCustomDao issueCustomDao;

    // DB: 案件一覧を取得する
    @Override
    @Transactional
    public List<IssuesDto> searchIssuesList(IssuesDto issuesSearch) {

        List<IssuesDto> issuesDtoList = new ArrayList<IssuesDto>();
        // DB検索
        List<IssueWithShopWithCorporation> IssueList = issueCustomDao
                .selectIssueList(ContextUtil.getCurrentShop().getShopId(), issuesSearch);
        for (IssueWithShopWithCorporation issue : IssueList) {
            IssuesDto issuesDto = new IssuesDto();
            issuesDto.setShopName(issue.getShopName());
            issuesDto.setCorporationName(issue.getCorporationName());
            issuesDto.setIssueId(issue.getIssueId());
            issuesDto.setCampaignName(issue.getCampaignName());
            issuesDto.setBudget(issue.getBudget());
            issuesDto.setStartDate(issue.getStartDate());
            issuesDto.setEndDate(issue.getEndDate());
            // campaignIdの有無で媒体を判別
            // Google
            if (Objects.nonNull(issue.getGoogleCampaignManageId())) {
                issuesDto.setMedia("Google");
                issuesDto.setMediaIcon("label google");
            }
            // Facebook
            if (Objects.nonNull(issue.getFacebookCampaignManageId())) {
                issuesDto.setMedia("FaceBook");
                issuesDto.setMediaIcon("label faceBook");
            }
            // twitter
            if (Objects.nonNull(issue.getTwitterCampaignManageId())) {
                issuesDto.setMedia("Twitter");
                issuesDto.setMediaIcon("label twitter");
            }
            // dsp
            if (Objects.nonNull(issue.getDspCampaignManageId())) {
                issuesDto.setMedia("FreakOut");
                issuesDto.setMediaIcon("label dsp");
            }
            // yahoo
            if (Objects.nonNull(issue.getYahooCampaignManageId())) {
                issuesDto.setMedia("Yahoo");
                issuesDto.setMediaIcon("label yahoo");
            }
            // youtube
            if (Objects.nonNull(issue.getYoutubeCampaignManageId())) {
                issuesDto.setMedia("Youtube");
                issuesDto.setMediaIcon("label youtube");
            }
            // 配信開始日と配信終了日で配信状態を判別
            LocalDate today = LocalDate.now();
            LocalDate startDate = LocalDate.parse(issue.getStartDate());
            LocalDate endDate = LocalDate.parse(issue.getEndDate());
            // 配信待ち
            if (today.isBefore(startDate)) {
                issuesDto.setStatusIcon("label wait");
                issuesDto.setStatus("配信待ち");
            }
            // 配信済み
            if (today.isAfter(endDate)) {
                issuesDto.setStatusIcon("label stop");
                issuesDto.setStatus("配信済み");
            }
            // 配信中
            if ((today.isAfter(startDate) || today.isEqual(startDate))
                    && (today.isBefore(endDate) || today.isEqual(endDate))) {
                issuesDto.setStatusIcon("label live");
                issuesDto.setStatus("配信中");
            }
            issuesDtoList.add(issuesDto);
        }
        return issuesDtoList;
    }

    // DB: 案件を案件Idで論理削除する
    @Override
    @Transactional
    public void deleteIssueById(Long issueId) {

        Issue issue = issueDao.selectById(issueId);
        issue.setIsActived(Flag.OFF.getValue());
        // DB更新
        issueDao.update(issue);

    }

    // 案件IdでcampaignIdを検索
    @Override
    public String selectCampaignIdByIssueId(Long issueId) {
        TwitterCampaignManage twitterCampaignManage = issueCustomDao
                .selectCampaignIdByIssueId(ContextUtil.getCurrentShop().getShopId(), issueId);
        String campaignId = twitterCampaignManage.getCampaignId();
        return campaignId;
    }

}
