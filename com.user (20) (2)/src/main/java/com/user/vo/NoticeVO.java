package com.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class NoticeVO {
	private String userid; //admin 고정
	private int boardnum; //seq
	private String boardsubject;
	private String boardcontent;
	private String regidate; //sysdate
	private String modifydate;  //sysdate
	private int views; //default 0
	private String origin; //파일이름
	private String uuid;
	private String extention; //확장자
	private String fileUrl;// 저장장소
	private String boardwriter; //추가
	private String boardfile; //추가

}
