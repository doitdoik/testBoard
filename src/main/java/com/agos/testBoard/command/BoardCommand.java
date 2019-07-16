package com.agos.testBoard.command;

import java.time.*;
import java.util.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardCommand {
	private int board_num;
	private String board_title;
	private String board_content;
	private String board_file; 
	private LocalDateTime board_date;
	private int board_read_cnt;
	private List<FileCommand> fileCommand;
	
}

