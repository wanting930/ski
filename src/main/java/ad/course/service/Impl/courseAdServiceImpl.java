package ad.course.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.TypedQuery;

import ad.course.Dao.courseAdDao;
import ad.course.Dao.courseAdHistoryDao;
import ad.course.Dao.Impl.courseAdDaoImpl;
import ad.course.Dao.Impl.courseAdHistoryDaoImpl;
import ad.course.service.courseAdService;
import ad.course.vo.courseAd;
import ad.course.vo.courseAdHistory;
import course.entity.Course;

public class courseAdServiceImpl implements courseAdService{

	private courseAdDao dao = new courseAdDaoImpl(); 
	private courseAdHistoryDao hDao = new courseAdHistoryDaoImpl();
	
	//查詢全部廣告
	@Override
	public List<courseAd> selectAll() {
		return dao.selectAll();
	}
//	public courseAd selectAd(Integer id) {
//		return dao.selectAd(id);
//	}
	
	
	//新增
	public courseAd insertAd(Integer courseId) {
		courseAd cAd = new courseAd();
		cAd.setCourseID(courseId);
		dao.insert(cAd);
		return cAd;
	}
	
	
	//刪除
	public int deleteAd(Integer id) {
		
		courseAdHistory cAdHistory = new courseAdHistory();
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
