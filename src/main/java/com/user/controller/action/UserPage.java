package com.user.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.user.dao.UserDAO;
import com.user.vo.KakaoUserVO;

public class UserPage implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "user/userPage.jsp";
		String userid = request.getParameter("userid");
		UserDAO dao = UserDAO.getInstance();
		KakaoUserVO vo =  dao.getUser(userid);
		request.setAttribute("admin", vo);
		System.out.println("마이페이지 userid : "+userid);
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);

	}

}
