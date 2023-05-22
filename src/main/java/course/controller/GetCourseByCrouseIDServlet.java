package course.controller;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import course.dao.impl.CourseDaoImpl;
import course.entity.Course;


@WebServlet("/course_GBI")
public class GetCourseByCrouseIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
			
			CourseDaoImpl dao = new CourseDaoImpl();
//			PojoToJson(response, dao.getAllCourses());
			Course course = dao.getCourseByCourseId(3);

				System.out.println(course.getCourseID());
				System.out.println(course.getCourseName());
			
			return;			
	
	}
}

