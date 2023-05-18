package course.entity;

import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;

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
	        private int courseID;
	        private String courseName;
	        private String courseIntroduce;
	        private int courseMax;
	        private int courseMin;
	        private int coursePerson;
	        private double coursePrice;
	        private byte[] coursePhoto;
	        private String level;
	        private int coachID;
	        private String skill;
	        private int pointID;
	        private String courseDate;
	        private String startDate;
	        private String endDate;
	        private String courseStatus;

//	        // Constructors
//	        public Course() {
//	        }
//
//	        public Course(int courseID, String courseName, String courseIntroduce, int courseMax, int courseMin, int coursePerson, double coursePrice,
//	                      byte[] coursePhoto, String level, int coachID, String skill, int pointID, String courseDate, String startDate,
//	                      String endDate, String courseStatus) {
//	            this.courseID = courseID;
//	            this.courseName = courseName;
//	            this.courseIntroduce = courseIntroduce;
//	            this.courseMax = courseMax;
//	            this.courseMin = courseMin;
//	            this.coursePerson = coursePerson;
//	            this.coursePrice = coursePrice;
//	            this.coursePhoto = coursePhoto;
//	            this.level = level;
//	            this.coachID = coachID;
//	            this.skill = skill;
//	            this.pointID = pointID;
//	            this.courseDate = courseDate;
//	            this.startDate = startDate;
//	            this.endDate = endDate;
//	            this.courseStatus = courseStatus;
//	        }
//
//			public int getCourseID() {
//				return courseID;
//			}
//
//			public void setCourseID(int courseID) {
//				this.courseID = courseID;
//			}
//
//			public String getCourseName() {
//				return courseName;
//			}
//
//			public void setCourseName(String courseName) {
//				this.courseName = courseName;
//			}
//
//			public String getCourseIntroduce() {
//				return courseIntroduce;
//			}
//
//			public void setCourseIntroduce(String courseIntroduce) {
//				this.courseIntroduce = courseIntroduce;
//			}
//
//			public int getCourseMax() {
//				return courseMax;
//			}
//
//			public void setCourseMax(int courseMax) {
//				this.courseMax = courseMax;
//			}
//
//			public int getCourseMin() {
//				return courseMin;
//			}
//
//			public void setCourseMin(int courseMin) {
//				this.courseMin = courseMin;
//			}
//
//			public int getCoursePerson() {
//				return coursePerson;
//			}
//
//			public void setCoursePerson(int coursePerson) {
//				this.coursePerson = coursePerson;
//			}
//
//			public double getCoursePrice() {
//				return coursePrice;
//			}
//
//			public void setCoursePrice(double coursePrice) {
//				this.coursePrice = coursePrice;
//			}
//
//			public byte[] getCoursePhoto() {
//				return coursePhoto;
//			}
//
//			public void setCoursePhoto(byte[] coursePhoto) {
//				this.coursePhoto = coursePhoto;
//			}
//
//			public String getLevel() {
//				return level;
//			}
//
//			public void setLevel(String level) {
//				this.level = level;
//			}
//
//			public int getCoachID() {
//				return coachID;
//			}
//
//			public void setCoachID(int coachID) {
//				this.coachID = coachID;
//			}
//
//			public String getSkill() {
//				return skill;
//			}
//
//			public void setSkill(String skill) {
//				this.skill = skill;
//			}
//
//			public int getPointID() {
//				return pointID;
//			}
//
//			public void setPointID(int pointID) {
//				this.pointID = pointID;
//			}
//
//			public String getCourseDate() {
//				return courseDate;
//			}
//
//			public void setCourseDate(String courseDate) {
//				this.courseDate = courseDate;
//			}
//
//			public String getStartDate() {
//				return startDate;
//			}
//
//			public void setStartDate(String startDate) {
//				this.startDate = startDate;
//			}
//
//			public String getEndDate() {
//				return endDate;
//			}
//
//			public void setEndDate(String endDate) {
//				this.endDate = endDate;
//			}
//
//			public String getCourseStatus() {
//				return courseStatus;
//			}
//
//			public void setCourseStatus(String courseStatus) {
//				this.courseStatus = courseStatus;
//			}


	    }




