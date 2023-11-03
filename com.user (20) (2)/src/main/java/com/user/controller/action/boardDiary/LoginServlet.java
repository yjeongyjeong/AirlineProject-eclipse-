package com.user.controller.action.boardDiary;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.controller.action.Action;
import com.user.dao.MemberDAO;
import com.user.vo.MemberVO;

public class LoginServlet implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		doPost(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");	//한글 데이터를 받을 때
		
		String userId = request.getParameter("userId");
		String pwd = request.getParameter("pwd");
		
		String url = "member/login.jsp";
		
		MemberDAO mDao = MemberDAO.getInstance();
		
		//result 1 => 로그인 성공, 0 => 비밀번호 틀림, -1 => 존재하지 않는 아이디
		int result = mDao.userCheck(userId, pwd);
		
		if(result==1) {
			MemberVO vo = mDao.getMember(userId);
			//System.out.println("====>"+vo);
			HttpSession session = request.getSession();	//로그인 세션을 만든 뒤 vo 정보를 넣어줌.
			session.setAttribute("loginUser", vo);		//세션이기 때문에 브라우저를 끄기 전까지 계속 값을 들고 있음
			request.setAttribute("message", "회원가입에 성공했습니다.");
			url = "member/login.jsp";		//새로고침해야 list가 나오는 문제 - > 메인으로 넘긴다음, list로 찾아오게 하면 됨.
		} else if(result==0) {
			request.setAttribute("message", "비밀번호가 틀립니다.");
		} else if(result==-1) {			
			request.setAttribute("message", "아이디가 존재하지 않습니다.");
		} 
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
