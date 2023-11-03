package com.user.controller.action.login;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.controller.action.Action;
import com.user.util.ShareVar_login;

public class AuthEmailRequestAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String inputedEmail = request.getParameter("email");

		// 인증코드 생성
		String AuthenticationKey = authCodeMaker();

		// mail server 설정
		String host = "smtp.gmail.com"; //네이버로 해도 되고 구글로 해도 됨 구글계정으로 사용함(한도 500개)
		ShareVar_login sv = ShareVar_login.getInstance();
		String user = sv.getHostID(); // 자신의 구글 계정
		String password = sv.getHostPW();// 자신의 구글 패스워드

		// 메일 받을 주소
		String to_email = inputedEmail;
		System.out.println("inputedEmail : " + inputedEmail);

		// SMTP 서버 정보를 설정한다.
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2"); //강제로 프로토콜 설정함

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		// email 전송
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(user));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));

			// 메일 제목
			msg.setSubject("안녕하세요. 카카오 항공의 인증메일입니다.", "UTF-8");
			// 메일 내용
			msg.setText("인증 번호 : " + AuthenticationKey);

			Transport.send(msg);
			System.out.println("이메일 전송 : " + AuthenticationKey);
			sv = ShareVar_login.getInstance();
			sv.authEamilCode = AuthenticationKey;

		} catch (AddressException e) {
			e.printStackTrace();
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		HttpSession saveKey = request.getSession();
		saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
		System.out.println("세션에 담긴 값 : " + AuthenticationKey);
		saveKey.setAttribute("email", inputedEmail);
		
		String url = "join/authCodeInput.jsp";
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
		System.out.println("authCodeInput.jsp로 이동!");
	}

	// 인증 번호 생성기 : 메서드를 따로 빼도 괜찮을 것 같음
	public String authCodeMaker() {
		String authCode = null;

		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
			case 0:
				// a-z
				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1:
				// A-Z
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2:
				// 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
		}

		authCode = temp.toString();
		System.out.println(authCode);

		return authCode;
	}
	
	
	
}
