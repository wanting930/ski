package ad.course.Dao.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.query.Query;

import ad.course.vo.courseAd;
import course.entity.Course;
import ad.course.Dao.courseAdDao;
//import core.CoreDao;
//import core.util.HibernateUtil;
//import ad.course.Dao.courseAdDao;



public class courseAdDaoImpl implements courseAdDao {
//	getSession() getSession() = HibernateUtil.getgetSession()Factory().getCurrentgetSession()();

	
	@Override
	public int insert(courseAd courseAd) {
		getSession().persist(courseAd);
		return -1;
	}

	@Override
	public Course getCourseByID(Integer courseID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Integer id) {
		courseAd cAd = getSession().get(courseAd.class, id);
		getSession().remove(cAd);
		return 1;
	}

	// ???
	@Override
	public int update(courseAd courseAd) {
		getSession().update(courseAd);
		return 1;
	}

	@Override
	public courseAd selectAd(Integer id) {
//		return getSession().get(courseAd.class, id);
		return getSession().get(courseAd.class, id);
	}

	@Override
	public List<courseAd> selectAll() {
		return getSession().createQuery("from courseAd", courseAd.class).list();
	}

	public List<Course> selectAllCourses() {
		return getSession().createQuery("from course", Course.class).list();
	}
	
	public List<Course> searchCourses(String keyword){
		String hql = "FROM Course WHERE "
				+ "courseName LIKE :keyword "
				+ "OR level LIKE :keyword "
				+ "OR skill LIKE :keyword";
		Query<Course> query = getSession().createQuery(hql,Course.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();

	}
	public List<Course> selectActiveCourse() {
		String hql = "FROM course WHERE startDate <= CURRENT_TIMESTAMP() "
				+ "AND d.endDate > CURRENT_TIMESTAMP()";
		Query<Course> query = getSession().createQuery(hql,Course.class);
		return query.getResultList();
	}
	
	
	
}
