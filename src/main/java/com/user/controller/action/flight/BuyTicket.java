package com.user.controller.action.flight;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.controller.action.Action;
import com.user.dao.FlightDAO;
import com.user.vo.FlightVO;

public class BuyTicket implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//티켓구매처리
		String userid = request.getParameter("userid");
		String num = request.getParameter("num");
		String department = request.getParameter("department");
		String arrive = request.getParameter("arrive");
		String checkin = request.getParameter("checkin");
		System.out.println(num);
		FlightDAO dao = FlightDAO.getInstance();
		FlightVO fvo = null;
		if(request.getParameter("department")== null) {
			fvo = dao.getFlightInfo(num);
		}else {
			fvo = dao.getFlightInfoBySearch(num, department, arrive, checkin);
		}
		
		FlightVO vo = new FlightVO();
		vo.setRegidate(fvo.getRegidate());
		vo.setAirline(fvo.getAirline());
		vo.setFlightnum(fvo.getFlightnum());
		vo.setDepairport(fvo.getDepairport());
		vo.setArrairport(fvo.getArrairport());
		vo.setEstimate(fvo.getEstimate());
		vo.setUserid(userid);
		System.out.println("vo : "+vo);
		
		int result = dao.buyTicket(vo);
		if(result == 1) {
			System.out.println("티켓구매성공!!");
			int flightseq = dao.getSeqnum();
			System.out.println("seq : "+flightseq);
			//구매내역 확인
			response.sendRedirect("UserServlet?command=ticketconfirm&userid="+userid+"&seq="+flightseq);
			//마이페이지로->구매내역 확인페이지로 이동
		}else {
			System.out.println("구매 실패!!");
			//티켓조회페이지
			response.sendRedirect("UserServlet?command=flightlist&userid="+userid);
		}

	}

}
