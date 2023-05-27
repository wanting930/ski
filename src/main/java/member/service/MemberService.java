package member.service;

import member.vo.Member;

public interface MemberService {

	Member login(Member member);

	Member register(Member member);

	String genAuthCode();
	
	Member checkPassword(Member member);

	Member passwordChange(Member member);

}