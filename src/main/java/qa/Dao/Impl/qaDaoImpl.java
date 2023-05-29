package qa.Dao.Impl;

import javax.persistence.PersistenceContext;


import java.util.*;

import antlr.collections.List;
import qa.vo.qa;
import qa.Dao.qaDao;

public class qaDaoImpl implements qaDao {

//	getSession() getSession() = HibernateUtil.getgetSession()Factory().getCurrentgetSession()();

	@Override
	public int insert(qa qa) {
		getSession().persist(qa);
		return -1;
	}

	@Override
	public int deleteById(Integer id) {
		qa qa = getSession().load(qa.class, id);
		getSession().remove(qa);
		return 1;
	}

	@Override
	public int updata(qa qa) {
		getSession().update(qa);
		return 0;
	}

	@Override
	public qa selectByID(Integer id) {
		return getSession().get(qa.class, id);
	}

	@Override
	public java.util.List<qa> selectAll() {
		return getSession().createQuery("from qa", qa.class).list();

	}
	
}
