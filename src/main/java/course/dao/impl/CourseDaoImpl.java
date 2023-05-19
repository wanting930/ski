package course.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import course.dao.CourseDao;
import course.entity.Course;
import product.vo.Product;


	    public class CourseDaoImpl implements CourseDao {
	    	
	        @Override
	    	public int insertCourse(Course course) {
	        	getSession().persist(course);
	        	return 1;
	    	}
	              
	        @Override
	    	public Course getCourseByCourseId(Integer courseId) {
	        	return getSession().get(Course.class, courseId);
	    	}
	        
	        @Override
	    	public List<Course> getAllCourses() {
		        	String hql = "FROM Course";
		        	Query<Course> query = getSession().createQuery(hql, Course.class);
		        	List<Course> resultList = query.getResultList();
		        	
		        	return resultList; 

	    	}
	        
	    	@Override
	        public int updateByCourseId(Course course) {
	    		return 0;
	    	}
	           
	        @Override
	        public int deleteByCourseId(Integer courseId) {
	        	Course course = getSession().get(Course.class, courseId);
	    		getSession().remove(course);
	    		return 1;
	        }
	        
	    }

			
	    


