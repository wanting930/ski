package ad.course.service.Impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ad.course.Daos.*;
import ad.course.Daos.impl.CourseAdDaoImpl;
import ad.course.Daos.impl.CourseAdHistoryDaoImpl;
import ad.course.service.CourseAdService;
import ad.course.vo.CourseAd;
import ad.course.vo.CourseAdHistory;
import ad.course.vo.CourseDTO;
import course.dao.CourseDao;
import course.dao.impl.CourseDaoImpl;
import course.entity.Course;

public class CourseAdServiceImpl implements CourseAdService {

	private CourseAdDao dao = new CourseAdDaoImpl();
	private CourseDao courseDao = new CourseDaoImpl();
	private CourseAdHistoryDao hDao = new CourseAdHistoryDaoImpl();

	// 查詢全部廣告&課程時間
	public List<CourseDTO> courseAdlist() {
		List<CourseAd> ads = dao.selectAll();
		List<CourseDTO> courseDTOs = new ArrayList<>();
		for (CourseAd ad : ads) {
			int courseID = ad.getCourseID();
			Course course = courseDao.getCourseByCourseId(courseID); // 假設透過 courseID 查詢相關 Course 資訊的方法為
																		// getCourseByCourseID()

			CourseDTO cDTO = new CourseDTO();
			cDTO.setCourseAD(ad.getCourseAD());
			cDTO.setCourseID(courseID);
			cDTO.setCourseName(course.getCourseName());
			cDTO.setStartDate(course.getStartDate());
			cDTO.setEndDate(course.getEndDate());

			courseDTOs.add(cDTO);
		}
		return courseDTOs;
	}

//	public courseAd selectAd(Integer id) {
//		return dao.selectAd(id);
//	}

	// 新增
	public CourseAd insertAd(Integer courseId) {
		CourseAd cAd = new CourseAd();
		cAd.setCourseID(courseId);
		dao.insert(cAd);
		return cAd;
	}

	// 刪除
	public int deleteAd(Integer id) {

		CourseAdHistory cAdHistory = new CourseAdHistory();
		cAdHistory.setCourseID(id);
		cAdHistory.setHistoryDateTime(new Timestamp(System.currentTimeMillis()));
		hDao.insert(cAdHistory);

		dao.delete(id);
		return -1;
	}

	// 取全部課程
	public List<Course> getCourse() {
		return dao.selectActiveCourse();
	}

	// 模糊查詢課程
	public List<Course> search(String keyword) {
		return dao.searchCourses(keyword);
	}
}
