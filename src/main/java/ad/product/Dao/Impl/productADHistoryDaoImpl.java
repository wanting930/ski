package ad.product.Dao.Impl;

import java.util.List;


import ad.product.Dao.productAdHistoryDao;
import ad.product.vo.productADHistory;

public class productADHistoryDaoImpl implements productAdHistoryDao {
//	getSession() getSession() = HibernateUtil.getgetSession()Factory().getCurrentgetSession()();

	@Override
	public int insert(productADHistory productADHistory) {
		getSession().persist(productADHistory);
		return -1;
	}

	@Override
	public int delete(Integer id) {
		productADHistory pAdHistory = getSession().load(productADHistory.class, id);
		getSession().remove(pAdHistory);
		return 1;
	}

	@Override
	public int update(productADHistory productADHistory) {
		getSession().update(productADHistory);
		return 1;
	}

	@Override
	public productADHistory selectById(Integer id) {
		return getSession().get(productADHistory.class, id);
	}

	@Override
	public List<productADHistory> selectAll() {
		return getSession().createQuery("from productAdHistory", productADHistory.class).list();
	}

}
