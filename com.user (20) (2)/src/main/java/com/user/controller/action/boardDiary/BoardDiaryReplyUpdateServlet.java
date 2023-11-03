package com.user.controller.action.boardDiary;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.BoardDiaryDAO;
import com.user.dao.BoardDiaryDAOImpl;
import com.user.vo.BoardDiaryReplyVO;


@WebServlet("/boardDiaryReplyUpdate.do")
public class BoardDiaryReplyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		BoardDiaryDAO dao = new BoardDiaryDAOImpl();
//		
//		BoardDiaryReplyVO vo = new BoardDiaryReplyVO();
//		
//		request.setCharacterEncoding("utf-8");
//		String replyNum = request.getParameter("replyNum");
//		String bb = "boardNum";
//		
//		System.out.println("=====>"+request.getParameterValues("replyContent"));
//		String[] arr = request.getParameterValues("replyContent");
//		for(String str : arr) {
//			System.out.println("aa"+str);
//		}
//
//		vo.setBoardNum(Integer.parseInt(request.getParameter(bb)));
//		vo.setReplyNum(Integer.parseInt(request.getParameter("replyNum")));
////		vo.setReplyContent(request.getParameter(target));
//		vo.setReplyContent(request.getParameter("replyContent"));
//		
//		
//		System.out.println("bnum : "+Integer.parseInt(request.getParameter("boardNum")));
//		System.out.println("rnm : "+Integer.parseInt(request.getParameter("replyNum")));
////		System.out.println("rcon : "+request.getParameter("replyContent"+replyNum));
//		System.out.println("rcon : "+request.getParameter("replyContent"));
//		
//		dao.updateReply(vo);	
//		
//		response.sendRedirect("http://localhost:8181/project_jsp/boardDiaryView.do?boardNum="+Integer.parseInt(request.getParameter("boardNum")));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDiaryDAO dao = new BoardDiaryDAOImpl();
		
		BoardDiaryReplyVO vo = new BoardDiaryReplyVO();
		
		request.setCharacterEncoding("utf-8");
		
		vo.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
		vo.setReplyNum(Integer.parseInt(request.getParameter("replyNum")));
		vo.setReplyContent(request.getParameter("replyContent"));
		
		System.out.println("bnum : "+Integer.parseInt(request.getParameter("boardNum")));
		System.out.println("rnm : "+Integer.parseInt(request.getParameter("replyNum")));
		System.out.println("rcon : "+request.getParameter("replyContent"));
		
		dao.updateReply(vo);	
		
		dao.updateReplyCount(Integer.parseInt(request.getParameter("boardNum")));
		
		response.sendRedirect("boardDiaryView.do?boardNum="+Integer.parseInt(request.getParameter("boardNum"))+"#allReply");
	}

}
