package course.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import order.courseCar.dao.CourseCarPK;
import order.courseCar.model.CourseCar;
import order.courseCar.service.CourseCarService;
import order.courseCar.service.CourseCarServiceImpl;
@WebServlet("/course_AC")
public class AddCourseCar extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 private CourseCarService courseCarService = new  CourseCarServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	    resp.setContentType("application/json;charset=utf-8");
	    int userID=Integer.parseInt(req.getParameter("userID"));
	    int productID=Integer.parseInt(req.getParameter("productID"));
	    CourseCarPK PK=new CourseCarPK(userID,productID);
	    CourseCar car=new CourseCar(PK,1);
	    boolean status=courseCarService.addCar(car);

	    

	    JSONObject jsonObject=new JSONObject();
	    jsonObject.put("status", status);
	    System.out.println(jsonObject);
      resp.getWriter().print(jsonObject);

  }
}
