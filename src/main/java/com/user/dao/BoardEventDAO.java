package com.user.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.user.vo.BoardEventFileVO;
import com.user.vo.BoardEventVO;
import com.user.vo.PageDTO;

public interface BoardEventDAO {
	
	int insertBoardEvent(BoardEventVO vo) throws Exception;
	
	int insertBoardEventFile(BoardEventFileVO vo); 

	int maxBoardNum();
	
	int maxBoardNum2();

	BoardEventVO selectOneByBoardNum(int boardNum);

	void updateReadcount(int parseInt);

	int deleteBoard(int boardNum);

	void updateBoard(BoardEventVO vo) throws Exception;
	
	PageDTO pageCalcu(HttpServletRequest request, int boardCount);	//페이지 계산

	List<BoardEventVO> searchByType(int startNum, int endNum, String searchType, String keyword);

	int searchCount(String searchType, String keyword);
	
	List<BoardEventFileVO> searchFileByBoardNum(int boardNum);
	
	int fileCount(int boardNum);
	
	int fileDelete(int boardNum);
	
	List<BoardEventFileVO> findRepImg();

	int updateRepImg(String ori_file_name, int boardNum);

}
