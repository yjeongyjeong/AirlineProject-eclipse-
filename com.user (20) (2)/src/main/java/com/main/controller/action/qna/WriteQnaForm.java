package com.main.controller.action.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.QnaDAO;

public class WriteQnaForm implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
//		int boardnum = qDao.getSelectBoardNum();
//
//		request.setAttribute("boardnum", (boardnum + 1));

		QnaDAO qDao = QnaDAO.getInstance();
		RequestDispatcher dis = request.getRequestDispatcher("/qna/writeQna.jsp");
		dis.forward(request, response);

	}

}
