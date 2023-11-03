package com.user.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
BOARDNUM     NOT NULL NUMBER(8)    
BOARDTITLE            VARCHAR2(50) 
BOARDCONTENT          CLOB         
BOARDWRITER           VARCHAR2(50) 
REGIDATE              DATE         
MODIFYDATE            DATE         
READCOUNT             NUMBER(8)  */

@Getter @Setter @ToString
public class BoardDiaryVO {
	
	private int boardNum;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String regiDate;
	private String modifyDate;
	private int readcount;
	private int replycount;
	private int likecount;
	
	
}
