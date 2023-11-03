package com.main.controller.action.notice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main.controller.action.qna.Action;
import com.user.dao.NoticeDAO;
import com.user.vo.NoticeVO;

public class SearchNotice implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardsubject = request.getParameter("searchbar");
		NoticeDAO nDao = NoticeDAO.getInstance();
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		list = nDao.searchNotice(boardsubject);

		request.setAttribute("list", list);

		RequestDispatcher dis = request.getRequestDispatcher("notice/searchNotice.jsp");
		dis.forward(request, response);

	}

}
