package com.feipinjia.util;

public class StringUtils {
	
	public static String getUsername(String str){
		if(str==null||str==""){
			return null;
		}
		int position=str.indexOf("@");
		return str.substring(0, position);
	}

}
