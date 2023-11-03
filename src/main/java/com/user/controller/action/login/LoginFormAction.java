package com.user.controller.action.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.controller.action.Action;
import com.user.dao.LoginDAO;
import com.user.dao.UserDAO;
import com.user.vo.KakaoUserVO;

public class LoginFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "main.jsp";

		LoginDAO uDao = LoginDAO.getInstance();

		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");

		System.out.println("userid and pwd : " + userid + " and " + pwd);
		//출력됨
		
		int result = uDao.userCheck(userid, pwd);

		if (result == 1) { // 아이디 존재, 비밀번호 일치하는 경우 메인페이지로 이동
			KakaoUserVO vo = uDao.getOneUser(userid);
			
			System.out.println("vo : " + vo);
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", vo);

			if (vo.getAdmin() == 1) { // 관리자인 경우 관리자 페이지로 이동
				url = "/admin/adminPage.jsp";
			}
		} 
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);

	}
}
