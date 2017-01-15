package com.hlideal.framework.core.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.hlideal.common.mybatis.DataEntity;


 /**
 * SyspoststaffEntity
 * @author younger
 * @date 2016-08-21
 */
public class Syspoststaff extends DataEntity<Syspoststaff> {
	private static final long serialVersionUID = 1L;

	private String postid;
	private String staffid;

	public Syspoststaff() {
		super();
	}

	public Syspoststaff(String id) {
		super(id);
	}

	public void setPostid(String postid){
		this.postid = postid;
	}

	public String getPostid(){
		return this.postid;
	}	   
	public void setStaffid(String staffid){
		this.staffid = staffid;
	}

	public String getStaffid(){
		return this.staffid;
	}	   

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}