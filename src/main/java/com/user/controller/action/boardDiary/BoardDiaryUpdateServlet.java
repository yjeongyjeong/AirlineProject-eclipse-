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
import com.user.vo.BoardDiaryVO;

@WebServlet("/boardDiaryUpdate.do")
public class BoardDiaryUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	BoardDiaryDAO dao = new BoardDiaryDAOImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/boardDiary/boardDiaryUpdate.jsp";
		String num = request.getParameter("boardNum");

		BoardDiaryVO vo = dao.selectOneByBoardNum(Integer.parseInt(num));
		
		request.setAttribute("board", vo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDiaryVO vo = new BoardDiaryVO();
		
		request.setCharacterEncoding("utf-8");
		
		vo.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
		vo.setBoardTitle(request.getParameter("boardTitle"));
		vo.setBoardContent(request.getParameter("boardContent"));
		
		try {
			dao.updateBoard(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("boardDiaryList.do");
		
	}

}
