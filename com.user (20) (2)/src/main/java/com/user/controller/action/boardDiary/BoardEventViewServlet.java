package com.user.controller.action.boardDiary;

import java.io.IOException; 
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.BoardEventDAO;
import com.user.dao.BoardEventDAOImpl;
import com.user.vo.BoardEventFileVO;
import com.user.vo.BoardEventVO;


@WebServlet("/boardEventView.do")
public class BoardEventViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardEventDAO dao = new BoardEventDAOImpl();

		String url = "boardEvent/boardEventView.jsp";
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String userId = request.getParameter("userId");
		
		BoardEventVO vo = dao.selectOneByBoardNum(boardNum);
		request.setAttribute("board", vo);
		
		int count = dao.fileCount(boardNum);
		request.setAttribute("imgCount", count);
		
		List<BoardEventFileVO> list = dao.searchFileByBoardNum(boardNum);
		request.setAttribute("fileList", list);
		
		dao.updateReadcount(boardNum);
		
		
		
//		int replyCount = dao.replyCount(boardNum);
//		System.out.println(replyCount);
//		
//		PageDTO paging = new PageDTO();
//		int page = request.getParameter("page") == null? 1: Integer.parseInt(request.getParameter("page"));
//		paging.calcu(page, 5, replyCount);
//		paging.setLimitList(5);
//		
//		List<BoardDiaryReplyVO> list = dao.selectAllReply(paging.getStartNum(),paging.getEndNum(), boardNum);
//		
//		System.out.println("startnum: " + paging.getStartNum());
//		System.out.println("endnum: " + paging.getEndNum());
//		System.out.println("listsize: "+list.size());
//		
//		request.setAttribute("replyList", list);
//		request.setAttribute("paging", paging); 
//		request.setAttribute("replyCount", replyCount); 
//		request.setAttribute("checkLike", checkLike);
//		request.setAttribute("likecount", likecount);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
