package course.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import course.dao.impl.CourseDaoImpl;
import course.entity.Course;



@WebServlet("/course_UD")
@MultipartConfig
public class UpdateCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
			
		CourseDaoImpl dao = new CourseDaoImpl();
			
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Integer courseID =  Integer.valueOf(request.getParameter("courseID"));
		Integer courseSkill =  Integer.valueOf(request.getParameter("courseSkill"));
		Integer courseLevel =  Integer.valueOf(request.getParameter("courseLevel"));
        String courseName = request.getParameter("courseName");
        Integer courseLocation =  Integer.valueOf(request.getParameter("courseLocation"));
        
        Date courseDate = null;
        Date startDate = null;
        Date endDate = null;
        
		try {
			courseDate = dateFormat.parse(request.getParameter("courseDate"));
			startDate = dateFormat.parse(request.getParameter("startDate"));
	        endDate = dateFormat.parse(request.getParameter("endDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        Integer coursePrice = Integer.valueOf(request.getParameter("coursePrice"));
        Integer courseMax =  Integer.valueOf(request.getParameter("courseMax"));
        Integer courseMin =  Integer.valueOf(request.getParameter("courseMin"));
        Integer courseStatus =  Integer.valueOf(request.getParameter("courseStatus"));       

        
//        try {
//        	Part filePart = request.getPart("coursePhoto");
//			String test = setimage(filePart);
//			System.out.println("test"+test);
//		} catch (IOException | ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

        
        
//        String coursePhoto = request.getParameter("coursePhoto");
//        byte[] imageBytes = Base64.getDecoder().decode(coursePhoto);
        
        String courseIntroduce = request.getParameter("courseIntroduce");
        
        Course course = new Course();
        
//		course.setCourseID(courseID);
//		course.setSkill(courseSkill);
//		course.setLevel(courseLevel);
//		course.setCourseName(courseName);
//		course.setPointID(courseLocation);
//		course.setCourseDate(courseDate);
//		course.setStartDate(startDate);
//		course.setEndDate(endDate);
//		course.setCoursePrice(coursePrice);
//		course.setCourseMax(courseMax);
//		course.setCourseMin(courseMin);
//		course.setCourseStatus(courseStatus);
//		course.setCoursePhoto(coursePhoto);
//		course.setCourseIntroduce(courseIntroduce);
		
//        dao.updateCourse(course);
        
       
        }	
	
	private String setimage(Part part) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        char[] buffer = new char[1024];
        for (int length = 0; (length = reader.read(buffer)) > 0;) {
            value.append(buffer, 0, length);
        }
        return value.toString();
		
		
//	    String fileName = part.getSubmittedFileName();
//        
//        InputStream inputStream = part.getInputStream();
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        byte[] buffer = new byte[4096];
//        int bytesRead;
//        while ((bytesRead = inputStream.read(buffer)) != -1) {
//          outputStream.write(buffer, 0, bytesRead);
//        }
//      byte[] imageBytes = outputStream.toByteArray();
//      return imageBytes;
    }
}

