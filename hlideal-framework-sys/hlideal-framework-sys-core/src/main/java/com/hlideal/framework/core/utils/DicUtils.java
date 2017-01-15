package com.hlideal.framework.core.utils;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;


//系统字典表,常变量
public class DicUtils {
	
	//常用变量
	public static final String PUB_STRING_00 = "0";
	public static final String PUB_STRING_01 = "1";
	public static final String PUB_STRING_02 = "2";
	
	public static final Integer PUB_INTEGER_00 = 0;
	public static final Integer PUB_INTEGER_01 = 1;
	public static final Integer PUB_INTEGER_02 = 2;
	
	public static final String PUB_DELETEFLAG_01 = "0";
	public static final String PUB_DELETEFLAG_02 = "1";
	

	public static boolean checkhaskey(HashMap<String, Object> content, String key){
		if(content!= null && content.size() > 0 && !StringUtils.isEmpty(key) && content.containsKey(key))
			return true;
		return false;
	}
}
