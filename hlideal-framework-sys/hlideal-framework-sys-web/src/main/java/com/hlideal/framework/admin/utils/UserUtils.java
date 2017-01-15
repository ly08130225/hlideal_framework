/**
 * Copyright 2014-2015 <a href="http://www.hlideal.com">exam</a> All rights reserved.
 */
package com.hlideal.framework.admin.utils;

import com.hlideal.common.session.SessionLocal;
import com.hlideal.common.session.SessionLocalManager;

public class UserUtils {

	/**
	 * 根据ID获取登录用户
	 * 
	 * @param
	 * @return 取不到返回null
	 */
	public static SessionLocal getSessionLocal() {
		return SessionLocalManager.getSessionLocal();
	}

	public static String getUserid() {
		try {
			return SessionLocalManager.getSessionLocal().getUserid();
		} catch (Exception e) {
			;
		}
		return "";
	}
	
	public static String getUsername() {
		try {
			return SessionLocalManager.getSessionLocal().getTruename();
		} catch (Exception e) {
			;
		}
		return "";
	}
}