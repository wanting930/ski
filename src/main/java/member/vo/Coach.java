package member.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table
public class Coach extends Core{
	private static final long serialVersionUID = 5228656458800449563L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer coachID;
	private Integer userID;
	private String skill;
	private String background;
	private String introduce;
	private byte[] license;
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "userID", insertable = false, updatable = false)
//	private Member member;

	public Coach(Integer userID, String skill, String background, String introduce, byte[] license) {
		this.userID = userID;
		this.skill = skill;
		this.background = background;
		this.introduce = introduce;
		this.license = license;
	}

//	public Coach(String skill, String background, String introduce, byte[] license, Member member) {
//		this.skill = skill;
//		this.background = background;
//		this.introduce = introduce;
//		this.license = license;
//		this.member = member;
//	}

//	public Coach(Integer userID, String skill, String background, String introduce, byte[] license, Member member) {
//		this.userID = userID;
//		this.skill = skill;
//		this.background = background;
//		this.introduce = introduce;
//		this.license = license;
//		this.member = member;
//	}


	

	
	
}
