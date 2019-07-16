package com.agos.testBoard.dto;

import java.time.*;
import java.util.*;

import com.agos.testBoard.command.*;

import lombok.*;

public class BoardDto {
	@Data
	public static class DtoForRead{
		private int board_num;
		private String board_title;
		private String board_content;
		private String board_file; 
		private LocalDateTime board_date;
		private int board_read_cnt;
		private List<FileCommand> fileCommand;
	}

}
