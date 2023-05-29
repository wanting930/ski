package qa.Dao;

import antlr.collections.List;
import core.CoreDao;
import qa.vo.qa;

public interface qaDao extends CoreDao{

	int insert(qa qa);

	int deleteById(Integer id);

	int updata(qa qa);

	qa selectByID(Integer id);

	java.util.List<qa> selectAll();

}