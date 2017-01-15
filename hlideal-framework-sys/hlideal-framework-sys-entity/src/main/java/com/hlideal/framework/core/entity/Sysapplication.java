package com.hlideal.framework.core.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.hlideal.common.mybatis.DataEntity;


 /**
 * SysapplicationEntity
 * @author younger
 * @date 2016-08-21
 */
public class Sysapplication extends DataEntity<Sysapplication> {
	private static final long serialVersionUID = 1L;

	private String appcode;
	private String appname;
	private String apphost;
	private String appport;
	private String appcontext;
	private String appctx;
	private String appurl;
	private String appstatus;

	public Sysapplication() {
		super();
	}

	public Sysapplication(String id) {
		super(id);
	}

	public void setAppcode(String appcode){
		this.appcode = appcode;
	}

	public String getAppcode(){
		return this.appcode;
	}	   
	public void setAppname(String appname){
		this.appname = appname;
	}

	public String getAppname(){
		return this.appname;
	}	   
	public void setApphost(String apphost){
		this.apphost = apphost;
	}

	public String getApphost(){
		return this.apphost;
	}	   
	public void setAppport(String appport){
		this.appport = appport;
	}

	public String getAppport(){
		return this.appport;
	}	   
	public void setAppcontext(String appcontext){
		this.appcontext = appcontext;
	}

	public String getAppcontext(){
		return this.appcontext;
	}	   
	public void setAppctx(String appctx){
		this.appctx = appctx;
	}

	public String getAppctx(){
		return this.appctx;
	}	   
	public void setAppurl(String appurl){
		this.appurl = appurl;
	}

	public String getAppurl(){
		return this.appurl;
	}	   
	public void setAppstatus(String appstatus){
		this.appstatus = appstatus;
	}

	public String getAppstatus(){
		return this.appstatus;
	}	   

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}