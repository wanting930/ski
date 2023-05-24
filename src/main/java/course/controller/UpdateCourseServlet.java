package course.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import course.dao.impl.CourseDaoImpl;
import course.entity.Course;



@WebServlet("/course_UD")
public class UpdateCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
			
			CourseDaoImpl dao = new CourseDaoImpl();
//			Integer productID = Integer.parseInt(readValue(request.getPart("productID")));
//	        String productClass = readValue(request.getPart("productClass"));
//	        String productName = readValue(request.getPart("productName"));
//	        Integer productPrice = Integer.parseInt(readValue(request.getPart("productPrice")));
//	        Integer productQuantity = Integer.parseInt(readValue(request.getPart("productQuantity")));
//	        String productDetail = readValue(request.getPart("productDetail"));
//	        String productStatus = readValue(request.getPart("productStatus"));
			
			Course course = new Course();
			
//			 System.out.println("courseID: " + request.getParameter("courseID"));
//			 System.out.println("courseID: " + request.getParameter("courseId"));
//			 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//				  String parameter1 = null;
//				  String parameter2 = null;
//
//				  Collection<Part> parts = request.getParts();
//				  for (Part part : parts) {
//				    String paramName = part.getName();
//
//				    if (paramName.equals("parameter1")) {
//				      parameter1 = request.getParameter(paramName);
//				    } else if (paramName.equals("parameter2")) {
//				      parameter2 = request.getParameter(paramName);
//				    }
//				  }
//
//				  // Use the parameter values as needed
//				}

			 //			Integer courseID = Integer.valueOf(request.getParameter("courseID"));
			
	        // Use the retrieved data as needed
	       
//			course.setCourseID(3);
//			course.setCourseID(courseID);
			course.setCourseName("maticsud3");
			course.setCourseIntroduce("Introduction to calculus");
			course.setCourseMax(30);
			course.setCourseMin(10);
			course.setCoursePerson(15);
			course.setCoursePrice(99.99);
			course.setCoursePhoto(new byte[]{/* course photo byte data */});
			course.setLevel("1");
			course.setCoachID(101);
			course.setSkill("0");
			course.setPointID(123);
			course.setCourseDate("2023-06-01");
			course.setStartDate("2023-06-10");
			course.setEndDate("2023-08-20");
			course.setCourseStatus("Active");
			
//			int resultarg = dao.updateCourse(course);
//			js轉物件後丟回，須個別屬性設定嗎?
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

