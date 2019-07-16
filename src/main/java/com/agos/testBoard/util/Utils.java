package com.agos.testBoard.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utils
{
  public static final int PUSH_TYPE_APP = 0;
  public static final int PUSH_TYPE_APP_RESULT = 1;
  public static final int PUSH_TYPE_COMMENT = 2;
  public static final int PUSH_TYPE_JOIN = 3;
  public static final int PUSH_TYPE_BOARD_COMMENT = 4;
  
  public Utils() {}
  
  public static String getRandomString(int length)
  {
    StringBuffer buffer = new StringBuffer();
    Random random = new Random();
    
    String code = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
    
    String[] chars = code.split(",");
    
    for (int i = 0; i < length; i++) {
      buffer.append(chars[random.nextInt(chars.length)]);
    }
    return buffer.toString();
  }
  
  public static String getCurrentTime()
  {
    Calendar cal = Calendar.getInstance();
    String year = String.valueOf(cal.get(1));
    String month = String.valueOf(cal.get(2) + 1);
    String day = String.valueOf(cal.get(5));
    String hour = String.valueOf(cal.get(11));
    String min = String.valueOf(cal.get(12));
    String sec = String.valueOf(cal.get(13));
    String millis = String.valueOf(cal.get(14));
    
    String time = year;
    time = time + ZeroString(month, 2);
    time = time + ZeroString(day, 2);
    time = time + ZeroString(hour, 2);
    time = time + ZeroString(min, 2);
    time = time + ZeroString(sec, 2);
    time = time + ZeroString(millis, 3);
    return time;
  }
  
  public static String ZeroString(String str, int len)
  {
    String result = "";
    if (str.length() < len) {
      for (int i = str.length(); i < len; i++) {
        result = result + "0";
      }
    }
    result = result + str;
    return result;
  }
  
  public static String setURLEncoding(String param)
  {
    try {
      if ((param != null) && (!param.equals(""))) {}
      return java.net.URLEncoder.encode(param, "UTF-8");

    }
    catch (Exception e)
    {
      e.printStackTrace(); }
    return null;
  }
  
  public static Map<String, String> getIpUA(HttpServletRequest req)
  {
    try
    {
      Map<String, String> map = new HashMap();
      map.put("ua", req.getHeader("User-Agent"));
      map.put("ip", req.getRemoteAddr());
      return map;
    } catch (Exception e) {
      e.printStackTrace(); }
    return null;
  }
  
  public static int getMemberIdx(HttpSession session)
  {
    try {
      int mem_idx = 0;
      if (session.getAttribute("SS_IDX") != null) {}
      return Integer.parseInt(session.getAttribute("SS_IDX").toString());
    }
    catch (Exception e)
    {
      e.printStackTrace(); }
    return 0;
  }
  
  public static String getMemberId(HttpSession session)
  {
    try {
      String mem_id = "";
      if (session.getAttribute("SS_ID") != null) {}
      return session.getAttribute("SS_ID").toString();
    }
    catch (Exception e)
    {
      e.printStackTrace(); }
    return null;
  }
  
  public static boolean isAdmin(HttpSession session)
  {
    try {
      boolean isAdmin = false;
      if ((session.getAttribute("SS_IDX") != null) && (session.getAttribute("SS_ID") != null) && (session.getAttribute("SS_NAME") != null)) {}
      return true;
    }
    catch (Exception e)
    {
      e.printStackTrace(); }
    return false;
  }
  

  public static String changeByte(int size)
  {
    String SIZE = "";
    
    if (size < 1024) {
      SIZE = size + "byte";
    }
    else if ((size > 1024) && (size < 1048576)) {
      SIZE = size / 1024 + "KB";
    } else {
      SIZE = size / 1048576 + "MB";
    }
    
    return SIZE;
  }
  

  public static String getCode()
  {
    StringBuffer buffer = new StringBuffer();
    
    buffer.append("A");
    
    Calendar cal = Calendar.getInstance();
    String year = String.valueOf(cal.get(1)).substring(2);
    String month = String.valueOf(cal.get(2) + 1);
    String day = String.valueOf(cal.get(5));
    int am_pm = cal.get(9);
    String hour = String.valueOf(cal.get(10));
    String min = String.valueOf(cal.get(12));
    String sec = String.valueOf(cal.get(13));
    String millis = String.valueOf(cal.get(14));
    
    buffer.append(getRandomString(3));
    
    buffer.append(ZeroString(Integer.toHexString(Integer.parseInt(year)).toUpperCase(), 2));
    
    buffer.append(Integer.toHexString(Integer.parseInt(month)).toUpperCase());
    
    buffer.append(ZeroString(Integer.toHexString(Integer.parseInt(day)).toUpperCase(), 2));
    
    buffer.append(am_pm == 0 ? "A" : "P");
    
    buffer.append(Integer.toHexString(Integer.parseInt(hour)).toUpperCase());
    
    buffer.append(ZeroString(Integer.toHexString(Integer.parseInt(min)).toUpperCase(), 2));
    
    buffer.append(ZeroString(Integer.toHexString(Integer.parseInt(sec)).toUpperCase(), 2));
    
    buffer.append(ZeroString(millis, 3));
    

    return buffer.toString();
  }
  
  
  public static String getShortCode()
  {
    StringBuffer buffer = new StringBuffer();
    
    buffer.append("CB_");
    
    Calendar cal = Calendar.getInstance();
    String year = String.valueOf(cal.get(1)).substring(2);
    String month = String.valueOf(cal.get(2) + 1);
    String day = String.valueOf(cal.get(5));
    int am_pm = cal.get(9);
    String hour = String.valueOf(cal.get(10));
    String min = String.valueOf(cal.get(12));
    String sec = String.valueOf(cal.get(13));
    String millis = String.valueOf(cal.get(14));
    
    buffer.append(getRandomString(4));
    
    //buffer.append(ZeroString(Integer.toHexString(Integer.parseInt(year)).toUpperCase(), 2));
    
    //buffer.append(Integer.toHexString(Integer.parseInt(month)).toUpperCase());
    
    //buffer.append(ZeroString(Integer.toHexString(Integer.parseInt(day)).toUpperCase(), 2));
    
    buffer.append(am_pm == 0 ? "A" : "P");
    //�߰�
    buffer.append(ZeroString(Integer.toHexString(Integer.parseInt(day)).toUpperCase(), 2));
    
    buffer.append(Integer.toHexString(Integer.parseInt(hour)).toUpperCase());
    
    buffer.append(ZeroString(Integer.toHexString(Integer.parseInt(min)).toUpperCase(), 2));
    
    buffer.append(ZeroString(Integer.toHexString(Integer.parseInt(sec)).toUpperCase(), 2));
    
    buffer.append(ZeroString(millis, 3));
    

    return buffer.toString();
  }
}