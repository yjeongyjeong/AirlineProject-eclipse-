package com.main.controller.action.qna;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.QnaDAO;
import com.user.vo.QnaVO;

public class SearchQna implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<QnaVO> vo = new ArrayList<QnaVO>();
		String boardsubject = request.getParameter("searchbar");
		QnaDAO qDao = QnaDAO.getInstance();
		vo = qDao.searchQnaList(boardsubject);
		
		request.setAttribute("list", vo);
		
		RequestDispatcher dis = request.getRequestDispatcher("/qna/searchQna.jsp");
		dis.forward(request, response);
		
	}

}
