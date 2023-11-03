package com.user.controller.action.boardDiary;

import java.io.File; 
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.user.dao.BoardEventDAO;
import com.user.dao.BoardEventDAOImpl;
import com.user.vo.BoardEventFileVO;
import com.user.vo.BoardEventVO;

@WebServlet("/boardEventUpdate.do")
public class BoardEventUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardEventDAO dao = new BoardEventDAOImpl();
		
		String url = "/boardEvent/boardEventUpdate.jsp";
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		request.setAttribute("boardNum", boardNum);
		
		BoardEventVO vo = dao.selectOneByBoardNum(boardNum);
		request.setAttribute("board", vo);
		
		int count = dao.fileCount(boardNum);
		request.setAttribute("imgCount", count);
		
		List<BoardEventFileVO> list = dao.searchFileByBoardNum(boardNum);
		request.setAttribute("fileList", list);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardEventDAO dao = new BoardEventDAOImpl();
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");	//타입 설정
		PrintWriter out = response.getWriter();
		
		String savePath = "upload";
		int uploadFileSizeLimit = 5*1024*1024;
		String encType = "utf-8";
		
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		
		System.out.println("uploadFilePath : " + uploadFilePath);
		
		try {
			MultipartRequest multi = new MultipartRequest(request,
					uploadFilePath,
					uploadFileSizeLimit,
					encType,
					new DefaultFileRenamePolicy());
			Enumeration files = multi.getFileNames();
			
			System.out.println("startdate : "+multi.getParameter("startDate"));
			System.out.println(multi.getParameter("endDate"));
			System.out.println("title : " + multi.getParameter("boardTitle"));
			System.out.println("boardNum : " + multi.getParameter("boardNum"));
			
			int boardNum = Integer.parseInt(multi.getParameter("boardNum"));
			dao.fileDelete(boardNum);

			//int bNum = dao.maxBoardNum()+1;
			
			BoardEventVO vo = new BoardEventVO();
			
			vo.setBoardNum(boardNum);
			vo.setBoardTitle(multi.getParameter("boardTitle"));
			vo.setBoardContent(multi.getParameter("boardContent"));		          
			vo.setStartDate(multi.getParameter("startDate"));
			vo.setEndDate(multi.getParameter("endDate"));
			
			dao.updateBoard(vo);
			
			String file_name = "";
			String extension = "";
			String fileSize = "";
			String ori_file_name = "";
			String savedFileName = "";
			String fileUploadFullUrl = "";
			String type = "";
			File fileObj = null;

			while (files.hasMoreElements()) {
				String file = (String) files.nextElement();
				System.out.println("file : " + file);
				file_name = multi.getFilesystemName(file); //서버상 파일이름
				System.out.println("file name : " + file_name);
				
				if(file_name!=null) {
					type = multi.getContentType(file);                   //콘텐트타입    
		            fileObj = multi.getFile(file);                             //파일객체
//		            originFileName = multi.getOriginalFileName(file);           //초기 파일명
		            extension = file_name.substring(file_name.lastIndexOf(".")); //파일 확장자	 //+1
		            fileSize = String.valueOf(fileObj.length());                    // 파일크기
		            ori_file_name = multi.getOriginalFileName(file); //실제 파일명, 다운로드 시에는 원본이름으로 다운받게 해야함.
		            
		            UUID uuid = UUID.randomUUID();
		            savedFileName = uuid.toString() + extension;
		            fileUploadFullUrl = uploadFilePath + "/" + savedFileName;         

		            
		          	            
		            BoardEventFileVO fvo = new BoardEventFileVO();
		            fvo.setBoardNum(boardNum);
		            fvo.setExtension(extension);
		            fvo.setFileSize(Integer.parseInt(fileSize));
		            fvo.setOriFileName(ori_file_name);
		            fvo.setSavedFileName(savedFileName);
		            if(file.equals("uploadFile01")) {
		            	fvo.setRepImgYn("Y");
		            } else {
		            	fvo.setRepImgYn("N");
		            }
		            fvo.setFileOrder(file);
		            
		            dao.insertBoardEventFile(fvo);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("boardEventList.do");
	}

}
