package com.main.controller.action.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.QnaDAO;

public class DeleteQna implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		QnaDAO qDao = QnaDAO.getInstance();
		int num = Integer.parseInt(request.getParameter("boardnum"));
		int result = qDao.deleteQna(num);

		if (result == 1) {
			response.sendRedirect("BoardServlet?command=qna_list");
		}

	}

}
