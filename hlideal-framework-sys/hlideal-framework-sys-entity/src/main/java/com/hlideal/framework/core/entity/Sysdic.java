package com.hlideal.framework.core.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.hlideal.common.mybatis.DataEntity;


 /**
 * SysdicEntity
 * @author younger
 * @date 2016-08-21
 */
public class Sysdic extends DataEntity<Sysdic> {
	private static final long serialVersionUID = 1L;

	private String cateid;
	private String catekey;
	private String parentid;
	private String checktree;
	private String dickey;
	private String dicvalue;
	private String minvalue;
	private String maxvalue;
	protected String var06; // 扩展字段6
	protected String var07; // 扩展字段7
	protected String var08; // 扩展字段8
	protected String var09; // 扩展字段9
	protected String var10; // 扩展字段10
	public Sysdic() {
		super();
	}

	public Sysdic(String id) {
		super(id);
	}

	public String getCateid() {
		return cateid;
	}

	public void setCateid(String cateid) {
		this.cateid = cateid;
	}

	public String getCatekey() {
		return catekey;
	}

	public void setCatekey(String catekey) {
		this.catekey = catekey;
	}

	public void setParentid(String parentid){
		this.parentid = parentid;
	}

	public String getParentid(){
		return this.parentid;
	}	   
	public void setChecktree(String checktree){
		this.checktree = checktree;
	}

	public String getChecktree(){
		return this.checktree;
	}	   
	public void setDickey(String dickey){
		this.dickey = dickey;
	}

	public String getDickey(){
		return this.dickey;
	}	   
	public void setDicvalue(String dicvalue){
		this.dicvalue = dicvalue;
	}

	public String getDicvalue(){
		return this.dicvalue;
	}	   
	public void setMinvalue(String minvalue){
		this.minvalue = minvalue;
	}

	public String getMinvalue(){
		return this.minvalue;
	}	   
	public void setMaxvalue(String maxvalue){
		this.maxvalue = maxvalue;
	}

	public String getMaxvalue(){
		return this.maxvalue;
	}

	public String getVar06() {
		return var06;
	}

	public void setVar06(String var06) {
		this.var06 = var06;
	}

	public String getVar07() {
		return var07;
	}

	public void setVar07(String var07) {
		this.var07 = var07;
	}

	public String getVar08() {
		return var08;
	}

	public void setVar08(String var08) {
		this.var08 = var08;
	}

	public String getVar09() {
		return var09;
	}

	public void setVar09(String var09) {
		this.var09 = var09;
	}

	public String getVar10() {
		return var10;
	}

	public void setVar10(String var10) {
		this.var10 = var10;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}