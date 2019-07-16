package com.agos.testBoard.service;

import java.util.*;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.agos.testBoard.command.*;
import com.agos.testBoard.dao.*;
import com.agos.testBoard.dto.*;
import com.agos.testBoard.util.*;

@Service
public class BoardService {
	// -----------------------------------------------테스트
	@Autowired
	private BoardDao bDao;
	@Autowired
	private FileDao fDao;
	@Autowired
	private ModelMapper modelMapper;


	// 페이징
	public List<Map<String, Object>> findByList(Map<String, Object> map, Integer pageNo) {
		// 글개수 읽기
		int count = bDao.count(map);
		Pagination pagination = PagingUtil.getPagination(pageNo, count);
		// 페이징
		List<Map<String, Object>> result = bDao.selectTestList(pagination.getStartRowNum(),pagination.getEndRowNum(),map);
		return result;
	}
	// 글갯수
	public Integer count(Map<String, Object> map) {
		return bDao.count(map);
	}
	

	// 글쓰기
	public Integer write(BoardCommand board) {
		
		return bDao.write(board);
	}
	
	// 글쓰기2
		public Integer write2(BoardCommand board) {
			
			return bDao.write2(board);
		}
	

	// 글읽기
	public BoardDto.DtoForRead detail(Integer board_num) {
		
		BoardCommand result = bDao.detail(board_num);
		System.out.println("file - "+result.getBoard_file());
		System.out.println("date - "+result.getBoard_date());
		/*String file_group = result.getBoard_file();*/
		BoardDto.DtoForRead detail = modelMapper.map(result, BoardDto.DtoForRead.class);
		/*System.out.println(fDao.selectFileList(file_group)+"boardService Line61");*/
		bDao.updateReadCnt(board_num);

		detail.setBoard_read_cnt(detail.getBoard_read_cnt()+1);	
		return detail;
	}
	// 글읽기2
		public BoardCommand detail2(Integer board_num) {
			return bDao.detail2(board_num);
		}
	
	// 글삭제
	public Integer delete(Integer board_num) {
		return bDao.delete(board_num);
	}
	
	// 글수정
	public Integer update(BoardCommand board) {
		return bDao.update(board);
	}

}