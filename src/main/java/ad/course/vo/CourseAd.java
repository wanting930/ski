package ad.course.vo;

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
@Table(name = "CourseAD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseAd {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseAD;
	@Column
	private Integer courseID;

}
