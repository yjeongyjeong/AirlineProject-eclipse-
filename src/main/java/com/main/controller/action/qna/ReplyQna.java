package com.main.controller.action.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.QnaDAO;
import com.user.vo.QnaVO;

public class ReplyQna implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  QnaDAO qDao = QnaDAO.getInstance();
		    
		    int parentBoardNum = Integer.parseInt(request.getParameter("boardnum"));

		    int parentRelevel = qDao.getParentRelevel(parentBoardNum);
		    int parentReseq = qDao.getParentReseq(parentBoardNum);

		    int newRelevel = parentRelevel + 1;
		    int newReseq = parentReseq + 1;
		    
		    int getParentBoardNum = parentBoardNum;

		    if (parentRelevel > 0) {
		        getParentBoardNum = qDao.getParentBoardNum(parentBoardNum);
		    }
		    
		    QnaVO vo = new QnaVO();
		    vo.setBoardnum(getParentBoardNum); 
		    vo.setBoardsubject(request.getParameter("boardsubject"));
		    vo.setBoardcontent(request.getParameter("boardcontent"));
		    vo.setBoardwriter("관리자");
		    vo.setBoardreref(getParentBoardNum);
		    vo.setBoardrelevel(newRelevel);
		    vo.setBoardreseq(newReseq);

		    int result = qDao.insertReplyQna(vo);
		    if (result == 1) {
		        response.sendRedirect("BoardServlet?command=qna_list");
		    }

}
}
