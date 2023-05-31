package ad.course.service;

import java.util.List;

import ad.course.Dao.Impl.CourseAdDaoImpl;
import ad.course.vo.CourseAd;
import course.entity.Course;

public interface CourseAdService {
	
		public List<CourseAd> selectAll();
//		public courseAd selectAd(Integer id);
		public CourseAd insertAd(Integer courseId);
		public int deleteAd(Integer id);
		public List<Course> getCourse();
		public List<Course> search(String keyword);
		}
