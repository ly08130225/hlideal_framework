package com.hlideal.framework.core.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.hlideal.common.mybatis.DataEntity;


 /**
 * SysdeptEntity
 * @author younger
 * @date 2016-08-21
 */
public class Sysdept extends DataEntity<Sysdept> {
	private static final long serialVersionUID = 1L;

	private String parentid;
	private String depttype;
	private String deptno;
	private String deptname;
	private String managerid;
	private String tempmanagerids;
	private String supermanagerid;

	public Sysdept() {
		super();
	}

	public Sysdept(String id) {
		super(id);
	}

	public void setParentid(String parentid){
		this.parentid = parentid;
	}

	public String getParentid(){
		return this.parentid;
	}	   
	public void setDepttype(String depttype){
		this.depttype = depttype;
	}

	public String getDepttype(){
		return this.depttype;
	}	   
	public void setDeptno(String deptno){
		this.deptno = deptno;
	}

	public String getDeptno(){
		return this.deptno;
	}	   
	public void setDeptname(String deptname){
		this.deptname = deptname;
	}

	public String getDeptname(){
		return this.deptname;
	}	   
	public void setManagerid(String managerid){
		this.managerid = managerid;
	}

	public String getManagerid(){
		return this.managerid;
	}	   
	public void setTempmanagerids(String tempmanagerids){
		this.tempmanagerids = tempmanagerids;
	}

	public String getTempmanagerids(){
		return this.tempmanagerids;
	}	   
	public void setSupermanagerid(String supermanagerid){
		this.supermanagerid = supermanagerid;
	}

	public String getSupermanagerid(){
		return this.supermanagerid;
	}	   

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}