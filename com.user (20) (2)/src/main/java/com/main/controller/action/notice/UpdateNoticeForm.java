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

public class UpdateNoticeForm implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		regi,작성자,제목 수정x 
//		내용 , modify sysdate로 수정

		NoticeDAO nDao = NoticeDAO.getInstance();
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		NoticeVO vo = new NoticeVO();
		NoticeFileVO fvo = new NoticeFileVO();
		fvo=nDao.getOneNoticeFile(boardnum);
		request.setAttribute("file", fvo);
		System.out.println("======================== : " + fvo);
		vo = nDao.getOneNotice(boardnum);
		request.setAttribute("list", vo);
		RequestDispatcher dis = request.getRequestDispatcher("notice/updateNotice.jsp");
		dis.forward(request, response);
		
		
		
	}

}
