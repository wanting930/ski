package course.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


	@Entity
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	    public class Course {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer courseID;
        private String courseName;
        private String courseIntroduce;
        private Integer courseMax;
        private Integer courseMin;
        private Integer coursePerson;
        private Integer coursePrice;
        private byte[] coursePhoto;
        private Integer level;   
        private Integer coachID;
        private Integer skill;
        
//        @ManyToOne
//        @JoinColumn(name = "pointID", insertable = false, updatable = false)
        private Integer pointID;
        private Date courseDate;
        private Date startDate;
        private Date endDate;
        private Integer courseStatus;


	    }




