package article.controller;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.google.protobuf.Message;

@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipientEmail = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        // 發送郵件的SMTP設定
        String host = "smtp.gmail.com";
        String port = "465";
        String username = "你的Gmail帳號";
        String password = "你的Gmail密碼";

        // 設定郵件屬性
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // 建立驗證器
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        // 建立郵件會話
        Session session = Session.getInstance(properties, authenticator);

        try {
            // 建立郵件訊息
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(username));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            // 發送郵件
            Transport.send(mimeMessage);

            response.getWriter().println("郵件已成功發送！");
        } catch (MessagingException e) {
            response.getWriter().println("發送郵件時發生錯誤: " + e.getMessage());
        }
    }
}
