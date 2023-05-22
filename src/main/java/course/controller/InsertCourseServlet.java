package course.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import course.dao.impl.CourseDaoImpl;
import course.entity.Course;


@WebServlet("/course_IS")
public class InsertCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			CourseDaoImpl dao = new CourseDaoImpl();
//			PojoToJson(response, dao.getAllCourses());
			Course course = new Course();
			
//			course.setCourseID(1);
//			course.setCourseName("Mathematics2");
//			course.setCourseIntroduce("Introduction to calculus");
//			course.setCourseMax(30);
//			course.setCourseMin(10);
//			course.setCoursePerson(15);
//			course.setCoursePrice(99.99);
//			course.setCoursePhoto(new byte[]{/* course photo byte data */});
//			course.setLevel("1");
//			course.setCoachID(101);
//			course.setSkill("0");
//			course.setPointID(123);
//			course.setCourseDate("2023-06-01");
//			course.setStartDate("2023-06-10");
//			course.setEndDate("2023-08-20");
//			course.setCourseStatus("Active");
			
			int result = dao.insertCourse(course);
			
			if (result > 0) {
				// 刪除成功
				response.getWriter().println("insert success");
			} else {
				// 刪除失敗
				response.getWriter().println("insert fail");
			}
			return ;					
	}
}

