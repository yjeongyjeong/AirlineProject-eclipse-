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


@WebServlet("/boardDiaryLikeInsert.do")
public class BoardDiaryLikeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDiaryDAO dao = new BoardDiaryDAOImpl();
		
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String userId = request.getParameter("userId");
		System.out.println("userId : "+ userId);
		request.setAttribute("userId", userId);
		
		int result = dao.checkLike(boardNum, userId);
		
		if(result==1) {
			int delete = dao.deleteLikecount(boardNum, userId);
			dao.updateLikecount(boardNum);
			System.out.println("delete="+delete);
		} else {
			int insert = dao.insertLikecount(boardNum, userId);
			dao.updateLikecount(boardNum);
			System.out.println("insert="+insert);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("boardDiaryView.do?boardNum="+boardNum+"#allReply");
		dispatcher.forward(request, response);
	}

}
