package com.agos.testBoard.controller;

import java.io.*;
import java.text.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import com.agos.testBoard.command.*;
import com.agos.testBoard.dao.*;
import com.agos.testBoard.dto.BoardDto.*;
import com.agos.testBoard.service.*;
import com.agos.testBoard.util.*;



@Controller
public class BoardController {
	// -----------------------------------------------테스트
	@Autowired
	private BoardService boardService;
	@Autowired
	private FileService fileService;
	@Autowired
	private FileDao fDao;

	//검색
	// 게시판 리스트
	@RequestMapping(value = "/boardList")
	public String boardList(@RequestParam(defaultValue = "1") Integer pageNo,
							@RequestParam Map<String, Object> map,
							@RequestParam(defaultValue="board_title") String serchType,
							@RequestParam(defaultValue="") String keyWord,
							Model model){
		int count = boardService.count(map);
		Pagination p = PagingUtil.getPagination(pageNo, count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = boardService.findByList(map,pageNo);
		
		
		map.put("pagination", p);
		model.addAttribute("cnt", count);
		model.addAttribute("list", list);
		model.addAttribute("map", map);

	
		/*System.out.println(boardService.count(map)+"boardController:count@@@@@@@@@@@@@@@@@@@@");*/

		return "test/home";
	}
	// 다중업로드 + 글쓰기로 구현
	// 글쓰기(post) + 파일업로드
	/*@RequestMapping(value = "/boardWrite", method = RequestMethod.POST)
	public String boardWrite(BoardCommand board, MultipartFile multipartFile, FileCommand file)
			throws IllegalStateException, IOException {
		if(multipartFile.getSize()>0) {
		fileService.insertFile(board, file, multipartFile);
		boardService.write(board);
		}
		else {
		boardService.write2(board);
		}
		return "redirect:/boardList";
	}*/
	// 다중업로드 + 글쓰기
	@RequestMapping(value = "boardWrite")
	public String requestupload2(MultipartHttpServletRequest mtfRequest, BoardCommand board, FileCommand fileCommand) {
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
		String src = mtfRequest.getParameter("src");
		System.out.println("src value : " + src);
		
		//로컬용 저장소
		String path = "D:\\file\\";
		//서버용 저장소
		//String path = "C:\\work\\apache-tomcat-8.5.34\\webapps\\testBoard\\file";
		
		for (MultipartFile mf : fileList) {
			
			String file_org_name = mf.getOriginalFilename(); // 원본 파일 명
			long file_size = mf.getSize(); // 파일 사이즈

			System.out.println("file_org_name : " + file_org_name);
			System.out.println("fileSize : " + file_size);
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmSS");
			String time = dateFormat.format(cal.getTime());
			
			int lastIndexOfDot = mf.getOriginalFilename().lastIndexOf('.');
			String extension = mf.getOriginalFilename().substring(lastIndexOfDot + 1);
			
			File files = new File(path, time + "." + extension);
			File orgFiles = new File(path, mf.getOriginalFilename());
			
			/*String safeFile = path + System.currentTimeMillis() + file_org_name;*/
			
			fileCommand.setFile_name(files.getName());
			fileCommand.setFile_org_name(orgFiles.getName());
			fileCommand.setFile_size(mf.getSize());
			fDao.insert(fileCommand);
			try {
				mf.transferTo(new File(path, time + "." + extension));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
			boardService.write(board);

		return "redirect:/boardList";
	}
	// 글쓰기(get)
	@RequestMapping(value = "/boardWrite", method = RequestMethod.GET)
	public String boardWrite() {
		return "test/write";
	}

	// 글읽기
	@RequestMapping(value = "/boardDetail")
	public String boardDetail(Model model, Integer board_num, String board_file) {
		System.out.println("boardDetail Controller   " + board_file);
		System.out.println("boardDetail Controller   " + board_num);
		/*if(board_file==null) {
			boardService.detail2(board_num);
		}
		else if(board_file!=null) {*/
		DtoForRead bDTO = boardService.detail(board_num);
		/*FileCommand fDTO = fileService.fileList(board_file);*/
		/*System.out.println("boardDetail List" + board_file);*/
		//boardService.detailFileList(boDTO.getBoard_file());
		/*model.addAttribute("files", fDTO);*/
		model.addAttribute("detail", bDTO);
		/*System.out.println(fDTO+"boardController");*/
		/*}*/
		return "test/detail";
		
	}

	// 글삭제
	@RequestMapping(value = "/boardDelete", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(Integer board_num/*,Integer file_num, Model model*/) {
		/*model.addAttribute(boardService.delete(board_num));
		model.addAttribute(fileService.delete(file_num));*/
		return ResponseEntity.ok(boardService.delete(board_num));
	}
	
	
	
	// 글수정(get)
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.GET)
	public String boardUpdate(Model model, @RequestParam("board_num") Integer board_num) {
		model.addAttribute("detail", boardService.detail(board_num));
		return "test/update";
	}

	// 글수정(post)
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.POST)
	public String boardUpdate(BoardCommand board) {
		boardService.update(board);
		return "redirect:/boardDetail?board_num=" + board.getBoard_num();
	}
	
}
