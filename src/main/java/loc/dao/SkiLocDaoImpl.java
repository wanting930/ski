package loc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import loc.model.SkiLocation;

public class SkiLocDaoImpl implements SkiLocDao {

	//insert	
	@Override
	public int insert(SkiLocation skiLocation) {
		//Hibernate
		getSession().persist(skiLocation);
		return skiLocation.getPointID();
	}

	// delete
	@Override
	public int deleteByPointID(Integer pointID) {
		//Hibernate
//		Session session = getSession();
		SkiLocation skiLocation = getSession().get(SkiLocation.class, pointID);
		getSession().remove(skiLocation);
		return pointID;
	}
	
//	public int deleteAllByPointID(Integer pointID) {
//		Session session = getSession();
//		String hql = "DELETE FROM SkiLocation WHERE pointID = :pointID";
//		Query query = session.createQuery(hql);
//		query.setInteger("pointID", pointID);
//		return query.executeUpdate();
//	}

	// update
	@Override
	public int updateByPointID(SkiLocation skiLocation) {
		//Hibernate
		getSession().update(skiLocation);
		return skiLocation.getPointID();
	}

	// select
	@Override
	public SkiLocation selectByPointID(Integer pointID) {
		//Hibernate
		SkiLocation skiLocation = getSession().get(SkiLocation.class, pointID);
		return skiLocation;
	}

	// select all
	@Override
	public List<SkiLocation> selectAll() {
		//Hibernate
		return getSession().createQuery("FROM SkiLocation", SkiLocation.class).list();
	}
	
	

	//test
//	public static void main(String[] args) {
////		SkiLocDao dao = new SkiLocDaoImpl();
//////		insert
////		SkiLocation ski1 = new SkiLocation(1, "test", 2.0, 2.0, "test", "test", "test", 2, 2,
////				new byte[] { 0, 1, 2, 3 }, 2, "test");
////		dao.insert(ski1);
////		System.out.println();
//		
//		//Hibernate test
//		SessionFactory sf=HibernateUtil.getSessionFactory();
//		Session session=sf.getCurrentSession();
//		Transaction tr= session.beginTransaction();
//		SkiLocDaoImpl a = new SkiLocDaoImpl();
//		SkiLocation b = new SkiLocation(null, "test", 2.0, 2.0, "test", "test", "test", 2, 2,
//				new byte[] { 0, 1, 2, 3 }, 2, "test");
//		SkiLocation c = new SkiLocation(11, "test2", 2.1, 2.1, "test2", "test2", "test2", 2, 2,
//				new byte[] { 0, 1, 2, 3 }, 3, "test2");
////		a.insert(b);
////		a.deleteByPointID(9);
////		a.updateByPointID(c);
//		a.selectByPointID(11);
//		System.out.println(a.selectByPointID(11));
////		a.selectAll();
////		System.out.println(a.selectAll());
//		tr.commit();
//		
//		
//
//		
//		
//		
////		//select all
////		for (SkiLocVo skilocvo : dao.selectAll()){
////			System.out.println(skilocvo.getPointID());
////		}
//		
//		//select
////		System.out.println(dao.selectByPointID(1));
//		
//		//update
////		SkiLocVo ski2 = new SkiLocVo(1, "test1", 3.0, 3.0, "test1", "test1", "test1", 3, 3,
////				new byte[] { 4, 5, 6, 7 }, 3, "test1");
////		System.out.println(dao.updateByPointID(ski2));
//		
//		//delete
////		System.out.println(dao.deleteByPointID(1));
//
//	}


}
