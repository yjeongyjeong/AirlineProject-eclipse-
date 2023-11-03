package com.user.controller.action.flight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.controller.action.Action;
import com.user.dao.FlightDAO;
import com.user.vo.FlightVO;

public class UserTicket implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "flight/userticketList.jsp";
		String userid = request.getParameter("userid");
		
		int curPage = 1;
		if(request.getParameter("curPage") != null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		int onePage = 20; //한페이지당 게시물 개수 
		int oneBlock =10; //한 페이지당 블록개수
		
		
		FlightDAO dao = FlightDAO.getInstance();
		int count = dao.getTicketCountById(userid); //총 게시물 개수
		List<FlightVO> vo = dao.getTicketListById(curPage, onePage,userid); //리스트 가져오기
		List<Integer> pageCount = totalPage(count, onePage);//총 페이지 개수
		
		int pageSize = pageCount.size();
		System.out.println("pageSize : "+pageSize);
		int PrevPage = curPage-1;
		int nextPage = curPage+1;
		if(pageSize == 1) {
			PrevPage = 1;
			nextPage = 1;
		}
		if(pageSize<=nextPage) {
			nextPage = pageSize;
		}
		
		
		request.setAttribute("list", vo);
		request.setAttribute("PrevPage", PrevPage);
		request.setAttribute("nextPage", nextPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("curPage", curPage);
		request.setAttribute("userid", userid);
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);

	}

	private List<Integer> totalPage(int count, int onePage) {
		List<Integer> list = new ArrayList<Integer>();
		int page = count/onePage; //총 게시물/한페이당 게시물 개수
		if(count % onePage != 0) {
			page++;
		}
		for(int i = 1;i<=page; i++) {
			list.add(i);
		}
		return list;
	}

}
