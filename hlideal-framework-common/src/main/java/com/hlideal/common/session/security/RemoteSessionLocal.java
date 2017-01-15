// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RemoteSessionLocal.java

package com.hlideal.common.session.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RemoteSessionLocal
	implements Serializable
{

	private static final long serialVersionUID = 1L;
	private long timeout;
	private long createTime;
	private long lastAccessTime;
	private Map session;

	public RemoteSessionLocal(long timeout)
	{
		this.timeout = 0L;
		createTime = 0L;
		lastAccessTime = 0L;
		session = null;
		this.timeout = timeout;
		createTime = System.currentTimeMillis();
		lastAccessTime = createTime;
		session = new HashMap();
	}

	public long getTimeout()
	{
		return timeout;
	}

	public void setTimeout(long timeout)
	{
		this.timeout = timeout;
	}

	public long getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(long createTime)
	{
		this.createTime = createTime;
	}

	public long getLastAccessTime()
	{
		return lastAccessTime;
	}

	public void setLastAccessTime(long lastAccessTime)
	{
		this.lastAccessTime = lastAccessTime;
	}

	public Map getSession()
	{
		return session;
	}

	public void setSession(Map session)
	{
		this.session = session;
	}

	public boolean isTimeout()
	{
		if (timeout < 1L)
			return false;
		return System.currentTimeMillis() - lastAccessTime >= timeout;
	}
}
