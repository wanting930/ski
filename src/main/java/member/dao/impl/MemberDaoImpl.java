package member.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import member.dao.MemberDao;
import member.vo.Member;

public class MemberDaoImpl implements MemberDao {

	@Override
	public int insert(Member member) {
		try {
			getSession().persist(member);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insert方法發生錯誤：" + e.getMessage());
		}
		return -1;
	}

	@Override
	public int deleteById(Integer userID) {
		try {
			Session session = getSession();
			Member member = session.get(Member.class, userID);
			session.remove(member);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("deleteById方法發生錯誤：" + e.getMessage());
		}
		return -1;
	}

	@Override
	public boolean updateById(Member nMember) {
		try {
			Session session = getSession();
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
			return true;
		} catch (Exception e) {
			System.out.println("updateById方法發生錯誤：" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Member selectById(Integer userID) {
		try {
			Session session = getSession();
			Member member = session.get(Member.class, userID);
			return member;
		} catch (Exception e) {
			System.out.println("selectById方法發生錯誤：" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Member> selectAll() {
		final String hql = "FROM Member ORDER BY userID";
		try {
			Session session = getSession();
			Query<Member> query = session.createQuery(hql, Member.class); // import org.hibernate.query.Query;
			List<Member> list = query.getResultList();
			return list;
		} catch (Exception e) {
			System.out.println("selectAll方法發生錯誤：" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}