package com.hlideal.framework.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.hlideal.common.base.BaseController;
import com.hlideal.framework.core.service.impl.SysUserService;

public abstract class AdminBaseController extends BaseController {

	/**
	 * 前端基础路径
	 */
	@Value("${frontPath}")
	protected String frontPath;

	/**
	 * 后台基础路径
	 */
	@Value("${adminPath}")
	protected String adminPath;

	/**
	 * 前端URL后缀
	 */
	@Value("${urlSuffix}")
	protected String urlSuffix;
	
	@Autowired
	protected SysUserService sysUserService;

}
