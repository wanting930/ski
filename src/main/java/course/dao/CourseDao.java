package course.dao;

import java.util.List;

import org.hibernate.Session;

import course.core.util.HibernateUtil;
import course.entity.Course;

public interface CourseDao {
	
	default Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	int insertCourse(Course course) ;
	
    Course getCourseByCourseId(Integer courseId) ;
    
    List<Course> getAllCourses();
    
    int updateByCourseId(Course course) ;
    
    int deleteByCourseId(Integer courseId) ;
}
