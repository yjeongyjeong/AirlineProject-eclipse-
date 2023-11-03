package com.user.controller.action.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.controller.action.Action;
import com.user.dao.LoginDAO;
import com.user.dao.UserDAO;

public class EmailCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email"); //입력한 이메일
		LoginDAO uDao = LoginDAO.getInstance();
		String result = uDao.emailDupleCheck(email); //이메일이 있는지 체크
		
		//필요한가?
		request.setAttribute("emailDupleCheckResult", result);
		request.setAttribute("email", email); 
		
		System.out.println("EmailCheckActonServlet에서 받은 result : " + result);
		System.out.println("EmailCheckActonServlet에서 받은 email : " + email);
		
		String url = "join/confirmEmail.jsp";
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
		
	}

}
