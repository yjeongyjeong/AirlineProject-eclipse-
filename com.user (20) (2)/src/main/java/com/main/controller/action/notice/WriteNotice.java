package com.main.controller.action.notice;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main.controller.action.qna.Action;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.user.dao.NoticeDAO;
import com.user.vo.NoticeFileVO;
import com.user.vo.NoticeVO;


@MultipartConfig(
	    location = "/noticeFileUpload",  // 업로드된 파일을 임시로 저장할 디렉토리 경로
	    maxFileSize = 1024 * 1024 * 50,  // 최대 파일 크기 (50MB)
	    maxRequestSize = 1024 * 1024 * 100,  // 요청 전체 크기 (100MB)
	    fileSizeThreshold = 1024 * 1024 * 10  // 임시 파일로 저장되는 파일 크기 (10MB)
	)
	public class WriteNotice implements Action {

	    @Override
	    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        NoticeVO vo = new NoticeVO();
	        NoticeFileVO fileVo = new NoticeFileVO();

	        String admin = "관리자";
	        NoticeDAO nDao = NoticeDAO.getInstance();

	        // 파일 업로드 경로 설정 (본인의 웹 애플리케이션 경로에 맞게 수정)
	        String uploadPath = request.getServletContext().getRealPath("/noticeFileUpload");
	        System.out.println(uploadPath);
	        // 업로드한 파일의 이름을 랜덤으로 생성
	        String fileName = UUID.randomUUID().toString();
	        
	        // MultipartRequest를 사용하여 파일 업로드 처리
	        MultipartRequest multi = new MultipartRequest(
	            request, 
	            uploadPath,
	            1024 * 1024 * 50,  // 최대 파일 크기 (50MB)
	            "UTF-8",
	            new DefaultFileRenamePolicy()  // 파일명 중복 처리
	        );

	        // 파일 업로드 처리
	        String originalFileName = multi.getOriginalFileName("fileurl");
	        String fileSystemName = multi.getFilesystemName("fileurl");

	        // 업로드된 파일 정보를 데이터베이스에 저장
	        fileVo.setOrigin(originalFileName);
	        fileVo.setUuid(fileName);
	        fileVo.setFileurl(fileSystemName);

	        vo.setBoardsubject(multi.getParameter("boardsubject"));
	        vo.setBoardcontent(multi.getParameter("boardcontent"));
	        vo.setBoardwriter(admin);
	        vo.setRegidate(multi.getParameter("regidate"));

	        // 데이터베이스에 게시물 정보 및 파일 정보 저장
	        int result = nDao.fileWriteNotice(vo, fileVo);

	        if (result == 1) {
	            response.sendRedirect("BoardServlet?command=notice_list");
	        } else {
	            response.sendRedirect("BoardServlet?command=notice_list");
	        }
	    }
	}

//public class WriteNotice implements Action {
//
//	@Override
//	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		NoticeVO vo = new NoticeVO();
//		
//		String admin="관리자";
//		System.out.println("공지사항 게시판 : " + vo);
//		NoticeDAO nDao = NoticeDAO.getInstance();
//		vo.setBoardsubject(request.getParameter("boardsubject"));
//		vo.setBoardcontent(request.getParameter("boardcontent"));
//		vo.setBoardwriter(admin);
//		vo.setRegidate(request.getParameter("regidate"));
//		
//		
//		int result = nDao.writeNotice(vo);
//		if (result == 1) {
//			response.sendRedirect("BoardServlet?command=notice_list");
//		} else {
//			response.sendRedirect("BoardServlet?command=notice_list");
//		}
//		
//
//	}
//
//}
