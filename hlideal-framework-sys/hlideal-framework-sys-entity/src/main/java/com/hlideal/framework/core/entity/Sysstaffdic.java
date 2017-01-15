package com.hlideal.framework.core.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.hlideal.common.mybatis.DataEntity;


/**
 * SysstaffdicEntity
 * @author younger
 * @date 2016-11-20
 */
public class Sysstaffdic extends DataEntity<Sysstaffdic> {
	private static final long serialVersionUID = 1L;

	private String staffid;
	private String dictype;
	private String dicvalue;
	private String dictext;

	public Sysstaffdic() {
		super();
	}

	public Sysstaffdic(String id) {
		super(id);
	}

	public void setStaffid(String staffid){
		this.staffid = staffid;
	}

	public String getStaffid(){
		return this.staffid;
	}	   
	public void setDictype(String dictype){
		this.dictype = dictype;
	}

	public String getDictype(){
		return this.dictype;
	}	   
	public void setDicvalue(String dicvalue){
		this.dicvalue = dicvalue;
	}

	public String getDicvalue(){
		return this.dicvalue;
	}	   
	public void setDictext(String dictext){
		this.dictext = dictext;
	}

	public String getDictext(){
		return this.dictext;
	}	   

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}