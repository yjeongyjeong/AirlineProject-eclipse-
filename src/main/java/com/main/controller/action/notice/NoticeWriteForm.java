package com.main.controller.action.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main.controller.action.qna.Action;
import com.user.dao.NoticeDAO;

public class NoticeWriteForm implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDAO nDao = NoticeDAO.getInstance();
		
		
		RequestDispatcher dis = request.getRequestDispatcher("/notice/writeNotice.jsp");
		dis.forward(request, response);
	}

}
