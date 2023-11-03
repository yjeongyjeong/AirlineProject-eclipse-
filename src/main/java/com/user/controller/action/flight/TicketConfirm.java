package com.user.controller.action.flight;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.controller.action.Action;
import com.user.dao.FlightDAO;
import com.user.vo.FlightVO;

public class TicketConfirm implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "flight/flightconfirm.jsp";
		String userid = request.getParameter("userid");
		int seq = Integer.parseInt(request.getParameter("seq"));
		System.out.println("seq2 : "+seq);
		
		FlightDAO dao = FlightDAO.getInstance();
		FlightVO vo = dao.getOneTicket(userid, seq);
		
		request.setAttribute("vo", vo);
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
