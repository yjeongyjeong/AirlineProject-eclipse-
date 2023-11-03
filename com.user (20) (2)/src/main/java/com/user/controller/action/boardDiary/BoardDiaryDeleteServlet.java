package com.user.controller.action.boardDiary;

import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.controller.action.Action;
import com.user.dao.BoardDiaryDAO;
import com.user.dao.BoardDiaryDAOImpl;


@WebServlet("/boardDiaryDelete.do")
public class BoardDiaryDeleteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("boardNum"));
		
		BoardDiaryDAO dao = new BoardDiaryDAOImpl();
		
		int result = dao.deleteBoard(num);
		
		response.sendRedirect("boardDiaryList.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
