package ad.course.service.Impl;

import ad.course.dao.CourseAdHistoryDao;
import ad.course.dao.impl.CourseAdHistoryDaoImpl;
import ad.course.service.CourseAdHistoryService;
import ad.course.vo.CourseAdHistory;

public class CourseAdHistoryServiceImpl implements CourseAdHistoryService {

	private CourseAdHistoryDao dao = new CourseAdHistoryDaoImpl();

	@Override
	public CourseAdHistory insert(Integer courseId) {
		CourseAdHistory cAdHistory = new CourseAdHistory();
		cAdHistory.setCourseID(courseId);
		return cAdHistory;
	}
}
