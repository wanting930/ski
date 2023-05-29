package qa.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.GsonUtils;
import qa.service.qaService;
import qa.service.Impl.qaServiceImpl;
import qa.vo.qa;

@WebServlet("/editQa")
public class editorServlet extends HttpServlet{

	private qaService service = new qaServiceImpl();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		 resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
		 resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		 resp.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求

		 req.setCharacterEncoding("UTF-8");
		 resp.setContentType("application/json;charset=UTF-8");

		 qa qz = new qa();
		 qz.setQaID(Integer.parseInt(req.getParameter("QaId")));
		 qz.setQuestionType(Integer.parseInt(req.getParameter("Type")));
		 qz.setQuestionTitle(req.getParameter("Title"));
		 qz.setAnswerContent(req.getParameter("Ans"));
		 qz.setQuestionDate(new Timestamp(System.currentTimeMillis()));
		 
		 service.edit(qz);

	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 doPost(req, resp);
	}

}
