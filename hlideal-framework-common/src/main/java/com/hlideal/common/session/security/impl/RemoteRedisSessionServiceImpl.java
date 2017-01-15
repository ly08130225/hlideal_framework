// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RemoteSessionServiceImpl.java

package com.hlideal.common.session.security.impl;

import com.hlideal.common.base.utils.JedisUtils;
import com.hlideal.common.session.security.RemoteSessionService;
import com.hlideal.common.session.security.RemoteSessionLocal;

public class RemoteRedisSessionServiceImpl implements RemoteSessionService {

	public RemoteRedisSessionServiceImpl() {
	}

	@SuppressWarnings("unchecked")
	public void setAttribute(String id, String key, byte bytes[], long timeout) {
		RemoteSessionLocal remoteSessionLocal = getSessionLocal(id, timeout);
		remoteSessionLocal.getSession().put(key, bytes);
		remoteSessionLocal.setLastAccessTime(System.currentTimeMillis());
		JedisUtils.setObject(id, remoteSessionLocal, (int) timeout);

	}

	public void removeAttribute(String id, String key, long timeout) {
		RemoteSessionLocal remoteSessionLocal = getSessionLocal(id, timeout);
		if (remoteSessionLocal != null) {
			remoteSessionLocal.getSession().remove(key);
			remoteSessionLocal.setLastAccessTime(System.currentTimeMillis());
			JedisUtils.setObject(id, remoteSessionLocal, (int) timeout);
		}
	}

	public byte[] getAttribute(String id, String key, long timeout) {
		try {
			RemoteSessionLocal remoteSessionLocal = getSessionLocal(id, timeout);
			if (remoteSessionLocal != null) {
				if (remoteSessionLocal.isTimeout() == false) {
					remoteSessionLocal.setLastAccessTime(System.currentTimeMillis());
					JedisUtils.setObject(id, remoteSessionLocal, (int) timeout);
					return (byte[]) remoteSessionLocal.getSession().get(key);
				} else {
					destroy(id);
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public void destroy(String id) {
		JedisUtils.del(id);
	}

	private RemoteSessionLocal getSessionLocal(String id, long timeout) {
		RemoteSessionLocal sessionLocal = null;
		try {
			if (JedisUtils.getObject(id) != null) {
				sessionLocal = (RemoteSessionLocal) JedisUtils.getObject(id);
				sessionLocal.setTimeout(timeout);
			}
		} catch (Exception e) {
			sessionLocal = null;
		}
		if (sessionLocal == null) {
			sessionLocal = new RemoteSessionLocal(timeout);
			JedisUtils.setObject(id, sessionLocal, (int) timeout);
		}
		return sessionLocal;
	}

}
