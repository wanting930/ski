package mem.vo;

import java.io.Serializable;

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
public class Coach implements Serializable {

	private static final long serialVersionUID = 2L;
	private Integer coachID;
	private Integer userID;
	private String skill;
	private String background;
	private String introduce;
	private byte[] license;

}
