package ad.course.Dao.Impl;

import java.util.List;

import ad.course.vo.*;
import ad.course.Dao.*;

public class courseAdHistoryDaoImpl implements courseAdHistoryDao {
	
//	getSession() getSession() = HibernateUtil.getgetSession()Factory().getCurrentgetSession()();
	
	@Override
	public int insert(courseAdHistory courseAdHistory) {
		getSession().persist(courseAdHistory);
		return -1;
	}
	@Override
	public int delete(Integer id) {
		courseAdHistory cAdHistory = getSession().load(courseAdHistory.class, id);
		getSession().remove(cAdHistory);
		return 1;
	}
	@Override
	public int update(courseAdHistory nCourseAdHistory) {
		courseAdHistory courseAdHistory = getSession().load(courseAdHistory.class,nCourseAdHistory.getCourseADHistoryID());
		courseAdHistory.setHistoryDateTime(nCourseAdHistory.getHistoryDateTime());
		return 1;
	}
	@Override
	public courseAdHistory selectById(Integer id) {
		return getSession().get(courseAdHistory.class,id);
	}
	@Override
	public List<courseAdHistory> selectAll() {
		return getSession().createQuery("from courseAdHistory",courseAdHistory.class).list();
	}
}
