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

public class BuyList implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "admin/buylist.jsp";
		FlightDAO dao = FlightDAO.getInstance();
		List<FlightVO> vo = dao.getBuyList();
		
		String userid = request.getParameter("userid");
		String checkin = request.getParameter("check-in");
		if(checkin == null) {
			checkin = "";
		}
		System.out.println("checkin : "+ checkin);
		
		int page = 20; //페이지당 게시글 수
		int curPage = 1;//현재페이지
		if(request.getParameter("curPage") != null ) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		
		//전체 개수 카운트
		int count = dao.getBuyCount();
		//페이지 수 반환
		List<Integer> pageCount = getPageCount(page, count);
		//한 페이지당 게시된 페이지의 수 = 10
		int oneBoard = 10;
		//페이징
		List<FlightVO> fvo = dao.getPaging(curPage, page);
		
		//받아온 날짜 변환
		String formattedDate=null;
		if(!checkin.isBlank()) {
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

			formattedDate = formatter.format(localdate);
			System.out.println(userid+" : "+formattedDate);
		}
		
		//기본 페이징
		if(userid == null && formattedDate == null) {
			count = dao.getBuyCount();
			pageCount = getPageCount(page, count);	
			fvo = dao.getBuyPaging(curPage, page);
			System.out.println("기본페이징 실행!");
			System.out.println("count : "+count+",page : "+page);
			System.out.println("pageCount : "+pageCount+",fvo : "+fvo);
		}
		

		//검색어가 있을때 페이징
		if(userid !=null || formattedDate != null) {

			
//			Date date = new Date(checkin);
//			int year = date.getYear()+1900;
//			int month = date.getMonth()+1;
//			int day = date.getDate();
//			System.out.println("year : "+year);
//			System.out.println("month : "+date.getMonth());
//			System.out.println("date : "+date.getDate());
//
//			LocalDate localdate = LocalDate.of(year,month,day);
//			System.out.println("localdate : "+localdate);
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd"); 
//
//			String formattedDate = formatter.format(localdate);
			if(userid != null && formattedDate != null) {
				count = dao.getBuysearchCount(userid, formattedDate);
				System.out.println("2페이징 실행!");
			}else if(userid != null && formattedDate == null) {
				count = dao.getBuysearchCountForuserid(userid);
				System.out.println("아이디페이징 실행!");
			}else if(userid == null && formattedDate != null){
				count = dao.getBuysearchCountForDate(formattedDate);
				System.out.println("날짜페이징 실행!");
			}
			
			pageCount = getPageCount(page, count);
			if(userid != null && formattedDate != null) {
				fvo = dao.getBuySearchPaging(curPage, page, userid, formattedDate);
			}else if(userid != null && formattedDate == null) {
				fvo = dao.getBuySearchPagingForid(curPage, page, userid);
			}else if(userid == null && formattedDate != null){
				fvo = dao.getBuySearchPagingForDate(curPage, page, formattedDate);
			}
		}
		
		int lastPage = pageCount.size();
		int prevPage = curPage -1;
		int nextPage = curPage +1;
		System.out.println(prevPage+" : "+nextPage);
		System.out.println("lastPage:"+lastPage);
		
		
		
		List<Integer> pageList = getPageBlock(lastPage, oneBoard, pageCount, curPage);
		System.out.println("pageList : "+pageList);
		
		//전체 리스트 반환
		//List<FlightVO> vo = dao.getFlightList(); //not paging
		
		//System.out.println(vo);
		
		//페이지 블럭 이동
		int prevBlock = ((curPage/oneBoard)*10+1)-10; //스타트블럭 페이지 1 11 
		int nextBlock = ((curPage/oneBoard)*10+1)+10; //다음블럭 스타트페이지 10 21 31
		if(curPage%10==0) {
			prevBlock = ((curPage/oneBoard-1)*10 +1)-10;
			nextBlock = ((curPage/oneBoard-1)*10+1)+10;
		}
		if(curPage<=10) {
			nextBlock = lastPage;
		}
		
		int blockcount = lastPage/oneBoard; //count의 개수만큼 페이징리스트를 묶어서(oneBoard*pageCount) 추가
		if(lastPage % oneBoard != 0 && lastPage>oneBoard) {
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
		
		request.setAttribute("userid", userid);
		request.setAttribute("checkin", checkin);
		
		RequestDispatcher dis  = request.getRequestDispatcher(url);
		dis.forward(request, response);
	
	}
	
	//페이지수 반환 메소드
	private List<Integer> getPageCount(int page, int count) {
		List<Integer> list = new ArrayList<>();
		int getPage = count/page;//0

		if(count%page != 0) {
			getPage++;  //1
		}
		System.out.println("count : "+count+",page : "+page+",getPAge : "+getPage);
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
			endBlock = lastPage; //마지막 페이지
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
