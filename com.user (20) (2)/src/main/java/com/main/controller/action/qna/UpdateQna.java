package com.main.controller.action.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.QnaDAO;
import com.user.vo.QnaVO;

public class UpdateQna implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("bbbb");
		QnaVO vo = new QnaVO();
		vo.setBoardsubject(request.getParameter("boardsubject"));
		vo.setBoardcontent(request.getParameter("boardcontent"));
		vo.setBoardwriter(request.getParameter("boardwriter"));
		vo.setBoardnum(Integer.parseInt(request.getParameter("boardnum")));
		System.out.println("aaaa");
		System.out.println(vo);
		QnaDAO qDao = QnaDAO.getInstance();
		int result = qDao.updateQna(vo);
	System.out.println("update : " + result);	
		if(result==1) {
			response.sendRedirect("BoardServlet?command=qna_list");
		}
		
	}

}
