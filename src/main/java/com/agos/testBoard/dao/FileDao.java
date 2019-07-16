package com.agos.testBoard.dao;

import java.util.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.agos.testBoard.command.*;

@Repository
public class FileDao {
	@Autowired
	private SqlSession sqlSession;
	// 파일 추가
	public Integer insert(FileCommand fileCommand) {
		return sqlSession.insert("insert_file", fileCommand);
	}
	
	// 파일 리스트
	public FileCommand selectFileList(String board_file){
		return sqlSession.selectOne("sel_file_list", board_file);
	}
	
	//파일 삭제
	public int delete(Integer file_num) {
		return sqlSession.delete("file_delete", file_num);
	}
	// 파일 갯수
	public int count(Map<String, Object> map) {
		return sqlSession.selectOne("cnt_file_num", map);
	}
}
