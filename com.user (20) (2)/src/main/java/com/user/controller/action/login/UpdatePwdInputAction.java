package com.user.controller.action.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.controller.action.Action;

public class UpdatePwdInputAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = (String) request.getSession().getAttribute("userid");
		String email = (String) request.getSession().getAttribute("email");
//		request.setAttribute("userid", userid);
		System.out.println("session에 있는 userid : " + userid);
		System.out.println("session에 있는 email : " + email);
		
		String url = "./user/updateUserPwd.jsp";
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
