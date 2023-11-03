package com.user.controller.action.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.user.controller.action.Action;
import com.user.dao.UserDAO;
import com.user.vo.KakaoUserVO;

public class AdminPage implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "admin/adminPage.jsp";
		String userid = request.getParameter("userid");
		UserDAO dao = UserDAO.getInstance();
		KakaoUserVO vo =  dao.getUser(userid);
		request.setAttribute("admin", vo);
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);

	}

}
