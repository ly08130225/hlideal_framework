package com.hlideal.framework.core.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.hlideal.common.mybatis.DataEntity;


 /**
 * SysdiccateEntity
 * @author younger
 * @date 2016-08-21
 */
public class Sysdiccate extends DataEntity<Sysdiccate> {
	private static final long serialVersionUID = 1L;

	private String catekey;
	private String catename;

	public Sysdiccate() {
		super();
	}

	public Sysdiccate(String id) {
		super(id);
	}

	public void setCatekey(String catekey){
		this.catekey = catekey;
	}

	public String getCatekey(){
		return this.catekey;
	}	   
	public void setCatename(String catename){
		this.catename = catename;
	}

	public String getCatename(){
		return this.catename;
	}	   

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}