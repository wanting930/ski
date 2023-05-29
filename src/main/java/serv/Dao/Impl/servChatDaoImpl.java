package serv.Dao.Impl;

import java.util.List;


import serv.vo.servChat;
import serv.Dao.servChatDao;

public class servChatDaoImpl implements servChatDao {
//	private getSession() getSession();
	
	
	@Override
	public int insert(servChat servChat) {
		getSession().persist(servChat);
		return 1;
	}

	@Override
	public int delete(Integer id) {
		servChat servChat = getSession().load(servChat.class, id);
		getSession().remove(servChat);
		return 1;
	}
	@Override
	public int update(servChat sChat) {
		servChat servChat = getSession().load(servChat.class, sChat.getServChatID());
		servChat.setManagerID(sChat.getManagerID());
		servChat.setMessage(sChat.getMessage());
		servChat.setSendTime(sChat.getSendTime());
		return 1;
	}
	@Override
	public servChat select(Integer id) {
		return getSession().get(servChat.class, id);
	}
	@Override
	public List<servChat> selectAll(){
		return getSession().createQuery("from servChat",servChat.class).list();
	}
}
