package com.hlideal.framework.admin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import com.hlideal.common.mybatis.mapper.JsonMapper;
import com.hlideal.common.session.SessionLocalManager;


public class AuthorityFilter implements Filter {
	protected final Logger logger = LoggerFactory.getLogger(getClass());	
	private static final String[] filterDefaultUrl = { "/admin/backstage" };

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String uri = request.getServletPath();
		boolean checkfilter = false;

		for (String s : filterDefaultUrl) {
			if (uri.startsWith(s)) {
				checkfilter = true;
			}
		}

		if (checkfilter == true) {			
			if (SessionLocalManager.getSessionLocal() == null || StringUtils.isEmpty(SessionLocalManager.getSessionLocal().getUserid())) {
				//判断是否ajax访问
				if(!StringUtils.isEmpty(request.getParameter("jsonCallBack"))){
					JSONObject json = new JSONObject();
					json.put("needlogin", true);
					json.put("message", "请重新登录");
					renderString(response, json, request.getParameter("jsonCallBack"));
					return;
				}
				
				response.sendRedirect("/admin/login.htm");
				return;
			}					
		}
		chain.doFilter(request, response);
	}
	
	protected String renderString(HttpServletResponse response, Object object, String jsonCallBack) {
		
		try {
			response.reset();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonCallBack + "(" + JsonMapper.toJsonString(object) + ")");
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}
}
