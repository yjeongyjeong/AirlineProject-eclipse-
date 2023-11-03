package com.main.controller.action.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.QnaDAO;
import com.user.vo.QnaVO;

public class WriteQna implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		QnaVO vo = new QnaVO();
		
//		vo.setBoardnum(Integer.parseInt(request.getParameter("boardnum")));
		vo.setBoardsubject(request.getParameter("boardsubject"));
		vo.setBoardcontent(request.getParameter("boardcontent"));
		vo.setBoardwriter(request.getParameter("boardwriter"));
		vo.setRegidate(request.getParameter("regidate"));
		vo.setBoardrelevel(0);
		vo.setBoardreseq(0);
		
//		vo.setReadcount(Integer.parseInt(request.getParameter("regidate")));

		QnaDAO sDao = QnaDAO.getInstance();
		int result = sDao.writeQna(vo);
		if(result==1) {
			response.sendRedirect("BoardServlet?command=qna_list");
		}
		
		
	}

}
