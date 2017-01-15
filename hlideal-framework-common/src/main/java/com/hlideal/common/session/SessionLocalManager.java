package com.hlideal.common.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionLocalManager {
	static Logger logger = LoggerFactory.getLogger(SessionLocalManager.class);
	/**
	 * session key
	 */
	private final static String SESSION_KEY = "session_key";
	
	/**
	 * 设置当前session局部数据
	 * @param local
	 */
	public static void setSessionLocal(SessionLocal local) {
		Session.setAttribute(SESSION_KEY, local);
	}
	
	/**
	 * 获取session局部数据
	 * @return
	 */
	public static SessionLocal getSessionLocal() {
		try {
			return (SessionLocal) Session.getAttribute(SESSION_KEY);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return null;
		}
	}
	
	/**
	 * 销毁session
	 */
	public static void destroy() {
		Session.destroy();
	}
	
}
