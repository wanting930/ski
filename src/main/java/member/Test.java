package member;

import static core.HibernateUtil.getSessionFactory;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import member.vo.Coach;
import member.vo.Member;

public class Test {

	public static void main(String[] args) {
		Test test = new Test();
		// 註冊
		Member member1 = new Member("123@gail.com", "password", "John Doe", "male", Timestamp.valueOf("2023-04-30 00:00:00"), "primary", "user", "active");
		// 修改會員資料
		Member member2 = new Member();
		member2.setUserID(19);
		member2.setPassword("1qaz2wsx");
		
		Coach coach1 = new Coach(19, "Java", "Computer Science", "Hi", new byte[] { 1, 2, 3, 4 });
//		Coach coach2 = new Coach("C++", "Computer Science", "Hi", new byte[] { 1, 2, 3, 4 }, member2);
//		Coach coach3 = new Coach(19, "Java", "Computer Science", "Hi", new byte[] { 1, 2, 3, 4 }, member1);
		Coach coach4 = new Coach();
		coach4.setCoachID(19);

//		System.out.println(test.insert1(member1));
//		System.out.println(test.deleteById1(18));
//		System.out.println(test.updateById1(member2));
//		System.out.println(test.selectById1(18));
//		System.out.println(test.selectAll1());

//		System.out.println(test.insert2(coach1));
//		System.out.println(test.deleteById2(5));
//		System.out.println(test.updateById2(coach4));
//		System.out.println(test.selectById2(3));
//		System.out.println(test.selectAll2());		
	}

	public Integer insert1(Member member) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			Transaction tx = session.beginTransaction();
			session.persist(member);
			tx.commit();
			return member.getUserID();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("insert方法發生錯誤：" + e.getMessage());
			return -1;
		}
	}

	public int deleteById1(Integer userID) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			Transaction tx = session.beginTransaction();
			Member member = session.get(Member.class, userID);
			session.remove(member);
			tx.commit();
			return 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("deleteById方法發生錯誤：" + e.getMessage());
		}
		return -1;
	}

	// 透過ID更新該筆資料
	public boolean updateById1(Member nMember) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			Transaction tx = session.beginTransaction();
			Member oMember = session.get(Member.class, nMember.getUserID());

			final String passowrd = nMember.getPassword();
			if (passowrd != null && !passowrd.isEmpty()) {
				oMember.setPassword(passowrd);
			}
			final String userName = nMember.getUserName();
			if (userName != null && !userName.isEmpty()) {
				oMember.setUserName(userName);
			}
			final String nickName = nMember.getNickName();
			if (nickName != null && !nickName.isEmpty()) {
				oMember.setNickName(nickName);
			}
			final String phone = nMember.getPhone();
			if (phone != null && !phone.isEmpty()) {
				oMember.setPhone(phone);
			}
			final String address = nMember.getAddress();
			if (address != null && !address.isEmpty()) {
				oMember.setAddress(address);
			}
			final String level = nMember.getLevel();
			if (level != null && !level.isEmpty()) {
				oMember.setLevel(level);
			}
			final byte[] photo = nMember.getPhoto();
			if (photo != null && photo.length > 0) {
				oMember.setPhoto(photo);
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("updateById方法發生錯誤：" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public Member selectById1(Integer userID) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			Transaction tx = session.beginTransaction();
			Member member = session.get(Member.class, userID);
			tx.commit();
			return member;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("selectById方法發生錯誤：" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public List<Member> selectAll1() {
		final String hql = "FROM Member ORDER BY userID";
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			Transaction tx = session.beginTransaction();
			Query<Member> query = session.createQuery(hql, Member.class); // import org.hibernate.query.Query;
			List<Member> list = query.getResultList();
			tx.commit();
			return list;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("selectAll方法發生錯誤：" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public int insert2(Coach coach) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			Transaction tx = session.beginTransaction();
			session.persist(coach);
			tx.commit();
			return coach.getCoachID();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("insert方法發生錯誤：" + e.getMessage());
		}
		return -1;
	}

	public int deleteById2(Integer coachID) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			Transaction tx = session.beginTransaction();
			Coach coach = session.get(Coach.class, coachID);
			session.remove(coach);
			tx.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("deleteById方法發生錯誤：" + e.getMessage());
		}
		return -1;
	}

	// 透過ID更新該筆資料
	public boolean updateById2(Coach nCoach) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			Transaction tx = session.beginTransaction();
			Coach oCoach = session.get(Coach.class, nCoach.getCoachID());

			final String skill = nCoach.getSkill();
			if (skill != null && !skill.isEmpty()) {
				oCoach.setSkill(skill);
			}
			final String background = nCoach.getBackground();
			if (background != null && !background.isEmpty()) {
				oCoach.setBackground(background);
			}
			final String introduce = nCoach.getIntroduce();
			if (introduce != null && !introduce.isEmpty()) {
				oCoach.setIntroduce(introduce);
			}
			final byte[] license = nCoach.getLicense();
			if (license != null && license.length > 0) {
				oCoach.setLicense(license);
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("updateById方法發生錯誤：" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public Coach selectById2(Integer coachID) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			Transaction tx = session.beginTransaction();
			Coach coach = session.get(Coach.class, coachID);
			tx.commit();
			return coach;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("selectById方法發生錯誤：" + e.getMessage());
		}
		return null;
	}

	public List<Coach> selectAll2() {
		final String hql = "FROM Coach ORDER BY coachID";
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			Transaction tx = session.beginTransaction();
			Query<Coach> query = session.createQuery(hql, Coach.class); // import org.hibernate.query.Query;
			List<Coach> list = query.getResultList();
			tx.commit();
			return list;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("selectAll方法發生錯誤：" + e.getMessage());
		}
		return null;
	}

}
