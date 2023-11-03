package com.user.controller.action.flight;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.controller.action.Action;
import com.user.dao.FlightDAO;

public class TicketCancel implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resno = request.getParameter("resno");
		String userid = request.getParameter("userid");
		String mypage = request.getParameter("mypage");
		System.out.println("resno : "+resno);
		
		FlightDAO dao = FlightDAO.getInstance();
		int result = dao.cancelTicket(resno);
		
		if(result == 1&&mypage==null) {
			System.out.println("티켓취소 성공!!");
			response.sendRedirect("UserServlet?command=flightList&curPage=1&userid="+userid);
		}
		if(result==1&&mypage!=null) {
			response.sendRedirect("UserServlet?command=userticket&userid="+userid+"&curPage=1");
		}
			
			
			
			
	
	}

}
