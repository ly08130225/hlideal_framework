package com.hlideal.framework.core.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.hlideal.common.mybatis.DataEntity;


 /**
 * SyspostEntity
 * @author younger
 * @date 2016-08-21
 */
public class Syspost extends DataEntity<Syspost> {
	private static final long serialVersionUID = 1L;

	private String posttype;
	private String postno;
	private String postname;

	public Syspost() {
		super();
	}

	public Syspost(String id) {
		super(id);
	}

	public void setPosttype(String posttype){
		this.posttype = posttype;
	}

	public String getPosttype(){
		return this.posttype;
	}	   
	public void setPostno(String postno){
		this.postno = postno;
	}

	public String getPostno(){
		return this.postno;
	}	   
	public void setPostname(String postname){
		this.postname = postname;
	}

	public String getPostname(){
		return this.postname;
	}	   

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}