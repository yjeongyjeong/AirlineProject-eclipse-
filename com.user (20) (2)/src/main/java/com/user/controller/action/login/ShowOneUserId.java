package com.user.controller.action.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.controller.action.Action;
import com.user.dao.LoginDAO;
import com.user.dao.UserDAO;

public class ShowOneUserId implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//유저 이메일을 입력받아서 유저 아이디를 가져옴
		
		String email = (String) request.getSession().getAttribute("email");
		LoginDAO uDao = LoginDAO.getInstance();
		String result = uDao.getUserId(email);
		request.setAttribute("result", result);
		
		System.out.println("가져온 유저의 이메일 값 : " + email);
		System.out.println("유저 이메일 입력받아서 DB에서 가져오기 : " + result);
		//유저 이메일이 없는 유저는 여기까지 오지 않을 것이므로 if를 사용하지 않음
		
		RequestDispatcher dis = request.getRequestDispatcher("user/showUserId.jsp");
		dis.forward(request, response);
		
	}
}
