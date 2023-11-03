package com.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*create table flightDiaryReply(
    replyNum number(8) primary key,
    boardNum number(8),
    replyWriter varchar2(50),
    replyContent varchar2(1000),
    regiDate date,
    modifyDate date,
    constraint fk_boardNum foreign key(boardNum) references flightDiary(boardNum)
);*/

@Getter @Setter @ToString
public class BoardDiaryReplyVO {
	
	private int replyNum; 
	private int boardNum;
	private String replyWriter;
	private String replyContent;
	private String regiDate;
	private String modifyDate;
	
}
