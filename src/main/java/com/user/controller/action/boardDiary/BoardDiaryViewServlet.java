package com.user.controller.action.boardDiary;

import java.io.IOException; 
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.BoardDiaryDAO;
import com.user.dao.BoardDiaryDAOImpl;
import com.user.vo.BoardDiaryLikeVO;
import com.user.vo.BoardDiaryReplyVO;
import com.user.vo.BoardDiaryVO;
import com.user.vo.PageDTO;



@WebServlet("/boardDiaryView.do")
public class BoardDiaryViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDiaryDAO dao = new BoardDiaryDAOImpl();

		String url = "boardDiary/boardDiaryView.jsp";
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		
		String userId = request.getParameter("userId");
		
		if(userId==null) {
			int checkLike = -1;
			request.setAttribute("checkLike", checkLike);
		} else {
			int checkLike = dao.checkLike(boardNum, userId);	
			request.setAttribute("checkLike", checkLike);
		}
		
		BoardDiaryVO vo = dao.selectOneByBoardNum(boardNum);
		
		request.setAttribute("board", vo);
		
		dao.updateReadcount(boardNum);
		
		int likecount = dao.likecount(boardNum);
		
		int replyCount = dao.replyCount(boardNum);
		System.out.println("boardNum"+boardNum);
		System.out.println("userId"+userId);
		System.out.println(replyCount);
		
		PageDTO paging = new PageDTO();
		int page = request.getParameter("page") == null? 1: Integer.parseInt(request.getParameter("page"));
		paging.calcu(page, 5, replyCount);
		paging.setLimitList(5);
		
		List<BoardDiaryReplyVO> list = dao.selectAllReply(paging.getStartNum(),paging.getEndNum(), boardNum);
		
		System.out.println("startnum: " + paging.getStartNum());
		System.out.println("endnum: " + paging.getEndNum());
		System.out.println("listsize: "+list.size());
		//System.out.println("checklike : "+checkLike);
		
		request.setAttribute("replyList", list);
		request.setAttribute("paging", paging); 
		request.setAttribute("replyCount", replyCount); 
		
		request.setAttribute("likecount", likecount);

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
