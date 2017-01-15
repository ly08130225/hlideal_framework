package com.hlideal.common.session.security.impl;

import com.hlideal.common.session.security.RemoteSessionLocal;

import java.util.Iterator;
import java.util.Map;

public class SessionScanThread extends Thread {
	public SessionScanThread() {
		setDaemon(true);
	}

	@SuppressWarnings("rawtypes")
	public void run() {
		System.out.println("session扫描线程已启动...");
		while (true) {
			Iterator entries = RemoteSessionServiceImpl.sessionLocals.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = (Map.Entry) entries.next();
				if (((RemoteSessionLocal) entry.getValue()).isTimeout())
					RemoteSessionServiceImpl.sessionLocals.remove(entry.getKey());
			}
			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}