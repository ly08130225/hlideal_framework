package com.hlideal.framework.core.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.hlideal.common.mybatis.DataEntity;


 /**
 * SysrolefunctionEntity
 * @author younger
 * @date 2016-08-21
 */
public class Sysrolefunction extends DataEntity<Sysrolefunction> {
	private static final long serialVersionUID = 1L;

	private String roleid;
	private String functionid;
	private String appid;

	public Sysrolefunction() {
		super();
	}

	public Sysrolefunction(String id) {
		super(id);
	}

	public void setRoleid(String roleid){
		this.roleid = roleid;
	}

	public String getRoleid(){
		return this.roleid;
	}	   
	public void setFunctionid(String functionid){
		this.functionid = functionid;
	}

	public String getFunctionid(){
		return this.functionid;
	}	   
	public void setAppid(String appid){
		this.appid = appid;
	}

	public String getAppid(){
		return this.appid;
	}	   

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}