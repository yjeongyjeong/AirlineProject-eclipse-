package com.main.controller.action.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.main.controller.action.qna.Action;
import com.user.dao.NoticeDAO;
import com.user.vo.UserVO;

public class LoginNotice implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String pass = request.getParameter("pass");
		System.out.println("username : "+ username+ " / pass: " + pass);
		String url = "BoardServlet?command=notice_list";
		NoticeDAO ndao = NoticeDAO.getInstance();
		// result가 1이면 로그인성공, 0이면 암호 틀림, -1이면 아이디 틀림
		int result = ndao.userCheck(username, pass);
		System.out.println("로그인 : " + result);
		if (result == 1) {
			UserVO vo = ndao.getMember(username);
			HttpSession session = request.getSession();
			session.setAttribute("username", vo);
			System.out.println("listuser : "+vo );
			url = "BoardServlet?command=notice_list";
		} else if (result == 0) {
			request.setAttribute("message", "비밀번호를 확인해주세요.");
		} else if (result == -1) {
			request.setAttribute("message", "아이디를 확인해주세요.");
		}
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);

	}
	
}
