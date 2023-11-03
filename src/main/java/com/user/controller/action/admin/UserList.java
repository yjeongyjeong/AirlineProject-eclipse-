package com.user.controller.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.controller.action.Action;
import com.user.dao.UserDAO;
import com.user.vo.UserInfoVO;

public class UserList implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "admin/userList.jsp";
		// 한 페이지당 게시물 개수
		int onePage = 10;
		//한 페이지당 블럭 개수
		int oneBoard = 10;
		
		
		UserDAO dao = UserDAO.getInstance();
		
		List<UserInfoVO> gradevo = dao.getGrade();
		//request.setAttribute("list", gradevo);
		
		int curPage = 1;
		if(request.getParameter("curPage") != null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		
		//변수 초기화
		 int lastPage = 1;
		 int prevPage = 1;
		 int nextPage = 1;
		 List<UserInfoVO> pageList = null;
		 List<Integer> page = null;
		 List<Integer> blockList = null;
		
		//페이지네이션 -> 검색을 하지 않으면 기본 페이지네이션 쿼리 작동
		if(request.getParameter("keyword") == null || request.getParameterValues("type")== null) {
			int totalCount = dao.getTotalCount(); //전체 게시물의 개수
			System.out.println("totalCount : "+totalCount);
			page = countPage(totalCount, onePage); 
			
			
			System.out.println("curpage : "+curPage);
			pageList = dao.pageList(onePage, curPage); //페이징(한페이당 게시물 개수, 현재 페이지) 
			System.out.println("pageList : "+pageList);
			System.out.println("기본페이지네이션");
			
			lastPage = page.size();
			prevPage = curPage-1;
			nextPage = curPage+1;
			blockList = getPageBlock(lastPage, oneBoard, pageList, curPage);

		}

		//검색 ->보통은 페이징만 작동한 리스트가 반영, 검색할때만 검색까지 반영한 리스트가 작동해야 함
		if(request.getParameter("keyword") != null && request.getParameterValues("type")!= null) {
			 String[] select = request.getParameterValues("type"); //검색조건
			 String keyword = request.getParameter("keyword");//검색어
			 System.out.println(select[0]+":"+keyword);
			 UserDAO searchDao = UserDAO.getInstance();
			 String type = "";
			 
			 if(select[0].equals("1")) {//등급
				 type = "gradecode";
			 }
			 if(select[0].equals("2")) {//아이디
				 type = "userid";
			 }
			 if(select[0].equals("3")){//한국이름
				 type = "korname";
			 }
			 if(select[0].equals("4")){//관리자
				 type = "admin";
			 }
			 
			 System.out.println(type+":"+keyword);
			 int totalCount = 0;
			 if(type.equals("userid")) {
				 totalCount = dao.getSearchTotalCount(type, keyword);//검색결과에 따른 게시글 개수
				 pageList = searchDao.getSearch(type, keyword, onePage, curPage, totalCount);//페이징
			 }
			 
			 request.setAttribute("keyword", keyword);
			 System.out.println("==========keyword================ : " + keyword);
			 
			 totalCount = dao.getSearchTotalCount(type, keyword);//검색결과에 따른 게시글 개수
			 pageList = searchDao.getSearch(type, keyword, onePage, curPage, totalCount);//페이징
			 System.out.println("totalCount : "+totalCount);
			 System.out.println("검색어 페이지네이션");
			 page = countPage(totalCount, onePage); 
			 
			 lastPage = page.size();
			 prevPage = curPage-1;
			 nextPage = curPage+1;
			 blockList = getPageBlock(lastPage, oneBoard, pageList, curPage);

		}
		
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
		
		
		 request.setAttribute("prevPage", prevBlock);
		 request.setAttribute("nextPage", nextBlock);
		 request.setAttribute("lastPage", lastPage);
		 request.setAttribute("page", page);
		 request.setAttribute("pagelist", pageList);
		 request.setAttribute("blockcount", blockcount);
		
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);

	}



	private List<Integer> countPage(int totalCount,int onePage) { 
		List<Integer> list = new ArrayList<Integer>();
		//전체 게시물의 개수를 구해 1p당 들어갈 게시물의 개수로 나눔
		int pageCount = totalCount/onePage; //페이지 개수
		if(totalCount % onePage != 0) {
			pageCount++;
		}
		System.out.println("pageCount : "+pageCount);
		for(int i = 1; i<=pageCount; i++) {
			list.add(i);
			//System.out.println("list : "+list);
		}
		return list;
	}
	
	//페이지 블록 메소드
	private List<Integer> getPageBlock(int lastPage, int oneBoard, List<UserInfoVO> pageList, int curPage) {
		List<Integer> list = new ArrayList<>();
		// 한페이지당 1~10 11~20 20~나머지  oneBoard=10
		int count = lastPage/oneBoard; //count의 개수만큼 페이징리스트를 묶어서(oneBoard*pageCount) 추가
		if(lastPage % oneBoard != 0) {
			count++;
		}
		int startBlock = (curPage/oneBoard)*10+1;
		int endBlock = (curPage/oneBoard)*10+10;
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
