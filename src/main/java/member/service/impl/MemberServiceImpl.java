package member.service.impl;

import java.util.List;

import member.dao.MemberDao;
import member.dao.impl.MemberDaoImpl;
import member.service.MemberService;
import member.vo.Member;

public class MemberServiceImpl implements MemberService {

	private MemberDao dao; // private MemberDao dao = new MemberDaoImpl();

	public MemberServiceImpl() {
		dao = new MemberDaoImpl();
	}

	@Override
	public Member register(Member member) {
		final String email = member.getEmail();
		String emailPatt = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

		if (!(email.matches(emailPatt))) {
			member.setMessage("信箱格式不正確，請重新輸入");
			member.setSuccessful(false);
			return member;
		}

		if (dao.selectByEmail(email) != null) {
			member.setMessage("帳號重複，請重新輸入");
			member.setSuccessful(false);
			return member;
		}

		final int resultCount = dao.insert(member);
		if (resultCount < 1) {
			member.setMessage("註冊失敗，請聯絡管理員!");
			member.setSuccessful(false);
			return member;
		}
		member.setMessage("註冊成功");
		member.setSuccessful(true);
		return member;
	}

	@Override
	public Member login(Member member) {
		final String email = member.getEmail();
		final String password = member.getPassword();

		member = dao.selectForLogin(email, password);

		if (email == null || password == null) {
			member.setMessage("帳號或密碼未輸入");
			member.setSuccessful(false);
			return member;
		}
		if (member == null) {
			member = new Member();
			member.setMessage("帳號或密碼錯誤，請重新輸入");
			member.setSuccessful(false);
			return member;
		}
		member.setMessage("登入成功");
		member.setSuccessful(true);
		return member;
	}

	@Override
	public String genAuthCode() {
		char[] code = new char[8];
		for (int i = 0; i < code.length; i++) {
			int ascii = (int) (Math.random() * 75) + 48;
			if ((ascii >= 48 && ascii <= 57) || (ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122)) {
				code[i] = (char) ascii;
			} else {
				i--;
			}
		}
		return new String(code);
	}

	@Override
	public Member infoChange(Member member) {
		Boolean isSuccess = dao.updateById(member);

		if (isSuccess == false) {
			member.setMessage("資訊變更失敗，請重新輸入");
			member.setSuccessful(false);
			return member;
		}

		member.setMessage("資訊變更成功");
		member.setSuccessful(true);
		return member;
	}

	@Override
	public Member checkPassword(Member member) {
		final Integer userID = member.getUserID();
		final String passwordInput = member.getPassword();

		String password = dao.selectById(userID).getPassword();

		if (!(password.equals(passwordInput))) {
			member.setMessage("原密碼輸入不正確");
			member.setSuccessful(false);
			return member;
		}

		member.setMessage("原密碼輸入正確");
		member.setSuccessful(true);
		return member;
	}

	@Override
	public Member passwordChange(Member member) {
		Boolean isSuccess = dao.updateById(member);

		if (isSuccess == false) {
			member.setMessage("密碼變更失敗，請重新輸入");
			member.setSuccessful(false);
			return member;
		}

		member.setMessage("密碼變更成功，請重新登入");
		member.setSuccessful(true);
		return member;
	}

	@Override
	public List<Member> findAll() {
		return dao.selectAll();
	}

	@Override
	public Member findOne(Integer userID) {
		return dao.selectById(userID);
	}

	@Override
	public Member settings(Member member) {
		Boolean isSuccess = dao.updateById(member);

		if (isSuccess == false) {
			member.setMessage("資訊變更失敗，請重新輸入");
			member.setSuccessful(false);
			return member;
		}

		member.setMessage("資訊變更成功");
		member.setSuccessful(true);
		return member;
	}

}
