// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RemoteSessionServiceImpl.java

package com.hlideal.common.session.security.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hlideal.common.session.security.RemoteSessionService;
import com.hlideal.common.session.security.RemoteSessionLocal;

public class RemoteSessionServiceImpl implements RemoteSessionService {

	protected static Map sessionLocals = new ConcurrentHashMap();

	public RemoteSessionServiceImpl() {
	}

	public void setAttribute(String id, String key, byte bytes[], long timeout) {
		RemoteSessionLocal remoteSessionLocal = getSessionLocal(id, timeout);
		remoteSessionLocal.getSession().put(key, bytes);
		remoteSessionLocal.setLastAccessTime(System.currentTimeMillis());
	}

	public void removeAttribute(String id, String key, long timeout) {
		RemoteSessionLocal remoteSessionLocal = (RemoteSessionLocal) sessionLocals.get(id);
		if (remoteSessionLocal != null) {
			remoteSessionLocal.getSession().remove(key);
			remoteSessionLocal.setLastAccessTime(System.currentTimeMillis());
		}
	}

	public byte[] getAttribute(String id, String key, long timeout) {
		try {
			RemoteSessionLocal remoteSessionLocal = (RemoteSessionLocal) sessionLocals.get(id);
			if (remoteSessionLocal != null) {
				remoteSessionLocal.setLastAccessTime(System.currentTimeMillis());
				return (byte[]) remoteSessionLocal.getSession().get(key);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public void destroy(String id) {
		sessionLocals.remove(id);
	}

	private RemoteSessionLocal getSessionLocal(String id, long timeout) {
		RemoteSessionLocal sessionLocal = null;
		try {
			sessionLocal = (RemoteSessionLocal) sessionLocals.get(id);
		} catch (Exception e) {
			sessionLocal = null;
		}
		if (sessionLocal == null) {
			sessionLocal = new RemoteSessionLocal(timeout);
			sessionLocals.put(id, sessionLocal);
		}
		return sessionLocal;
	}

}
