package com.main.controller.action.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.QnaDAO;
import com.user.vo.QnaVO;

public class ReplyQnaForm implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		QnaVO vo = new QnaVO();
		int num = Integer.parseInt(request.getParameter("boardnum"));
		System.out.println(num);
		QnaDAO qDao = QnaDAO.getInstance();
		QnaVO list = qDao.selectOneQnaList(num);
		request.setAttribute("list", list);
		
		RequestDispatcher dis = request.getRequestDispatcher("/qna/replyQnaForm.jsp");
		dis.forward(request, response);
		
	}

}
