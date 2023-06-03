package ad.course.Dao;

import java.util.List;

//import ad.course.vo.CourseAd;
import ad.course.vo.ourseAd;
import core.CoreDao;
import course.entity.Course;



public interface ourseAdDao extends CoreDao {

	int insert(ourseAd courseAd);

	int delete(Integer id);

	int update(ourseAd ncourseAd);

	ourseAd selectAd(Integer id);

	List<ourseAd> selectAll();
	
	Course getCourseByID(Integer courseID);

	List<Course> selectAllCourses();
	List<Course> searchCourses(String keyword);
	List<Course> selectActiveCourse();
	Course getInfoByID(Integer courseID);
}