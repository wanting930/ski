package ad.course.service.Impl;

import java.util.List;

import ad.course.Dao.CourseAdHistoryDao;
import ad.course.Dao.Impl.CourseAdDaoImpl;
import ad.course.Dao.Impl.CourseAdHistoryDaoImpl;
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
