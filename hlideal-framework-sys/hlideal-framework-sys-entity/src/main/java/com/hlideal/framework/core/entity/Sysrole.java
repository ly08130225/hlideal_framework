package com.hlideal.framework.core.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.hlideal.common.mybatis.DataEntity;


 /**
 * SysroleEntity
 * @author younger
 * @date 2016-08-21
 */
public class Sysrole extends DataEntity<Sysrole> {
	private static final long serialVersionUID = 1L;

	private String roleno;

	private String name;

	public Sysrole() {
		super();
	}

	public Sysrole(String id) {
		super(id);
	}

	public void setRoleno(String roleno){
		this.roleno = roleno;
	}

	public String getRoleno(){
		return this.roleno;
	}	   
	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}	   

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}