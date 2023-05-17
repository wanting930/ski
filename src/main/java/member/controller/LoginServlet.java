package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.dao.MemberDao;
import member.dao.impl.MemberDaoImpl;
import member.vo.Member;

@WebServlet("/login") // http://localhost:8080/ski/login
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberDao memDao = new MemberDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 設置中文編碼
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		String contextPath = req.getContextPath(); // /ski
		System.out.println(contextPath);

		// 第一次拜訪(URL)
		resp.getWriter().append("我是LoginServlet<br>");
		// (接受請求參數)
		String actoin = req.getParameter("action");
		String account = req.getParameter("acct");
		String password = req.getParameter("pwd");

		System.out.println("account =" + account);
		System.out.println("password = " + password);

		if (account.length() != 0 && password.length() != 0) {
			resp.getWriter().append("成功<br>");
			resp.getWriter().append("歡迎：" + account);
			Member member = memDao.selectById(1);
			System.out.println(member.getEmail());
		}

	}
}