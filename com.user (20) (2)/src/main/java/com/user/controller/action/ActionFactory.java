package com.user.controller.action;

import com.user.controller.action.admin.AdminFlightList;
import com.user.controller.action.admin.AdminPage;
import com.user.controller.action.admin.UserList;
import com.user.controller.action.flight.BuyList;
import com.user.controller.action.flight.BuyTicket;
import com.user.controller.action.flight.FlightList;
import com.user.controller.action.flight.TicketCancel;
import com.user.controller.action.flight.TicketConfirm;
import com.user.controller.action.flight.UserTicket;
import com.user.controller.action.login.AuthEmailConfirmAction;
import com.user.controller.action.login.AuthEmailRequestAction;
import com.user.controller.action.login.EmailCheckAction;
import com.user.controller.action.login.FindPwdAction;
import com.user.controller.action.login.JoinCheck;
import com.user.controller.action.login.JoinCheckIdAction;
import com.user.controller.action.login.JoinCheckUser;
import com.user.controller.action.login.JoinInfoAction;
import com.user.controller.action.login.JoinTermsAction;
import com.user.controller.action.login.ShowOneUserId;
import com.user.controller.action.login.UpdatePwdAction;
import com.user.controller.action.login.UpdatePwdInputAction;
import com.user.controller.action.login.UpdateUser;
import com.user.controller.action.login.UpdateUserForm;
import com.user.controller.action.login.UseridCheckAction;
import com.user.controller.action.login.findIdAction;

public class ActionFactory{

	private ActionFactory(){}
	
	private static ActionFactory instance = new ActionFactory();

	public static ActionFactory getInstance() {
		return instance;
	}
	
	
	//multipart 또한 command  값으로 던져줘야 함
	public Action getAction(String command) {
		Action action = null;
		System.out.println("ActionFactory : "+command);
		if(command.equals("main")) {
			action = new MainAction();
		}else if(command.equals("login")) { //로그인 창으로 이동
			action = new LoginAction();
		}
//		else if(command.equals("login_form")) { //로그인 기능 동작
//			action = new LoginFormAction();
//		}
		else if(command.equals("login_check")) {//로그인 체크
			action = new LoginCheckAction();
		}else if(command.equals("logout")){//로그아웃
			action = new Logout();
		}else if(command.equals("join_terms")) {//회원가입 약관동의 페이지로 이동
			action = new JoinTermsAction();
		}else if(command.equals("join_check")) {//기존 회원 여부 페이지로 이동
			action = new JoinCheck();
		}else if(command.equals("join_check_user")) {//기존 회원 여부 확인
			action = new JoinCheckUser(); //기존회원이면 로그인화면, 기존회원이 아니면 정보입력 페이지로 이동
		}else if(command.equals("join_info")) {//회원가입 정보 입력 페이지+로그인 화면으로 이동
			action = new JoinInfoAction();
		}else if(command.equals("find_id")) {//아이디 찾기 페이지로 이동 -> 이메일 인증을 하지 않으면 확인버튼
			action = new findIdAction();//대문자로 바꾸기.....
		}else if(command.equals("user_register_idCheck")) {
			action = new JoinCheckIdAction();
		}else if(command.equals("email_check") ) {//이메일 입력 후 회원정보와 일치 확인
			action = new EmailCheckAction(); 
		}else if(command.equals("auth_email_send")) {
			action = new AuthEmailRequestAction(); //인증번호 발송 시스템 및 인증번호 입력 창
		}else if(command.equals("authCode_Check")) {//입력받은 인증번호 확인창-> 확인되면 아이디 찾기에서 확인버튼이 활성화
			action = new AuthEmailConfirmAction();
		}else if(command.equals("show_userid")) { //유저 이메일로 유저 아이디 검색
			action = new ShowOneUserId();
		}else if(command.equals("find_pwd")) { //비밀번호 찾기 페이지로 이동
			action = new FindPwdAction();
		}else if(command.equals("userid_email_check")) { //비밀번호 찾기 페이지에서 userid, email로 유저 확인
			action = new UseridCheckAction();
		}else if(command.equals("update_pwd_form")) { //새 비밀번호 입력 페이지로 이동
			action = new UpdatePwdInputAction();
		}else if(command.equals("update_pwd")) { //새 비밀번호 업데이트 페이지
			action = new UpdatePwdAction();
		}else if(command.equals("header")){//헤더부분
			action = new Header();
		}else if(command.equals("user")) {//유저 마이페이지로 이동
			action = new UserPage();
		}
		else if(command.equals("admin")) {//관리자 페이지로 이동
			action = new AdminPage();
		}else if(command.equals("user_list")) {//관리자 페이지에서 조회하는 유저리스트
			action = new UserList();
		}else if(command.equals("user")) {//유저(마이페이지)로 이동
			action = new UserPage();
		}/*else if(command.equals("notice")) {//공지사항 페이지 이동
			action = new Notice();
		}else if(command.equals("noticeDetail")) {//공지사항 상세페이지 이동
			action = new NoticeDetail();
		}else if(command.equals("noticeWrite")) {//공지사항 글쓰기 페이지
			action = new NoticeWrite();
		}else if(command.equals("noticeWrite_form")) {//공지사항 글쓰기 처리
			action = new NoticeWriteForm();
		}else if(command.equals("noticeupdate")) {//공지사항 수정
			action = new NoticeUpdate();
		}else if(command.equalsIgnoreCase("noticeUpdate_form")) {//공지사항 수정 처리
			action = new NoticeUpdateForm();
		}else if(command.equals("userDelete")) {//공지사항 게시글 삭제
			action = new NoticeDelete();}*/
		else if(command.equals("flightList")) {//비행기 스케줄 조회
			action = new FlightList();
		}else if(command.equals("buyticket")) {//비행기 티켓 구매
			action = new BuyTicket();
		}else if(command.equals("ticketconfirm")) {//티켓구매 확인 페이지
			action = new TicketConfirm();
		}else if(command.equals("cancelTicket")) {//티켓구매 취소
			action = new TicketCancel();
		}else if(command.equals("adminFlight_list")) { //관리자페이지 비행기 스케줄 조회
			action = new AdminFlightList();
		}else if(command.equals("buylist")) {//전체회원구매내역(관리자)
			action = new BuyList();
		}else if(command.equals("main")) {//메인페이지로 갈때
			action = new UpdateUser();
		}else if(command.equals("updateUser")) {//회원정보 수정
			action = new UpdateUser();
		}else if(command.equals("updateUserForm")) {
	         action = new UpdateUserForm();
	    }else if(command.equals("userticket")) {//마이페이지 항공권 구매내역 조회
			action = new UserTicket();
		}
			
			
			/*
			 * else if(command.equals("boardDiaryDelete.do")) {//-------------여기서부터 추가
			 * action = new BoardDiaryDeleteServlet(); }else
			 * if(command.equals("boardDiaryLikeInsert.do")) { action = new
			 * BoardDiaryLikeInsertServlet(); }else if(command.equals("boardDiaryList.do"))
			 * { action = new BoardDiaryListServlet(); }else
			 * if(command.equals("boardDiaryReplyDelete.do")) { action = new
			 * BoardDiaryReplyDeleteServlet(); }else
			 * if(command.equals("boardDiaryReplyUpdate.do")) { action = new
			 * BoardDiaryReplyUpdateServlet(); }else
			 * if(command.equals("boardDiaryReplyWriter.do")) { action = new
			 * BoardDiaryReplyWriterServlet(); }else
			 * if(command.equals("boardDiaryUpdate.do")) { action = new
			 * BoardDiaryUpdateServlet(); }else if(command.equals("boardDiaryView.do")) {
			 * action = new BoardDiaryViewServlet(); }else
			 * if(command.equals("boardDiaryWrite.do")) { action = new
			 * BoardDiaryWriteServlet(); }else if(command.equals("boardEventDelete.do")) {
			 * action = new BoardEventDeleteServlet(); }else
			 * if(command.equals("boardEventListGrid.do")) { action = new
			 * BoardEventListGridServlet(); }else if(command.equals("boardEventList.do")) {
			 * action = new BoardEventListServlet(); }else
			 * if(command.equals("boardEventUpdate.do")) { action = new
			 * BoardEventUpdateServlet(); }else if(command.equals("boardEventView.do")) {
			 * action = new BoardEventViewServlet(); }else
			 * if(command.equals("boardEventWrite.do")) { action = new
			 * BoardEventWriteServlet(); }else if(command.equals("login.do")) { action = new
			 * LoginServlet(); }else if(command.equals("logout.do")) { action = new
			 * LogoutServlet(); }
			 */
		
		return action;
	}
	


}
