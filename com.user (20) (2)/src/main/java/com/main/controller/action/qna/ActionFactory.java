package com.main.controller.action.qna;

import com.main.controller.action.notice.ListNotice;
import com.main.controller.action.notice.LoginNotice;
import com.main.controller.action.notice.LogoutNotice;
import com.main.controller.action.notice.NoticeWriteForm;
import com.main.controller.action.notice.ReadNotice;
import com.main.controller.action.notice.SearchNotice;
import com.main.controller.action.notice.UpdateNotice;
import com.main.controller.action.notice.UpdateNoticeForm;
import com.main.controller.action.notice.WriteNotice;

public class ActionFactory {

	private static ActionFactory instance = new ActionFactory();

	private ActionFactory() {
	}

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;
		System.out.println("ActionFactory: " + command);
		if(command.equals("qna_write_form")) {
			action = new WriteQnaForm();
		}else if(command.equals("qna_list")) {
			action = new ListQna();
		}else if(command.equals("write_qna")) {
			action = new WriteQna();
		}else if(command.equals("select_qna")) {
			action = new SelectQna();
		}else if(command.equals("qna_update_form")) {
			action = new UpdateQnaForm();
		}else if(command.equals("qna_update")) {
			action = new UpdateQna();
		}else if(command.equals("qna_delete")) {
			action = new DeleteQna();
		}else if(command.equals("qna_reply_form")) {
			action = new ReplyQnaForm();
		}else if(command.equals("reply_qna")) {
			action = new ReplyQna();
		}else if(command.equals("search_qna")) {
			action = new SearchQna();
		}else if(command.equals("login_qna")) {
			action = new LoginQna();
		}else if(command.equals("logout_qna")) {
			action = new LogoutQna();
		}else if(command.equals("page_board")) {
			action = new PageBoard();
		}
		
		//여기부터 Notice(공지사항)
		if(command.equals("notice_list")) {
			action = new ListNotice();
		}else if(command.equals("login_notice")) {
			action = new LoginNotice();
		}else if(command.equals("logout_notice")) {
			action = new LogoutNotice();
		}else if(command.equals("notice_write_form")) {
			action = new NoticeWriteForm();
		}else if(command.equals("write_notice")) {
			action = new WriteNotice();
		}else if(command.equals("read_notice")) {
			action = new ReadNotice();
		}else if(command.equals("notice_delete")) {
			action = new DeleteNotice();
		}else if(command.equals("notice_update_form")) {
			action = new UpdateNoticeForm();
		}else if(command.equals("notice_update")) {
			action = new UpdateNotice();
		}else if(command.equals("search_notice")) {
			action = new SearchNotice();
		}
		
		return action;
	}
}