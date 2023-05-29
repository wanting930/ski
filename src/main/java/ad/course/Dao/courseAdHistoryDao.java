package ad.course.Dao;

import java.util.List;

import ad.course.vo.*;
import core.CoreDao;

public interface courseAdHistoryDao extends CoreDao {

	int insert(courseAdHistory courseAdHistory);

	int delete(Integer id);

	int update(courseAdHistory nCourseAdHistory);

	courseAdHistory selectById(Integer id);

	List<courseAdHistory> selectAll();

}