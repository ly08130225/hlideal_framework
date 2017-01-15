package com.hlideal.common.session;

import com.hlideal.common.session.security.impl.RemoteSessionServiceImpl;
import com.hlideal.common.session.security.RemoteSessionService;


public class Session {
	/**
	 * 远程session服务
	 */
	private static RemoteSessionService remoteSessionService = null;
	
	/**
	 * 获取远程session服务
	 * @return
	 */
	private static RemoteSessionService getRemoteSession() {
		if (remoteSessionService == null) {
			synchronized (Session.class) {
				//remoteSessionService = RmcServiceFactory.getInstance().getRmcService(Constant.host, Constant.port, Constant.name, RemoteSessionService.class);
				//remoteSessionService = RmcServiceFactory.getInstance().getRmcService("127.0.0.1", 8082, "remoteSessionService", RemoteSessionService.class);
				remoteSessionService = new RemoteSessionServiceImpl();
			}
		}
		return remoteSessionService;
	}
	
	/**
	 * 获取远程session服务 redis
	 * @return
	 */
	/*
	private static RemoteSessionService getRemoteSession() {
		if (remoteSessionService == null) {
			synchronized (Session.class) {
				//remoteSessionService = RmcServiceFactory.getInstance().getRmcService(Constant.host, Constant.port, Constant.name, RemoteSessionService.class);
				//remoteSessionService = RmcServiceFactory.getInstance().getRmcService("127.0.0.1", 8082, "remoteSessionService", RemoteSessionService.class);
				remoteSessionService = new RemoteRedisSessionServiceImpl();
			}
		}
		return remoteSessionService;
	}
	*/
	/**
	 * 将数据存入session
	 * @param key
	 * @param value
	 */
	public static void setAttribute(String key, Object value) {
		getRemoteSession().setAttribute(SessionConstant.getSessionId(), key,
			ByteObject.object2Bytes(value), SessionConstant.timeout);
	}
	
	/**
	 * 从session中获取数据
	 * @param key
	 * @return
	 */
	public static Object getAttribute(String key) {
		byte[] bytes = getRemoteSession().getAttribute(SessionConstant.getSessionId(), key, SessionConstant.timeout);
		return ByteObject.bytes2Object(bytes);
	}
	
	/**
	 * 从session中移除数据
	 * @param key
	 */
	public static void removeAttribute(String key) {
		getRemoteSession().removeAttribute(SessionConstant.getSessionId(), key, SessionConstant.timeout);
	}
	
	/**
	 * 销毁
	 */
	public static void destroy() {
		getRemoteSession().destroy(SessionConstant.getSessionId());
	}
	
	/**
	 * 获取session id
	 * @return
	 */
	public static String getId() {
		return SessionConstant.getSessionId();
	}
	
}
