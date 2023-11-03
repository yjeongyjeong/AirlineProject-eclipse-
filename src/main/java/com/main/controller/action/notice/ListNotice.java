package com.main.controller.action.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main.controller.action.qna.Action;
import com.user.dao.NoticeDAO;
import com.user.vo.NoticeVO;
import com.user.vo.PageVO;

public class ListNotice implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int pageNum = 1;
		int amount = 10;
		
		// 페이지번호를 클릭하는 경우
		if(request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			amount = Integer.parseInt(request.getParameter("amount"));
		}
		
		
		// DAO생성
		NoticeDAO dao = NoticeDAO.getInstance();
		
		// 2. pageVO생성
		List<NoticeVO> list = dao.getList(pageNum, amount);
		int total = dao.getTotal(); // 전체게시글수
		PageVO pageVO = new PageVO(pageNum, amount, total);
		System.out.println(pageNum);
		System.out.println(total);
		System.out.println(amount);
		
		// 3. 페이지네이션을 화면에 전달
		request.setAttribute("pageVO", pageVO);
		
		// 화면에 가지고 나갈 list를 request에 저장 !!
		request.setAttribute("list", list);
		
		RequestDispatcher dis = request.getRequestDispatcher("/notice/listNotice.jsp");
		dis.forward(request, response);
		
	}

}
