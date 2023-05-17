package member.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table
public class Coach implements Serializable {

	private static final long serialVersionUID = 2L;
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
//		super();
//		this.skill = skill;
//		this.background = background;
//		this.introduce = introduce;
//		this.license = license;
//		this.member = member;
//	}

//	public Coach(Integer userID, String skill, String background, String introduce, byte[] license, Member member) {
//		super();
//		this.userID = userID;
//		this.skill = skill;
//		this.background = background;
//		this.introduce = introduce;
//		this.license = license;
//		this.member = member;
//	}


	

	
	
}
