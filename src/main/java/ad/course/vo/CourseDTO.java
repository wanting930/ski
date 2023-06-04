package ad.course.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private int courseAD;
    private int courseID;
    private String courseName;
    private Date startDate;
    private Date endDate;

}
