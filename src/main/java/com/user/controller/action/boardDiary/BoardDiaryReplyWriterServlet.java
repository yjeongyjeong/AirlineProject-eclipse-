package com.user.controller.action.boardDiary;

import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.BoardDiaryDAO;
import com.user.dao.BoardDiaryDAOImpl;
import com.user.vo.BoardDiaryReplyVO;


@WebServlet("/boardDiaryReplyWriter.do")
public class BoardDiaryReplyWriterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDiaryDAO dao = new BoardDiaryDAOImpl();
		
		request.setCharacterEncoding("utf-8");
		
		BoardDiaryReplyVO vo = new BoardDiaryReplyVO();
		
		String boardNum = request.getParameter("boardNum");
		
		int maxReplyNum = dao.maxReplyNum(Integer.parseInt(boardNum));
		System.out.println("renum"+maxReplyNum);
		
		vo.setReplyNum(maxReplyNum+1);
		vo.setBoardNum(Integer.parseInt(boardNum));
		vo.setReplyWriter(request.getParameter("replyWriter"));
		vo.setReplyContent(request.getParameter("replyContent"));
		
		System.out.println(vo);
		
		dao.insertReply(vo);	
		
		dao.updateReplyCount(Integer.parseInt(request.getParameter("boardNum")));
			
		response.sendRedirect("boardDiaryView.do?boardNum="+boardNum+"#allReply");
	}

}
