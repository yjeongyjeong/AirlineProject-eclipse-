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
import com.user.vo.BoardDiaryVO;
import com.user.vo.PageDTO;


@WebServlet("/boardDiaryList.do")
public class BoardDiaryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	BoardDiaryDAO dao = new BoardDiaryDAOImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String viewLimit = request.getParameter("viewLimit");
		if(viewLimit==null||viewLimit=="") viewLimit="10";
		
		String sort = request.getParameter("sort");
		if(sort==null||sort=="") sort="latest";
		
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		if(searchType==null||searchType=="") searchType="boardTitle";
		if(keyword==null||keyword=="") keyword="";		
		
		int count = dao.searchCount(searchType, keyword);

		PageDTO paging = dao.pageCalcu(request, count);	

		List<BoardDiaryVO> list = dao.searchByType(paging.getStartNum(), paging.getEndNum(), searchType, keyword, sort);
		
		System.out.println(searchType);
		System.out.println(keyword);
		System.out.println(count);
		
		System.out.println("startnum" + paging.getStartNum());
		System.out.println("endnum" + paging.getEndNum());
		
		request.setAttribute("paging", paging); 
		request.setAttribute("boardCount",count); 
		request.setAttribute("diaryList", list);
		request.setAttribute("keyword", keyword);
		request.setAttribute("searchType", searchType);
		request.setAttribute("viewLimit", viewLimit);
		request.setAttribute("sort", sort);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("boardDiary/boardDiaryList.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
