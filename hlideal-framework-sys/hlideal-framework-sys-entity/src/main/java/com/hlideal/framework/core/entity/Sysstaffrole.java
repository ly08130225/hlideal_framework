package com.hlideal.framework.core.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.hlideal.common.mybatis.DataEntity;


/**
 * SysstaffroleEntity
 * @author younger
 * @date 2016-08-21
 */
public class Sysstaffrole extends DataEntity<Sysstaffrole> {
	private static final long serialVersionUID = 1L;

	private String staffid;
	private String roleid;

	public Sysstaffrole() {
		super();
	}

	public Sysstaffrole(String id) {
		super(id);
	}

	public void setStaffid(String staffid){
		this.staffid = staffid;
	}

	public String getStaffid(){
		return this.staffid;
	}	   
	public void setRoleid(String roleid){
		this.roleid = roleid;
	}

	public String getRoleid(){
		return this.roleid;
	}	   

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}