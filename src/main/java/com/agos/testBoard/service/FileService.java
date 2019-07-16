package com.agos.testBoard.service;

import java.io.*;
import java.text.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.multipart.*;

import com.agos.testBoard.command.*;
import com.agos.testBoard.dao.*;

@Service
public class FileService extends FileCommand {
	
	@Autowired
	private FileDao fDao;
	// 파일업로드
	@Value("D:\\file\\upload")
	private String fileUpFolder;
	
	// 파일 단일 업로드
	// 컨트롤러에서 다중업로드로 변경
	/*@Transactional
	public void insertFile(BoardCommand board, FileCommand file, MultipartFile multipartFile)
			throws IllegalStateException, IOException {
		// 확장자
		if(multipartFile.getSize()>0) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmSS");
			String time = dateFormat.format(cal.getTime());
			
			int lastIndexOfDot = multipartFile.getOriginalFilename().lastIndexOf('.');
			String extension = multipartFile.getOriginalFilename().substring(lastIndexOfDot + 1);
			
			File files = new File(fileUpFolder, time + "." + extension);
			File orgFiles = new File(fileUpFolder, multipartFile.getOriginalFilename());
			multipartFile.transferTo(files);
			file.setFile_name(files.getName());
			file.setFile_name(files.getName());
			file.setFile_org_name(orgFiles.getName());
			file.setFile_size(multipartFile.getSize());
			System.out.println("FileService Line44 ="+board.getBoard_file());
			file.setFile_group(board.getBoard_file());
			System.out.println(board.getBoard_file());
			file.setFile_group(board.getBoard_file());
			board.setBoard_file(files.getName());
		fDao.insert(file);
		}
		if(multipartFile.getSize()==0) {
			file.setFile_name(null);
			file.setFile_org_name(null);
			board.setBoard_file(null);
			fDao.insert(file);
		}
	}*/
	
	// 파일 리스트
	public FileCommand fileList(String board_file){
		
		
		System.out.println(fDao.selectFileList(board_file)+"fileService");
		return fDao.selectFileList(board_file);
	}	
	
	// 파일 삭제
	public Integer delete(Integer file_num) {
		return fDao.delete(file_num);
	}

	
}
