package com.user.controller.action.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.controller.action.Action;
import com.user.dao.LoginDAO;

public class JoinCheckIdAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		System.out.println("폼에서 입력받은 유저아이디 : " +userid);
		
		LoginDAO uDao = LoginDAO.getInstance();
		int result = uDao.useridDupleCheck(userid); //아이디 중복 여부 체크 1이면 중복, -1이면 사용가능
		System.out.println("아이디 중복 체크 결과(1이면 중복, -1이면 사용가능) : " + userid);
		request.setAttribute("result", result);
		request.setAttribute("userid", userid);
		
		String url = "join/joinUseridCheck.jsp";
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
