package com.hlideal.framework.core.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.hlideal.common.mybatis.DataEntity;


/**
 * SysstaffEntity
 * @author younger
 * @date 2016-08-21
 */
public class Sysstaff extends DataEntity<Sysstaff> {
	private static final long serialVersionUID = 1L;

	private String loginname;
	private String loginpwd;
	private String staffno;
	private String name;
	private String deptid;
	private String deptname;
	private String sex;
	private String address;
	private String email;
	private String hostip;
	private String mobilephone;
	private String connectphone;
	private String adminstatus;
	private String loginip;
	private String logindate;
	private String loginflag;

	public Sysstaff() {
		super();
	}

	public Sysstaff(String id) {
		super(id);
	}

	public void setLoginname(String loginname){
		this.loginname = loginname;
	}

	public String getLoginname(){
		return this.loginname;
	}	   
	public void setLoginpwd(String loginpwd){
		this.loginpwd = loginpwd;
	}

	public String getLoginpwd(){
		return this.loginpwd;
	}	   
	public void setStaffno(String staffno){
		this.staffno = staffno;
	}

	public String getStaffno(){
		return this.staffno;
	}	   
	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}	   
	public void setDeptid(String deptid){
		this.deptid = deptid;
	}

	public String getDeptid(){
		return this.deptid;
	}	   
	public void setDeptname(String deptname){
		this.deptname = deptname;
	}

	public String getDeptname(){
		return this.deptname;
	}	   
	public void setSex(String sex){
		this.sex = sex;
	}

	public String getSex(){
		return this.sex;
	}	   
	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return this.address;
	}	   
	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return this.email;
	}	   
	public void setHostip(String hostip){
		this.hostip = hostip;
	}

	public String getHostip(){
		return this.hostip;
	}	   
	public void setMobilephone(String mobilephone){
		this.mobilephone = mobilephone;
	}

	public String getMobilephone(){
		return this.mobilephone;
	}	   
	public void setConnectphone(String connectphone){
		this.connectphone = connectphone;
	}

	public String getConnectphone(){
		return this.connectphone;
	}	   
	public void setAdminstatus(String adminstatus){
		this.adminstatus = adminstatus;
	}

	public String getAdminstatus(){
		return this.adminstatus;
	}	   
	public void setLoginip(String loginip){
		this.loginip = loginip;
	}

	public String getLoginip(){
		return this.loginip;
	}	   
	public void setLogindate(String logindate){
		this.logindate = logindate;
	}

	public String getLogindate(){
		return this.logindate;
	}	   
	public void setLoginflag(String loginflag){
		this.loginflag = loginflag;
	}

	public String getLoginflag(){
		return this.loginflag;
	}	   

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}