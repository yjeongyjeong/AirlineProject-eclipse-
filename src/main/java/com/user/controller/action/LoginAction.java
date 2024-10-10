package com.user.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/join/login.jsp";
		
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		System.out.println("진입확인"+userid+":"+pwd);
		
	    // 로그인 실패 후, 리다이렉트될 때 메시지를 처리할 수 있도록 함
	    HttpSession session = request.getSession();
	    String message = (String) session.getAttribute("message");
	    if (message != null) {
	    	request.setAttribute("message", message);
	    	session.removeAttribute("message");
	    }
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);

	}

}
