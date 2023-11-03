package com.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*create table boardEventFile(
    fileNum number(8) primary key,
    boardNum number(8),
    oriFileName varchar2(500), 
    storedFileName varchar2(500),
    repImgYn varchar2(1),
    fileSize number(8),
    constraint fk_Evnet_file_boardNum foreign key(boardNum) references boardEvent(boardNum) on delete cascade
);*/

@Getter @Setter @ToString
public class BoardEventFileVO {
	private int fileNum;
	private int boardNum;
	private String oriFileName;
	private String savedFileName;
	private String extension;
	private String repImgYn;
	private int fileSize;
	private String fileOrder;
}
