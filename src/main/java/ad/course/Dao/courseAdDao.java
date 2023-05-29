package ad.course.Dao;

import java.util.List;

import ad.course.vo.courseAd;
import core.CoreDao;
import course.entity.Course;



public interface courseAdDao extends CoreDao {

	int insert(courseAd courseAd);

	int delete(Integer id);

	int update(courseAd ncourseAd);

	courseAd selectAd(Integer id);

	List<courseAd> selectAll();
	
	Course getCourseByID(Integer courseID);

	List<Course> selectAllCourses();
	List<Course> searchCourses(String keyword);
	List<Course> selectActiveCourse();
}