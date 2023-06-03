package ad.course.Dao;

import java.util.List;

import ad.course.vo.*;
import core.CoreDao;

public interface ourseAdHistoryDao extends CoreDao {

	int insert(ourseAdHistory courseAdHistory);

	int delete(Integer id);

	int update(ourseAdHistory nCourseAdHistory);

	ourseAdHistory selectById(Integer id);

	List<ourseAdHistory> selectAll();

}