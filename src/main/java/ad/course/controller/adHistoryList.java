package ad.course.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import ad.course.service.CourseAdHistoryService;
import ad.course.service.Impl.CourseAdHistoryServiceImpl;
import ad.course.vo.CourseAdHistory;

@WebServlet("/ad/adHistory")
public class adHistoryList extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private CourseAdHistoryService service = new CourseAdHistoryServiceImpl();
	
	

}
