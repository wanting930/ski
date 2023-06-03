package ad.course.Dao.Impl;

import java.util.List;

import ad.course.vo.*;
import ad.course.Dao.*;

public class ourseAdHistoryDaoImpl implements ourseAdHistoryDao {
	
//	getSession() getSession() = HibernateUtil.getgetSession()Factory().getCurrentgetSession()();
	public int insert(ourseAdHistory courseAdHistory) {
		getSession().persist(courseAdHistory);
		return -1;
	}
	@Override
	public int delete(Integer id) {
		ourseAdHistory cAdHistory = getSession().load(CourseAdHistory.class, id);
		getSession().remove(cAdHistory);
		return 1;
	}
	@Override
	public int update(ourseAdHistory nCourseAdHistory) {
		CourseAdHistory courseAdHistory = getSession().load(CourseAdHistory.class,nCourseAdHistory.getCourseADHistoryID());
		courseAdHistory.setHistoryDateTime(nCourseAdHistory.getHistoryDateTime());
		return 1;
	}
	@Override
	public ourseAdHistory selectById(Integer id) {
		return getSession().get(CourseAdHistory.class,id);
	}
	@Override
	public List<ourseAdHistory> selectAll() {
		return getSession().createQuery("FROM CourseAdHistory",CourseAdHistory.class).list();
	}
}
