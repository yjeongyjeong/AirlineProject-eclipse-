package com.user.controller.action.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.controller.action.Action;
import com.user.dao.UserDAO;
import com.user.vo.KakaoUserVO;

public class UpdateUser implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println();
		
		UserDAO dao = UserDAO.getInstance();
//		List<KakaoUserVO> allUser = dao.getAllUser();
//		for(KakaoUserVO vo : allUser) {
//			System.out.println(vo);
//		}
		//request.setAttribute("alluser", allUser);
		
		KakaoUserVO vo = new KakaoUserVO();
		
		String usernum = request.getParameter("usernum");	//생년월일
		
		String korName = request.getParameter("korName");	//1
		vo.setKorName(korName);
		String engName = request.getParameter("engName");	//2
		vo.setEngName(engName);
		String pwd = request.getParameter("pwd");			//3
		vo.setPwd(pwd);
		String gender = request.getParameter("gender");		//4
		vo.setGender(Integer.parseInt(gender));
		String mail = request.getParameter("email") +"@"+request.getParameter("mail_Domain");	//5
		vo.setMail(mail);
		String phone = ( request.getParameter("phone_first")+"-"+request.getParameter("phone_middle")+"-"+ request.getParameter("phone_last") ); //6
		vo.setPhone(phone);
		String postcode = request.getParameter("postcode");	//7
		vo.setPostcode(Integer.parseInt(postcode));
		String address = request.getParameter("address");	//8
		vo.setAddress(address);
		String userid = request.getParameter("userid");		//9
		vo.setUserid(userid);
		
		System.out.println("korName : " + korName + " engName : " + engName+ " gender : " + gender+ " usernum : " + usernum+ " userid : "+  userid+ " pwd : " + pwd+ " phone : " + phone + " mail : " + mail+ " postcode : " + postcode);
		System.out.println("address: " + address);
		
		UserDAO uDao = UserDAO.getInstance();
		int result = uDao.updateUser(vo);
		
		KakaoUserVO updatedUser = uDao.getUser(userid);
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", updatedUser);
		
		String url = "main.jsp";
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
		
	}

}
