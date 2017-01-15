// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RemoteSessionService.java

package com.hlideal.common.session.security;


public interface RemoteSessionService
{

	public abstract void setAttribute(String s, String s1, byte abyte0[], long l);

	public abstract void removeAttribute(String s, String s1, long l);

	public abstract byte[] getAttribute(String s, String s1, long l);

	public abstract void destroy(String s);
}
