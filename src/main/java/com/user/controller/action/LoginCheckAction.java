package com.user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.dao.UserDAO;
import com.user.vo.KakaoUserVO;

public class LoginCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 아이디, 비밀번호 유효성 검사 후 마이페이지 이동
		// String url = "admin/adminPage.jsp";
		System.out.println("점검화면 진입확인");
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		System.out.println(userid + " : " + pwd);
		request.setAttribute("userid", userid);
		request.setAttribute("pwd", pwd);
		

		UserDAO dao = UserDAO.getInstance();
		//아이디 체크
		int result = dao.getUserid(userid);
		//패스워드 체크
		KakaoUserVO vo = dao.loginCheck(userid);
		KakaoUserVO kvo = dao.getUser(userid);

		Cookie cookie = new Cookie("userid", userid);
		String rememberCheck = request.getParameter("rememberCheck");

		if (rememberCheck != null) { // 체크박스 체크여부에 따라 쿠키 저장 확인
			// 체크박스 체크 되었을 때
			// 쿠키 저장
			response.addCookie(cookie);
		} else {
			// 체크박스 체크 해제되었을 때
			// 쿠키 유효시간 0으로 해서 브라우저에서 삭제하게 한다.
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		// 쿼리를 통과하면 아이디는 같음-> 비밀번호 같은 경우->관리자/일반유저 여부 체크해서 url 나눠야 함
		// 로그인 통과 시 세션생성
		System.out.println("result : "+result);
		if(result == 0) {
			request.setAttribute("message", result);
			System.out.println("result id 진입");
			response.sendRedirect("UserServlet?command=login");
		}
		
		if(result == 1) {//아이디는 통과
		if(vo.getPwd().equals(pwd)) {
			if (vo.getAdmin() == 0) {// 일반회원
				response.sendRedirect("UserServlet?command=user&userid=" + userid);
				System.out.println("일반회원 로그인");
			} else {// 관리자
				response.sendRedirect("UserServlet?command=admin&userid=" + userid);
				System.out.println("관리자 로그인");
			}
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", kvo);

		} else {// 비밀번호 틀린 경우 -> 관리자 여부 체크할 필요 없음
			System.out.println("비밀번호 틀림");
			request.setAttribute("message", result);
			response.sendRedirect("UserServlet?command=login");
		}
	}
		
	}

}
