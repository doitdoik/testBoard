package com.agos.testBoard.command;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileCommand {
	private int file_num;
	private String file_name;
	private String file_org_name;
	private String file_group;
	private Long file_size;
	
}
