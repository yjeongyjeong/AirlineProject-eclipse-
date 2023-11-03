package com.user.controller.action.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.controller.action.Action;

public class AuthEmailConfirmAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String authCode = (String) request.getSession().getAttribute("AuthenticationKey");
		String inputedCode = request.getParameter("inputedCode");

		System.out.println("여기까지 넘어온 값들(authCode, inputedCode) : " + authCode + ", " + inputedCode);
		System.out.println("이메일 넘어오는지 확인 : " + (String) request.getSession().getAttribute("email"));
		
		String url = "join/authCodeCheck.jsp";
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);

	}

}
