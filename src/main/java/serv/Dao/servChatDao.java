package serv.Dao;

import java.util.List;

import core.CoreDao;
import serv.vo.servChat;

public interface servChatDao extends CoreDao{

	int insert(servChat servChat);

	int delete(Integer id);

	int update(servChat sChat);

	servChat select(Integer id);

	List<servChat> selectAll();

}