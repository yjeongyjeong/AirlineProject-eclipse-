package com.user.controller.action.boardDiary;

import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.BoardDiaryDAO;
import com.user.dao.BoardDiaryDAOImpl;


@WebServlet("/boardDiaryReplyDelete.do")
public class BoardDiaryReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		int replyNum = Integer.parseInt(request.getParameter("replyNum"));
		
		BoardDiaryDAO dao = new BoardDiaryDAOImpl();
		
		dao.deleteReply(replyNum, boardNum);
		
		dao.updateReplyCount(Integer.parseInt(request.getParameter("boardNum")));
		 
		response.sendRedirect("boardDiaryView.do?boardNum="+Integer.parseInt(request.getParameter("boardNum"))+"#allReply");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
