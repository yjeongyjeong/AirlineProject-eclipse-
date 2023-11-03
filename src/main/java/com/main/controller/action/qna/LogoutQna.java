package com.main.controller.action.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutQna implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.invalidate();
		request.setAttribute("message", "로그아웃 되었습니다.");
		RequestDispatcher dis = request.getRequestDispatcher("BoardServlet?command=qna_list");
		dis.forward(request, response);
		
	}

}
