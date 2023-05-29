package ad.course.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class courseAdHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseADHistoryID;
	@Column
	private Integer courseID;
	@Column
	private Timestamp historyDateTime;

}
