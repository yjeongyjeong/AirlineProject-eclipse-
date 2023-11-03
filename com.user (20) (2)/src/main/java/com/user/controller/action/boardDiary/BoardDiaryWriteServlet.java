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


@WebServlet("/boardDiaryWrite.do")
public class BoardDiaryWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDiaryDAO dao = new BoardDiaryDAOImpl();
		int num = dao.maxBoardNum();
		request.setAttribute("boardNum", num+1);
		System.out.println(num);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("boardDiary/boardDiaryWrite.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDiaryDAO dao = new BoardDiaryDAOImpl();
		
		request.setCharacterEncoding("utf-8");
		
		BoardDiaryVO vo = new BoardDiaryVO();
		
		vo.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
		vo.setBoardTitle(request.getParameter("boardTitle"));
		vo.setBoardWriter(request.getParameter("boardWriter"));
		vo.setBoardContent(request.getParameter("boardContent"));
		
		System.out.println(vo);
		
		try {
			dao.insertBoardDiary(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		response.sendRedirect("boardDiaryList.do");
		
	}

}
