package com.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.user.controller.action.Action;
import com.user.controller.action.ActionFactory;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
//		if(command == null) {
//			command ="noticeWrite_form";
//		}
		
	
		if(command == null) {
			String path = request.getServletContext().getRealPath("upload");
			String encType = "UTF-8";
			int sizeLimit = 20 * 1024 * 1024;
			MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
			command =multi.getParameter("command");
		}
		
		
		ActionFactory af = ActionFactory.getInstance();
		Action action = af.getAction(command);

		if (action != null) {

			try {
				action.execute(request, response);
				System.out.println("페이지 실행!");
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	
	}

