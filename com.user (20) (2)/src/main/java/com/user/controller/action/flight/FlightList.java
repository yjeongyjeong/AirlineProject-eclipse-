package com.user.controller.action.flight;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.controller.action.Action;
import com.user.dao.FlightDAO;
import com.user.vo.FlightVO;

public class FlightList implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "flight/flightList.jsp";
		
		String department = request.getParameter("department");
		String arrive = request.getParameter("arrive");
		String checkin = (request.getParameter("check-in"));
//		String[] dateSplit = checkin.split(" ");
//		System.out.println("split1 : "+dateSplit[1]);
		
//		System.out.println(department+" : "+arrive+" : "+checkin);
//		Date date = new Date(checkin);
//		int year = date.getYear()+1900;
//		int month = date.getMonth()+1;
//		int day = date.getDate();
//		System.out.println("year : "+year);
//		System.out.println("month : "+date.getMonth());
//		System.out.println("date : "+date.getDate());
//
//		LocalDate localdate = LocalDate.of(year,month,day);
//		System.out.println("localdate : "+localdate);
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd"); 
//
//		String formattedDate = formatter.format(localdate);
//
//		System.out.println("Formatted date: " + formattedDate);
		

		
		int page = 20; //페이지당 게시글 수
		int curPage = 1;//현재페이지
		if(request.getParameter("curPage") != null ) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		
		FlightDAO dao = FlightDAO.getInstance();
		//전체 개수 카운트
		int count = dao.getCount();
		//페이지 수 반환
		List<Integer> pageCount = getPageCount(page, count);
		//한 페이지당 게시된 페이지의 수 = 10
		int oneBoard = 10;
		//페이징
		List<FlightVO> fvo = dao.getPaging(curPage, page);
		
		//기본 페이징
		if(department == null) {
			count = dao.getCount();
			pageCount = getPageCount(page, count);	
			fvo = dao.getPaging(curPage, page);
		}
		
		//검색어가 있을때 페이징
		if(department !=null && arrive != null && checkin !=null) {
			
			Date date = new Date(checkin);
			int year = date.getYear()+1900;
			int month = date.getMonth()+1;
			int day = date.getDate();
			System.out.println("year : "+year);
			System.out.println("month : "+date.getMonth());
			System.out.println("date : "+date.getDate());

			LocalDate localdate = LocalDate.of(year,month,day);
			System.out.println("localdate : "+localdate);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd"); 

			String formattedDate = formatter.format(localdate);
			
			count = dao.getsearchCount(department, arrive, formattedDate);
			pageCount = getPageCount(page, count);
			fvo = dao.getSearchPaging(curPage, page, department, arrive, formattedDate);
			
		}
		
		int lastPage = pageCount.size();
		int prevPage = curPage -1;
		int nextPage = curPage +1;
		System.out.println(prevPage+" : "+nextPage);
		
		
		List<Integer> pageList = getPageBlock(lastPage, oneBoard, pageCount, curPage);
		
		//전체 리스트 반환
		List<FlightVO> vo = dao.getFlightList(); //not paging
		
		//System.out.println(vo);
		
		//페이지 블럭 이동
		int prevBlock = ((curPage/oneBoard)*10+1)-10; //스타트블럭 페이지 1 11 
		int nextBlock = ((curPage/oneBoard)*10+1)+10; //다음블럭 스타트페이지 10 21 31
		if(curPage%10==0) {
			prevBlock = ((curPage/oneBoard-1)*10 +1)-10;
			nextBlock = ((curPage/oneBoard-1)*10+1)+10;
		}
		
		int blockcount = lastPage/oneBoard; //count의 개수만큼 페이징리스트를 묶어서(oneBoard*pageCount) 추가
		if(lastPage % oneBoard != 0) {
			blockcount++;
		}
		
		System.out.println("prev"+prevBlock);
		System.out.println("next"+nextBlock);
		System.out.println("block"+blockcount);
		
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("PrevPage", prevBlock);
		request.setAttribute("nextPage", nextBlock);
		request.setAttribute("blockcount", blockcount);
		request.setAttribute("firstP", -9);
		//request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageCount", pageList);
		request.setAttribute("list", fvo);
		
		request.setAttribute("department", department);
		request.setAttribute("arrive", arrive);
		request.setAttribute("checkin", checkin);
		RequestDispatcher dis  = request.getRequestDispatcher(url);
		dis.forward(request, response);

	}
	
	//페이지수 반환 메소드
	private List<Integer> getPageCount(int page, int count) {
		List<Integer> list = new ArrayList<>();
		int getPage = count/page;
		if(count%page != 0) {
			getPage++;
		}
		
		for(int i = 1; i<=getPage; i++) {
			list.add(i);
		}
		return list;
	}
	
	//페이지 반환 메소드2(한 페이지당 반환되는 게시글 리스트의 수)
	private List<Integer> getPageBlock(int lastPage, int oneBoard, List<Integer> pageCount, int curPage) {
		List<Integer> list = new ArrayList<>();
		// 한페이지당 1~10 11~20 20~나머지  oneBoard=10
		int count = lastPage/oneBoard; //count의 개수만큼 페이징리스트를 묶어서(oneBoard*pageCount) 추가
		if(lastPage % oneBoard != 0) {
			count++;
		}
		int startBlock = (curPage/oneBoard)*10+1;
		int endBlock = (curPage/oneBoard)*10+10;
		if(curPage<=10) {
			startBlock = 1;
		}
		
		if(lastPage< endBlock) {
			endBlock = lastPage;
		}
		if(curPage%10==0) {
			startBlock = (curPage/oneBoard-1)*10+1;
			endBlock = (curPage/oneBoard-1)*10+10;	
		}

		System.out.println("start : "+startBlock);
		System.out.println("end : "+endBlock);
		
		for(int i = startBlock; i<=endBlock; i++) {
			list.add(i);
		}
		
		return list;
	}

}
