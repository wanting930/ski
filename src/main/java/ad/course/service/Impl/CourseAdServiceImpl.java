package ad.course.service.Impl;

import java.util.List;

import ad.course.Dao.CourseAdDao;
import ad.course.Dao.CourseAdHistoryDao;
import ad.course.Dao.Impl.CourseAdDaoImpl;
import ad.course.Dao.Impl.CourseAdHistoryDaoImpl;
import ad.course.service.CourseAdService;
import ad.course.vo.CourseAd;
import ad.course.vo.CourseAdHistory;
import course.entity.Course;

public class CourseAdServiceImpl implements CourseAdService{

	private CourseAdDao dao = new CourseAdDaoImpl(); 
	private CourseAdHistoryDao hDao = new CourseAdHistoryDaoImpl();
	
	//查詢全部廣告&課程時間
	@Override
	public List<CourseAd> selectAll() {
		List<CourseAd> cAd = dao.selectAll();
		return dao.selectAll();
	}
	
	
//	public courseAd selectAd(Integer id) {
//		return dao.selectAd(id);
//	}
	
	
	//新增
	public CourseAd insertAd(Integer courseId) {
		CourseAd cAd = new CourseAd();
		cAd.setCourseID(courseId);
		dao.insert(cAd);
		return cAd;
	}
	
	
	//刪除
	public int deleteAd(Integer id) {
		
		CourseAdHistory cAdHistory = new CourseAdHistory();
		cAdHistory.setCourseID(id);
		hDao.insert(cAdHistory);
		
		dao.delete(id);
		return -1;
	}
	
	
	//取全部課程
	public List<Course> getCourse() {
		return dao.selectActiveCourse();
	}
	
	//模糊查詢課程
	public List<Course> search(String keyword) {
		return dao.searchCourses(keyword);
	}
}
