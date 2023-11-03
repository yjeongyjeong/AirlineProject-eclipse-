package com.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaVO {

//	boardnum number(8) primary key,
//	boardsubject varchar(50) not null,
//	boardcontent varchar(2000),
//	boardwriter varchar(30),
//	regidate date default sysdate,
//	modifydate date default sysdate,
//	boardreref number(8,0),
//	boardrelevel number(2,0),
//	boardreseq number(6,0),
//	readcount number(6,0)
	
	private int boardnum;
	private String boardsubject;
	private String boardcontent;
	private String boardwriter;
	private String regidate;
	private String modifydate;
	private int boardreref;
	private int boardrelevel;
	private int boardreseq;
	private int readcount;
	private String replycontent;
	private String replydate;
	
	private int maxpage;
	private int startpage;
	private int endpage;
	private int listcount;
	private int page;
	private int limit;
	
}
