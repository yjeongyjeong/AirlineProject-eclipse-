package com.main.controller.action.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.NoticeDAO;

public class DeleteNotice implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		NoticeDAO nDao = NoticeDAO.getInstance();
		nDao.deleteNoticeFile(boardnum);
		int result = nDao.deleteNoticeBoard(boardnum);
		System.out.println("삭제해보기 : " + result);
		if(result==1) {
			response.sendRedirect("BoardServlet?command=notice_list");
		}
	}

}
