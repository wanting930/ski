package course.dao;

import java.util.List;

import org.hibernate.Session;

import core.CoreDao;
import course.core.util.HibernateUtil;
import course.entity.Course;

public interface CourseDao extends CoreDao{
//	default Session getSession() {
//		return HibernateUtil.getSessionFactory().getCurrentSession();
//	}
	
	//事後可能與update透過 merge()做整合
	int insertCourse(Course course) ;
	
    Course getCourseByCourseId(Integer courseId) ;
    
    List<Course> getAllCourses();
    
    Course updateByCourseId(Course course) ;
    
    int deleteByCourseId(Integer courseId) ;
}
