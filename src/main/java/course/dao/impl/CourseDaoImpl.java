package course.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import course.dao.CourseDao;
import course.entity.Course;



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
	        public Course updateByCourseId(Course course) {
	    		getSession().merge(course);
	    		return course;
	    	}
	           
	        @Override
	        public int deleteByCourseId(Integer courseId) {
	        	Course course = getSession().get(Course.class, courseId);
	    		try {
	    			getSession().remove(course);
	    			return 1;
				} catch (Exception e) {				
					e.printStackTrace();
			        System.out.println("deleteByCourseId：" + e.getMessage());
			        return 0;
				}        	
	        }
	        
	    }

			
	    


