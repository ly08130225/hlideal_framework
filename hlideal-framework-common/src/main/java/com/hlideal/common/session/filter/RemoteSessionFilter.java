package com.hlideal.common.session.filter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.hlideal.common.session.SessionConstant;
import com.hlideal.common.session.security.impl.SessionScanThread;

/**
 * 过滤器，通过isscan参数来调用session失效目的，若集群，可通过类进行扩展，如远程调用服务器将session存储在redis等缓存中
 */
public class RemoteSessionFilter implements Filter {
	
	@Override
	public void destroy() {

	}
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
																						throws IOException,
																						ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		SessionConstant.setSessionId(request.getSession().getId());
		arg2.doFilter(arg0, arg1);
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		boolean isscan = false;
		String temp = arg0.getInitParameter("scan");
		if (temp != null && temp.length() > 0) {
			isscan = Boolean.parseBoolean(temp);
		}
		if (isscan) {
			ExecutorService executorService = null;
			executorService = Executors.newSingleThreadExecutor();
		    executorService.execute(new SessionScanThread());
		}
	}
	
}
