package com.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*create table boardEvent(
    boardNum number(8) primary key,
    boardTitle varchar2(50) not null,
    boardContent clob,
    startDate date default sysdate,
    endDate date default sysdate+365,
    regiDate date default sysdate,
    modifydate date default '1900-01-01',
    readcount number(8) default 0
    repImg
);*/

@Getter @Setter @ToString
public class BoardEventVO {
	private int boardNum;
	private String boardTitle;
	private String boardContent;
	private String startDate;
	private String endDate;
	private String regiDate;
	private String modifyDate;
	private int readCount;
	private String repImg;
}
