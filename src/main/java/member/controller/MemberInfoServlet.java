package member.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.dao.MemberDao;
import member.dao.impl.MemberDaoImpl;
import member.service.MemberService;
import member.service.impl.MemberServiceImpl;
import member.vo.Member;

@WebServlet("/member/memberInfo") // http://localhost:8080/ski/member/memberInfo
//@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5*1024*1024, maxRequestSize = 5*5*1024*1024)
public class MemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = -1326472979046813869L;
	private MemberService service;
	private MemberDao dao;
	private Gson gson;

	@Override
	public void init() throws ServletException {
		service = new MemberServiceImpl();
		dao = new MemberDaoImpl();
		gson = new Gson();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		resp.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求

		// 設置中文編碼
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");
		
		// 從 HttpServletRequest 中取得 JSON 字串
		BufferedReader br = req.getReader();
		Member member = gson.fromJson(br, Member.class);
		
		System.out.println(member);
		System.out.println("userID = " + member.getUserID());

		member = dao.selectById(member.getUserID());
		
//		byte[] photo = member.getPhoto();
//		System.out.println(photo);
		
//		byte[] photo = member.getPhoto(); // 從 member 物件中獲取 photo 的 byte 陣列

		byte[] photoBytes = member.getPhoto();
//		String base64Photo = Base64.getEncoder().encodeToString(photoBytes);
		
		ServletOutputStream stream = resp.getOutputStream();
		stream.write(photoBytes);
		stream.flush();
		stream.close();
		
//		String memberJson = gson.toJson(member);
//		resp.getWriter().write(memberJson);
//		resp.getWriter().flush();

	}

	
	
	
	
	
	
}