package com.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*create table flightDiaryLike(
    likeNum number(4),      --필요한가?
    boardNum number(8),
    userId varchar2(30),
    likeDate date default sysdate
);*/

@Getter @Setter @ToString
public class BoardDiaryLikeVO {
	private int likeNum;
	private int boardNum;
	private String userId;
	private String likeDate;
}
