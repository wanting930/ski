package article.controller;
import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

//import member.core.Base64Util;
import member.service.MemberService;
import member.service.impl.MemberServiceImpl;
import member.vo.Member;



@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        doPost(req, res);
//    }
    
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

		member = service.forgotPassword(member);

		if (member == null) {
			member = new Member();
			member.setMessage("無會員資訊");
			member.setSuccessful(false);
			String memberJson = gson.toJson(member);
			resp.getWriter().write(memberJson);
			resp.getWriter().flush();
			return;
		}
		
		
		String userID = String.valueOf(member.getUserID());
		String encodedUserID = Base64Util.encode(userID);
		
		String resetPasswordUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
        + req.getContextPath() + "/member/passwordChange?userID=" + encodedUserID + "&from=mail";

		
		String to = member.getEmail(); 
		String subject = "【忘記密碼通知】Let's go skiing";
		String ch_name = member.getUserName();
		
		 String messageText = "Hello " + ch_name + "，您好：\r\n\r\n"
		            + "您申請了忘記密碼通知，如果您沒有申請，請忽略這封信件。\r\n"
		            + "如果您要重設密碼，請點選下方連結：\r\n\r\n"
		            + resetPasswordUrl
		            + "\r\n\r\n"
		            + "若有任何問題歡迎隨時回覆此郵件與我們聯繫，謝謝。\r\n"
		            + "Let's go skiing 會員中心 "; 

		service.sendMail(to, subject, messageText);
		
		member.setMessage("郵件已成功發送！");
		member.setSuccessful(true);
		String memberJson = gson.toJson(member);
		resp.getWriter().write(memberJson);
		resp.getWriter().flush();
//    	System.out.println("Rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr1");
//        String recipientEmail = request.getParameter("email");
//        String subject = request.getParameter("subject");
//        String message = request.getParameter("message");
//
//        // 發送郵件的SMTP設定
//        String host = "smtp.gmail.com";
//        String port = "587";
//        String username = "helpMeStartALiveStreamingRrr";// 你的Gmail帳號
//        String password = "helpMeStartALiveStreamingRrr";// 你的Gmail密碼
//
//        // 設定郵件屬性
//        Properties properties = new Properties();
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.port", port);
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//
//        // 建立驗證器
//        Authenticator authenticator = new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        };
//
//        // 建立郵件會話
//        Session session = Session.getInstance(properties, authenticator);
//
//        try {
//            // 建立郵件訊息
//            MimeMessage mimeMessage = new MimeMessage(session);
//            mimeMessage.setFrom(new InternetAddress(username));
//            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
//            mimeMessage.setSubject(subject);
//            mimeMessage.setText(message);
//
//            // 發送郵件
//            Transport.send(mimeMessage);
//
//            response.getWriter().println("郵件已成功發送！");
//            System.out.println("Rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
//        } catch (MessagingException e) {
//            response.getWriter().println("發送郵件時發生錯誤: " + e.getMessage());
//        }
    }
}
