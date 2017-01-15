package com.hlideal.framework.core.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.hlideal.common.mybatis.DataEntity;


 /**
 * SysparamEntity
 * @author younger
 * @date 2016-08-21
 */
public class Sysparam extends DataEntity<Sysparam> {
	private static final long serialVersionUID = 1L;

	private String paramkey;
	private String paramvalue;
	private String description;

	public Sysparam() {
		super();
	}

	public Sysparam(String id) {
		super(id);
	}

	public void setParamkey(String paramkey){
		this.paramkey = paramkey;
	}

	public String getParamkey(){
		return this.paramkey;
	}	   
	public void setParamvalue(String paramvalue){
		this.paramvalue = paramvalue;
	}

	public String getParamvalue(){
		return this.paramvalue;
	}	   
	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
	}	   

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}