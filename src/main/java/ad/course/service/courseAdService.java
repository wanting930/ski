package ad.course.service;

import java.util.List;

import ad.course.Dao.Impl.courseAdDaoImpl;
import ad.course.vo.courseAd;
import course.entity.Course;

public interface courseAdService {
	
		public List<courseAd> selectAll();
//		public courseAd selectAd(Integer id);
		public courseAd insertAd(Integer courseId);
		public int deleteAd(Integer id);
		public List<Course> getCourse();
		public List<Course> search(String keyword);
		}
