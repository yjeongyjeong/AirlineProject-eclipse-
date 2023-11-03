package com.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PageDTO {
	private int page; //현재 페이지 값을 나타내는 필드
	private int startNum; //현재 페이지에 조회 시작 넘버
	private int endNum; // 현재 페이지에 조회 종료 넘버
	private int startPage; //목록 출록시 하단부의 시작 페이지 수 
	private int endPage; //목록 출록시 하단부의 종료 페이지 수 
	private int maxPage; //전체 페이지의 수
	private int limitList = 10; //한페이지에 나타낼 수 있는 게시글 수(기본값으로 10)
	
	public int getPage() {
		return page;
	}
	public int getStartNum() {
		return startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public int getLimitList() {
		return limitList;
	}

	public void calcu(int page,int limit, int boardCount) {
		this.page = page;
		this.limitList = limit;
		this.startNum = (page-1)*limit+1;
		this.endNum = (page*limit)>boardCount? boardCount:page*limit;
		this.maxPage = (int) Math.ceil(boardCount*1.0/limit);
		this.startPage = (page/limit)*limit+1;
		this.endPage = startPage+(limit-1)>maxPage? maxPage:startPage+(limit-1);
	}
	
}
