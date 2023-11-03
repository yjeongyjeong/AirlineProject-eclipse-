package com.user.controller.action.boardDiary;

import java.io.IOException;  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.BoardEventDAO;
import com.user.dao.BoardEventDAOImpl;

@WebServlet("/boardEventDelete.do")
public class BoardEventDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		
		BoardEventDAO dao = new BoardEventDAOImpl();
		
		int result = dao.deleteBoard(boardNum);
		
		response.sendRedirect("boardEventList.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
