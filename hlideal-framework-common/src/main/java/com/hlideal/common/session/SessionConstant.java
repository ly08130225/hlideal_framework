package com.hlideal.common.session;

/**
 * 
 * 
 * @Filename Constant.java
 * 
 * @Description
 * 
 * @Version 1.0
 * 
 * @Author yhl
 * 
 * @Email yhailong@yiji.com
 * 
 * @History <li>Author: yhl</li> <li>Date: 2013-6-25</li> <li>Version: 1.0</li>
 * <li>Content: create</li> 常量
 */
public class SessionConstant {
	/**
	 * 超时时间， 若小于等于0就永远不会超时，单位毫秒
	 */
	public static long timeout = 1800000;

	/**
	 * 将session id与线程绑定
	 */
	private static ThreadLocal<String> sessionLocal = new ThreadLocal<String>();
	
	/**
	 * 保存session id
	 * @param sessionId
	 */
	public static void setSessionId(String sessionId) {
		sessionLocal.set(sessionId);
	}
	
	/**
	 * 获取session id
	 * @return
	 */
	public static String getSessionId() {
		return sessionLocal.get();
	}
}
