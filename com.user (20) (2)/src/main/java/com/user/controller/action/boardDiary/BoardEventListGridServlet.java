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
import com.user.vo.PageDTO;

@WebServlet("/boardEventListGrid.do")
public class BoardEventListGridServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardEventDAO dao = new BoardEventDAOImpl();
		
		String viewLimit = request.getParameter("viewLimit");
		if(viewLimit==null||viewLimit=="") viewLimit="10";
			
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		if(searchType==null||searchType=="") searchType="boardTitle";
		if(keyword==null||keyword=="") keyword="";		
		
		int count = dao.searchCount(searchType, keyword);

		//PageDTO paging = dao.pageCalcu(request, count);	

		//paging.setLimitList(12);
		
		PageDTO paging = new PageDTO();
		int page = request.getParameter("page") == null? 1: Integer.parseInt(request.getParameter("page"));
		paging.calcu(page, 12, count);
		paging.setLimitList(12);
		
		List<BoardEventVO> list = dao.searchByType(paging.getStartNum(), paging.getEndNum(), searchType, keyword);
		List<BoardEventFileVO> repList = dao.findRepImg();
		request.setAttribute("repList", repList);
		
		System.out.println(searchType);
		System.out.println(keyword);
		System.out.println(count);
		
		System.out.println(list.size());
		
		System.out.println("startnum" + paging.getStartNum());
		System.out.println("endnum" + paging.getEndNum());
		
		request.setAttribute("paging", paging); 
		request.setAttribute("boardCount",count); 
		request.setAttribute("EventList", list);
		request.setAttribute("keyword", keyword);
		request.setAttribute("searchType", searchType);
		request.setAttribute("viewLimit", viewLimit);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("boardEvent/boardEventListGrid.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
