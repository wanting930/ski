package ad.course.Dao.Impl;

import java.util.List;

import ad.course.Dao.CourseAdHistoryDao;
import ad.course.vo.CourseAdHistory;

public class CourseAdHistoryDaoImpl implements CourseAdHistoryDao {

//	getSession() getSession() = HibernateUtil.getgetSession()Factory().getCurrentgetSession()();
	public int insert(CourseAdHistory courseAdHistory) {
		getSession().persist(courseAdHistory);
		return -1;
	}

	@Override
	public int delete(Integer id) {
		CourseAdHistory cAdHistory = getSession().load(CourseAdHistory.class, id);
		getSession().remove(cAdHistory);
		return 1;
	}

	@Override
	public int update(CourseAdHistory nCourseAdHistory) {
		CourseAdHistory courseAdHistory = getSession().load(CourseAdHistory.class,
				nCourseAdHistory.getCourseADHistoryID());
		courseAdHistory.setHistoryDateTime(nCourseAdHistory.getHistoryDateTime());
		return 1;
	}

	@Override
	public CourseAdHistory selectById(Integer id) {
		return getSession().get(CourseAdHistory.class, id);
	}

	@Override
	public List<CourseAdHistory> selectAll() {
		return getSession().createQuery("FROM CourseAdHistory", CourseAdHistory.class).list();
	}
}
