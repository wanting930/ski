package qa.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qa.service.qaService;
import qa.service.Impl.qaServiceImpl;
import qa.vo.qa;

@WebServlet("/deleteQa")
public class deleteServlet extends HttpServlet{
	private qaService service =  new qaServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 // 設置跨域
		 resp.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		 resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
		 resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		 resp.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求
		 // 設置返回格式
		 req.setCharacterEncoding("UTF-8");
		 resp.setContentType("application/json;charset=UTF-8");

	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}
