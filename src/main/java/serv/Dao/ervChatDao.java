package serv.Dao;

import java.util.List;

import core.CoreDao;
import serv.vo.ServChat;

public interface ervChatDao extends CoreDao{

	int insert(ServChat servChat);

	int delete(Integer id);

	int update(ServChat sChat);

	ServChat select(Integer id);

	List<ServChat> selectAll();

}