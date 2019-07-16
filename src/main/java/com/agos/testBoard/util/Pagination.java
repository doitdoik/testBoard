package com.agos.testBoard.util;

import lombok.*;

@Data
@Builder
public class Pagination {
	
	private int startRowNum;
	private int endRowNum;
	private int prevPageNo;
	private int startPageNo;
	private int endPageNo;
	private int nextPageNo;
	private int pageNo;
	private int numOfPage;
}
