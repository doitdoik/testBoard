package com.agos.testBoard.controller;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class FileController {
	// 파일 다운로드
		@RequestMapping("/boardDownloadData")
	    public void boardDownloadData(ModelMap model,
	    		HttpServletRequest request,
	    		HttpServletResponse response,
	    		String file_name,
	    		String file_org_name
	  		  	) throws Exception {

	    	String dFile = file_name;
	    	
	    	// 로컬용 저장소
	    	String upDir = "D:\\file";
	    	// 서버용 저장소
	    	//String upDir = "C:\\work\\apache-tomcat-8.5.34\\webapps\\testBoard\\file";
	    	
	    	String path = upDir + File.separator + dFile;
	    	/*System.out.println(dFile);
	    	System.out.println(upDir);
	    	System.out.println(path);*/
	    	File file = new File(path);
	    	/*System.out.println(file);*/
	    	String userAgent = request.getHeader("User-Agent");
	    	boolean ie = (userAgent.indexOf("MSIE") > -1) || (userAgent.indexOf("rv:11") > -1);
	    	String fileName = null;
	      
	    	if (ie) {
	    		fileName = URLEncoder.encode(file_org_name, "utf-8");
	    	} else {
	    		fileName = new String(file_org_name.getBytes("utf-8"), "iso-8859-1");
	    	}

	    	response.setContentType("application/octet-stream");
	    	response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\";");
	      
	    	FileInputStream fis = new FileInputStream(file);
	    	BufferedInputStream bis = new BufferedInputStream(fis);
	      	ServletOutputStream so = response.getOutputStream();
	      	BufferedOutputStream bos = new BufferedOutputStream(so);
	      
	      	byte[] data = new byte[1024];
	      	int input = 0;
	      	while ((input = bis.read(data)) != -1) {
	      		bos.write(data, 0, input);
	      		bos.flush();
	    	}
	      
	      	if (bos != null) bos.close();
	      	if (bis != null) bis.close();
	      	if (so != null) so.close();
	      	if (fis != null) fis.close();
	    }
}
