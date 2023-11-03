package com.user.controller.action.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.controller.action.Action;
import com.user.dao.UserDAO;

public class UpdatePwdAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pwd = request.getParameter("pwd");
		String userid = request.getParameter("userid");
		
		System.out.println("updatepwdAction에 넘어온 pwd : " + pwd);
		System.out.println("updatepwdAction에 넘어온 userid : " + userid);
		
		UserDAO uDao = UserDAO.getInstance();
		int result = uDao.updateUserPwd(userid, pwd);
		
		String url = "TEST/ERROR.jsp"; 
		if (result == 1) {
		url = "user/anyUpdateSuccess.jsp";
		}
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
