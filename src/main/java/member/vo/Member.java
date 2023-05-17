package member.vo;

import java.io.Serializable;
import java.sql.Timestamp;

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
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userID;
	private String email;
	private String password;
	private String userName;
	private String nickName;
	private String gender;
	private Timestamp birthDate;
	private String personID;
	private String phone;
	private String address;
	private String level;
	private byte[] photo;
	private String role;
	private String userStatus;
	
	// 註冊
	public Member(String email, String password, String userName, String gender, Timestamp birthDate, String level,
			String role, String userStatus) {
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.level = level;
		this.role = role;
		this.userStatus = userStatus;
	}
	// 登入
	public Member(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
}
