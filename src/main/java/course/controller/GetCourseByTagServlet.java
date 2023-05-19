package course.controller;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import course.dao.impl.CourseDaoImpl;
import course.entity.Course;


//@WebServlet("/course")
public class GetCourseByTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
			
			CourseDaoImpl dao = new CourseDaoImpl();
//			PojoToJson(response, dao.getAllCourses());
			List<Course> list = dao.getAllCourses();
			for (Object e : list) {
				System.out.println(e);
			}
			return;
			
			
		//		Member member = json2Pojo(request, Member.class);
//		if (member == null) {
//			member = new Member();
//			member.setMessage("無會員資訊");
//			member.setSuccessful(false);
//			writePojo2Json(response, member);
//			return;
//		}
		
//		member = SERVICE.register(member);
//		writePojo2Json(response, member);

//	 @Override
//	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	        // Retrieve request parameters
//	        String parameter1 = request.getParameter("parameter1");
//	        String parameter2 = request.getParameter("parameter2");
//
//	        // Process the request parameters
//	        String result = processRequest(parameter1, parameter2);
//
//	        // Set the response content type
//	        response.setContentType("text/plain");
//
//	        // Write the response content
//	        response.getWriter().write(result);
//	    }
//
//	    private String processRequest(String param1, String param2) {
//	        // Process the request and generate a response
//	        // This is just a placeholder implementation
//	        return "Result: " + param1 + " - " + param2;

	
	}
}

