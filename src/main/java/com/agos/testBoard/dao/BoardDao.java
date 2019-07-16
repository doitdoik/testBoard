package com.agos.testBoard.dao;

import java.util.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.agos.testBoard.command.*;

@Repository
public class BoardDao {
	@Autowired
	SqlSession sqlSession;
	
	// 글 갯수
	public int count(Map<String, Object> map) {
		return sqlSession.selectOne("cnt_board_num", map);
	}
	
	// 글 리스트
	public List<Map<String, Object>> selectTestList(Integer startRowNum, Integer endRowNum, Map<String, Object> map){
	/*	Map<String, Integer> map = new HashMap<>();*/
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		return sqlSession.selectList("sel_board_list", map);
	}
	
	// 글 상세내용
	public BoardCommand detail(Integer board_num) {
		System.out.println("boardDao-detail"+board_num);
		return sqlSession.selectOne("sel_board_detail", board_num);
	}
	// 글 상세내용2
		public BoardCommand detail2(Integer board_num) {
			return sqlSession.selectOne("sel_board_detail2", board_num);
		}
		
	// 글 쓰기
	public int write(BoardCommand board) {
		return sqlSession.insert("board_write", board);
	}
	// 글 쓰기2
		public int write2(BoardCommand board) {
			return sqlSession.insert("board_write2", board);
		}
	
	// 글 삭제
	public int delete(Integer board_num) {
		return sqlSession.delete("board_delete", board_num);
	}

	// 글 수정
	public int update(BoardCommand board) {
		return sqlSession.update("board_update", board);
	}
	
	// 조회수 가져오기
	public Integer ReadCnt(Integer board_num) {
		return sqlSession.selectOne("sel_board_read_cnt", board_num);
	}
	
	// 조회수 증가
	public Integer updateReadCnt(Integer board_num) {
		return sqlSession.update("up_board_read_cnt", board_num);
	}
	
	// 파일 업로드
	public int insertFile(FileCommand file) {
		return sqlSession.insert("insert_file", file);
	}

}
