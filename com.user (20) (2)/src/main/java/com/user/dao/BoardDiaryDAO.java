package com.user.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.user.vo.BoardDiaryReplyVO;
import com.user.vo.BoardDiaryVO;
import com.user.vo.PageDTO;

public interface BoardDiaryDAO {

	void insertBoardDiary(BoardDiaryVO vo) throws Exception;
	
	int maxBoardNum();
	
	BoardDiaryVO selectOneByBoardNum(int boardNum);

	void updateReadcount(int parseInt);

	int deleteBoard(int boardNum);

	void updateBoard(BoardDiaryVO vo) throws Exception;
	
	PageDTO pageCalcu(HttpServletRequest request, int boardCount);	//페이지 계산

	List<BoardDiaryVO> searchByType(int startNum, int endNum, String searchType, String keyword, String sort);

	int searchCount(String searchType, String keyword);

	void insertReply(BoardDiaryReplyVO vo);
	
	int maxReplyNum(int boardNum);
	
	int replyCount(int boardNum);

	List<BoardDiaryReplyVO> selectAllReply(int startNum, int endNum, int boardNum);
	
	void updateReply(BoardDiaryReplyVO vo);
	
	void deleteReply(int replyNum, int boardNum);
	
	BoardDiaryReplyVO selectOneReply(int replyNum, int boardNum);
	
	void updateReplyCount(int boardNum);
	
	int insertLikecount(int boardNum, String userId);
	
	int deleteLikecount(int boardNum, String userId);
	
	int checkLike(int boardNum, String userId);
	
	int likecount(int boardNum);	
	
	int updateLikecount(int boardNum);
	
}
