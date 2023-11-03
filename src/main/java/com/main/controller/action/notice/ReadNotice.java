package com.main.controller.action.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main.controller.action.qna.Action;
import com.user.dao.NoticeDAO;
import com.user.vo.NoticeFileVO;
import com.user.vo.NoticeVO;

public class ReadNotice implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeFileVO fVo= new NoticeFileVO();
		NoticeDAO nDao = NoticeDAO.getInstance();
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		NoticeVO list = nDao.getOneNotice(boardnum);
		request.setAttribute("list", list);
		
		fVo= nDao.selectFileNotice(boardnum);

		request.setAttribute("file", fVo);

		String url = "notice/readNotice.jsp";
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
		
	}

}
