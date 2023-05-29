package ad.product.Dao;

import java.util.List;

import ad.product.vo.productADHistory;
import core.CoreDao;

public interface productAdHistoryDao extends CoreDao{

	int insert(productADHistory productADHistory);

	int delete(Integer id);

	int update(productADHistory productADHistory);

	productADHistory selectById(Integer id);

	List<productADHistory> selectAll();

}