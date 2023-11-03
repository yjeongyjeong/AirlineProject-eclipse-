package com.user.controller.action.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.controller.action.Action;
import com.user.dao.LoginDAO;

public class UseridCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		request.setAttribute("userid", userid);
		System.out.println("폼에서 입력받은 유저아이디 : " +userid);
		
		String email = request.getParameter("email");
		request.setAttribute("email", email);
		System.out.println("폼에서 입력받은 유저이메일 : " + email);
		
		HttpSession useridAndMailSession = request.getSession(); 
		useridAndMailSession.setAttribute("userid", userid);
		useridAndMailSession.setAttribute("email", email);
		
		LoginDAO uDao = LoginDAO.getInstance();
		int result = uDao.useridAndMailCheck(userid, email);
		
		request.setAttribute("userid", userid);
		request.setAttribute("email", email);
		request.setAttribute("result", result); //1이면 회원 -1이면 회원아님
		System.out.println("UseridCheck 입력받은 userid(1이면 회원, -1이면 회원아님) : " + result);
		
		String url = "join/confirmUserid.jsp";
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
		
	}

}
