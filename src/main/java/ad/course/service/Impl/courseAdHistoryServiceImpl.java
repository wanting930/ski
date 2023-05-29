package ad.course.service.Impl;

import java.util.List;

import ad.course.Dao.courseAdHistoryDao;
import ad.course.Dao.Impl.courseAdDaoImpl;
import ad.course.Dao.Impl.courseAdHistoryDaoImpl;
import ad.course.service.courseAdHistoryService;
import ad.course.vo.courseAdHistory;

public class courseAdHistoryServiceImpl implements courseAdHistoryService {

	private courseAdHistoryDao dao = new courseAdHistoryDaoImpl();
	
	@Override
	public courseAdHistory insert(Integer courseId) {
		courseAdHistory cAdHistory = new courseAdHistory();
		cAdHistory.setCourseID(courseId);
		return cAdHistory;
	}
}
