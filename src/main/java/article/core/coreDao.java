package article.core;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface coreDao {
	
	default Session getSession() {
        // 在此處放置獲取 Session 的程式碼
		SessionFactory sf=HibernateUtil.getSessionFactory();
        Session session=sf.getCurrentSession();
        return session;
    }

}
