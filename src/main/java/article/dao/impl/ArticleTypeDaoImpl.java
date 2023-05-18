package article.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import article.core.HibernateUtil;
import article.core.coreDao;
import article.dao.ArticleImageDao;
import article.dao.ArticleTypeDao;
import article.vo.ArticleImage;
import article.vo.ArticleType;

public class ArticleTypeDaoImpl implements ArticleTypeDao, coreDao {
	
	@Override
	public int insert(ArticleType articleType) {
		//Hibernate
		getSession().persist(articleType);
		return articleType.getArticleTypeID();
	}
	@Override
	public int deleteByArticleTypeID(Integer articleTypeID) {
		//Hibernate
		ArticleType articleType = getSession().get(ArticleType.class, articleTypeID);
	    getSession().remove(articleType);
	    return articleTypeID;
	}
	
	@Override
	public int updateByArticleTypeID(ArticleType articleType) {
		//Hibernate
		getSession().update(articleType);
	    return articleType.getArticleTypeID();
	}
	
	@Override
	public ArticleType selectByArticleTypeID(Integer articleTypeID) {
		//Hibernate
		ArticleType articleType = getSession().get(ArticleType.class, articleTypeID);
	    return articleType;
	}
	
	@Override
	public List<ArticleType> selectAll() throws ClassNotFoundException {
		//Hibernate
		return getSession().createQuery("FROM ArticleType", ArticleType.class).list();
	}
	
	public static void main(String[] args) throws ClassNotFoundException {

		SessionFactory sf=HibernateUtil.getSessionFactory();
		Session session=sf.getCurrentSession();
		Transaction tr=session.getTransaction();
		ArticleTypeDao dao = new ArticleTypeDaoImpl();
		try {
			tr.begin();
			
			//新增
//			ArticleType articleType1 = new ArticleType(null, "揪團");
//			dao.insert(articleType1);
			
			//刪除ID
//			dao.deleteByArticleTypeID(4);
			
			//修改
//			ArticleType articleType2 = new ArticleType(2, "新手");
//			dao.updateByArticleTypeID(articleType2);
			
			//查詢ID
			System.out.println(dao.selectByArticleTypeID(2));
			
			//查詢全部
//			System.out.println(dao.selectAll());
			
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			// TODO: handle exception
		}
	}
	

}
