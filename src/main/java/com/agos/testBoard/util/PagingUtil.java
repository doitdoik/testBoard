package com.agos.testBoard.util;

public class PagingUtil {
	
	private static int numOfBoardPerPage = 5;
	private static int numOfPagePerBlock = 4;
	
	public static Pagination getPagination(int pageNo, int count) {
		
		int startRowNum;
		int endRowNum;
		int prevPageNo;
		int startPageNo;
		int endPageNo;
		int nextPageNo;
		int numOfPage = count/numOfBoardPerPage + 1;
		if(pageNo<=0 || pageNo>numOfPage)
			pageNo =1;
		int blockNo = (pageNo-1)/numOfPagePerBlock+1;
		
		prevPageNo = (blockNo-1)*numOfPagePerBlock; 
		startPageNo = prevPageNo +1;
		endPageNo = prevPageNo + numOfPagePerBlock;
		nextPageNo = endPageNo +1;
		if(endPageNo>=numOfPage) {
			endPageNo=numOfPage;
			nextPageNo=0;
		}
		endRowNum = pageNo * numOfBoardPerPage;
		startRowNum = endRowNum - numOfBoardPerPage;
		if(endRowNum>count) {
			endRowNum=count;
		}
		return Pagination.builder().startRowNum(startRowNum).endRowNum(endRowNum)
				.startPageNo(startPageNo).prevPageNo(prevPageNo).
				nextPageNo(nextPageNo).endPageNo(endPageNo).pageNo(pageNo).numOfPage(numOfPage).build();
	
	}
}
