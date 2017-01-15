package com.hlideal.framework.core.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.hlideal.common.mybatis.DataEntity;

/**
 * SysfunctionEntity
 * 
 * @author younger
 * @date 2016-08-21
 */
public class Sysfunction extends DataEntity<Sysfunction> {
	private static final long serialVersionUID = 1L;

	private String appid;
	private String parentid;
	private String funtionkey;
	private String functionname;
	private int orderno;
	private String linkurl;
	private String iconlink;
	private String globalcheck;
	private String checkshow;

	private String userId;

	public Sysfunction() {
		super();
	}

	public Sysfunction(String id) {
		super(id);
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppid() {
		return this.appid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getParentid() {
		return this.parentid;
	}

	public void setFuntionkey(String funtionkey) {
		this.funtionkey = funtionkey;
	}

	public String getFuntionkey() {
		return this.funtionkey;
	}

	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}

	public String getFunctionname() {
		return this.functionname;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

	public int getOrderno() {
		return this.orderno;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	public String getLinkurl() {
		return this.linkurl;
	}

	public void setIconlink(String iconlink) {
		this.iconlink = iconlink;
	}

	public String getIconlink() {
		return this.iconlink;
	}

	public void setGlobalcheck(String globalcheck) {
		this.globalcheck = globalcheck;
	}

	public String getGlobalcheck() {
		return this.globalcheck;
	}

	public void setCheckshow(String checkshow) {
		this.checkshow = checkshow;
	}

	public String getCheckshow() {
		return this.checkshow;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}