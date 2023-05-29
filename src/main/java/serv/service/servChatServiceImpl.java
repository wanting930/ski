package serv.service;

import serv.Dao.servChatDao;
import serv.Dao.Impl.servChatDaoImpl;

public class servChatServiceImpl {
	private servChatDao dao;

	public servChatServiceImpl() {
			dao = new servChatDaoImpl();
	}
	
	
}
