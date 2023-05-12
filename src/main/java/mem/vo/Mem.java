package mem.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;

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
@Data
@Builder
public class Mem implements Serializable {

	private static final long serialVersionUID = 1L;
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

}
