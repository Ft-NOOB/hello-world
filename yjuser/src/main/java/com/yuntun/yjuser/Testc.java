package com.yuntun.yjuser;

public class Testc {
	
    public static String getErrorLine(){  
    	StringBuilder locations = new StringBuilder();  
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];  
  
        locations.append("File: "+stackTraceElement.getFileName()+" Line: "+stackTraceElement.getLineNumber()+" Method: "+stackTraceElement.getMethodName() +"\n"); 
      
        return locations.toString();  
    }  
    
}
