package qa.Dao.Impl;

import javax.persistence.PersistenceContext;


import java.util.*;

import antlr.collections.List;
import qa.vo.Qa;
import qa.Dao.QaDao;

public class aDaoImpl implements QaDao {

//	getSession() getSession() = HibernateUtil.getgetSession()Factory().getCurrentgetSession()();

	@Override
	public int insert(Qa qa) {
		getSession().persist(qa);
		return -1;
	}

	@Override
	public int deleteById(Integer id) {
		Qa qa = getSession().load(Qa.class, id);
		getSession().remove(qa);
		return 1;
	}

	@Override
	public int updata(Qa qa) {
		getSession().update(qa);
		return 0;
	}

	@Override
	public Qa selectByID(Integer id) {
		return getSession().get(Qa.class, id);
	}

	@Override
	public java.util.List<Qa> selectAll() {
		return getSession().createQuery("FROM Qa", Qa.class).list();

	}
	
}
