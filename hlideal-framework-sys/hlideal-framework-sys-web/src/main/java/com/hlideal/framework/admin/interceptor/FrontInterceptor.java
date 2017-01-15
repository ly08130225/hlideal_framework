package com.hlideal.framework.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hlideal.common.session.SessionLocalManager;


public class FrontInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
								Object handler) throws Exception {
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
							Object handler, ModelAndView modelAndView) throws Exception {
		
		if (modelAndView != null) {
			if (SessionLocalManager.getSessionLocal() != null
				&& StringUtils.isEmpty(SessionLocalManager.getSessionLocal().getUserid())) {
				modelAndView.addObject("sessionScope", SessionLocalManager.getSessionLocal());
			}
		}
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
								Object handler, Exception ex) throws Exception {
		response.setHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "must-revalidate");
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
	}
}
