package com.user.controller.action.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.controller.action.Action;
import com.user.dao.LoginDAO;
import com.user.dao.UserDAO;
import com.user.vo.KakaoUserVO;
import com.user.vo.UserInfoVO;

public class JoinInfoAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "TEST/ERROR.jsp";

		String korName = request.getParameter("korName");
		String engName = request.getParameter("engName");
		String gender = request.getParameter("gender");
		String usernum = request.getParameter("usernum");

		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String phone = (request.getParameter("phone_first") + request.getParameter("phone_middle")
				+ request.getParameter("phone_last"));
		String mail = request.getParameter("email") + "@" + request.getParameter("mail_Domain");
		String postcode = request.getParameter("postcode");
		String address = (request.getParameter("addressDefault") + request.getParameter("addressDetail"));

		System.out.println("korName : " + korName + " engName : " + engName + " gender : " + gender + " usernum : "
				+ usernum + " userid : " + userid + " pwd : " + pwd + " phone : " + phone + " mail : " + mail
				+ " postcode : " + postcode + " address : " + address);

		UserDAO uDao = UserDAO.getInstance();
		int result = uDao.insertKakaoUser(korName, engName, gender, usernum, userid, pwd, phone, mail, postcode,
				address);

		if (result == 1) {
			int userLogResult = uDao.insertUserLog(userid);
			System.out.println("회원로그 결과 : " + userLogResult);

			if (userLogResult == 1) {
				int termsResult01 = uDao.insertTermsLog01(userid);
				int termsResult02 = uDao.insertTermsLog02(userid);
				int termsResult03 = uDao.insertTermsLog03(userid);
				System.out.println("회원약관 결과 : " + termsResult01 + termsResult02 + termsResult03);

				url = "join/joinSuccess.jsp";
			}
		} 
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);

	}
}