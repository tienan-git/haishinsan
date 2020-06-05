package jp.acepro.haishinsan.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import jp.acepro.haishinsan.db.annotation.InjectConfig;
import jp.acepro.haishinsan.db.entity.CampaignKeyword;

@Dao
@InjectConfig
public interface CampaignKeywordCustomDao {

	@Select
	List<CampaignKeyword> selectAll();
}
