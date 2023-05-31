package member.service;

import java.util.List;

import member.vo.Member;

public interface MemberService {
	
	Member register(Member member);

	Member login(Member member);
	
	String genAuthCode();

	Member infoChange(Member member);
	
	Member checkPassword(Member member);

	Member passwordChange(Member member);

	List<Member> findAll();

	Member findOne(Integer userID);
	
	Member settings(Member member);

}