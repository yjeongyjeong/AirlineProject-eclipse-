package com.user.controller.action.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.controller.action.Action;
import com.user.dao.LoginDAO;
import com.user.dao.UserDAO;

public class JoinCheckUser implements Action {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String url ="join/joinCheck.jsp";
      
      LoginDAO uDao = LoginDAO.getInstance();
      
      String engName = request.getParameter("engName").toUpperCase();
      String korName = request.getParameter("korName");
      int gender = Integer.parseInt(request.getParameter("gender"));

      long usernum = Long.parseLong( request.getParameter("usernum_first") + request.getParameter("usernum_last")); 
      
      
      int result = uDao.existUserCheck(engName, korName ,gender, usernum);
      request.setAttribute("engName", engName);
      request.setAttribute("korName", korName);
      request.setAttribute("gender", gender);
      request.setAttribute("usernum", usernum);

      String userYear = Long.toString(usernum).substring(0, 2);
      String userMonth = Long.toString(usernum).substring(2, 4);
      String userDate = Long.toString(usernum).substring(4, 6);
      System.out.printf("year : %s, month : %s, date : %s", userYear, userMonth, userDate);
      
      request.setAttribute("userYear", userYear);
      request.setAttribute("userMonth", userMonth);
      request.setAttribute("userDate", userDate);
      
      
      System.out.println(result);
      
      if(result == 1) { //가입 이력이 있음
    	  
          url ="join/login.jsp"; //이미 가입된 회원입니다 창은 어케 띄울까?
          request.setAttribute("message", "이미 가입된 회원입니다.");
      } else if(result == -1) { //가입 이력이 없음
          url ="join/joinInform.jsp"; 
       }
      
      RequestDispatcher dis = request.getRequestDispatcher(url);
      dis.forward(request, response);

   }

}