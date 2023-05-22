package course.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import course.dao.impl.CourseDaoImpl;
import course.entity.Course;


@WebServlet("/course_GA")
public class GetAllCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			
			CourseDaoImpl dao = new CourseDaoImpl();
//			PojoToJson(response, dao.getAllCourses());
			List<Course> list = dao.getAllCourses();
//			for (Course e : list) {
//				System.out.println(e.getCourseID());
//				System.out.println(e.getCourseName());
//			}
			
//			response.getWriter().print("Hello");
			
			Gson gson = new Gson();
			String jsonStr = gson.toJson(list);
			response.getWriter().write(jsonStr);
//			return PojoToJson(response, dao.getAllCourses());


	
	}
}

