/**
 * Copyright 2014-2015 <a href="http://www.hlideal.com">dingding</a> All rights reserved.
 */
package com.hlideal.common.mybatis;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;

import com.hlideal.common.base.utils.StringUtils;
import com.hlideal.common.base.AppConfiguration;

/**
 * Entity支持类
 * @author ThinkGem
 * @version 2014-05-16
 */
public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 实体编号（唯一标识）
	 */
	protected String id;
	
	/**
	 * 当前实体分页对象
	 */
	protected Page<T> page;
	
	/**
	 * 自定义SQL（SQL标识，SQL内容）
	 */
	protected Map<String, String> sqlMap;
	
	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 */
	protected boolean isNewRecord = false;
	protected boolean isGenKey = true;
	protected boolean isSelectDelete = false;

	public BaseEntity() {
		
	}
	
	public BaseEntity(String id) {
		this();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonIgnore
	@XmlTransient
	public Page<T> getPage() {
		if (page == null){
			page = new Page<T>();
		}
		return page;
	}
	
	public Page<T> setPage(Page<T> page) {
		this.page = page;
		return page;
	}

	@JsonIgnore
	@XmlTransient
	public Map<String, String> getSqlMap() {
		if (sqlMap == null){
			sqlMap = Maps.newHashMap();
		}
		return sqlMap;
	}

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}
	
	/**
	 * 插入之前执行方法，子类实现
	 */
	public abstract void preInsert();
	
	/**
	 * 更新之前执行方法，子类实现
	 */
	public abstract void preUpdate();
	
    /**
	 * 是否是新记录（默认：false）
     * @return
     */
	public boolean getIsNewRecord() {
        return isNewRecord || StringUtils.isBlank(getId());
    }

	/**
	 * 是否是新记录（默认：false），
	 */
	public void setIsNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}
	
	
	/**
	 * 是否生成UUID（默认：true）
     * @return
     */
	public boolean getIsSelectDelete() {
		return isSelectDelete;
	}

	/**
	 * 是否生成UUID（默认：true）
     * @return
     */
	public void setIsSelectDelete(boolean isSelectDelete) {
		this.isSelectDelete = isSelectDelete;
	}
	
	/**
	 * 是否查询deleteflag数据（默认：false）
     * @return
     */
	public boolean getIsGenKey() {
		return isGenKey;
	}

	/**
	 * 是否生成UUID（默认：true）
     * @return
     */
	public void setIsGenKey(boolean isGenKey) {
		this.isGenKey = isGenKey;
	}

	/**
	 * 全局变量对象
	 */
	@JsonIgnore
	public AppConfiguration getAppConfiguration() {
		return AppConfiguration.getInstance();
	}
	
	/**
	 * 全局变量对象
	 */
	@JsonIgnore
	public Global getGlobal() {
		return Global.getInstance();
	}
	
	/**
	 * 获取数据库名称
	 */
	@JsonIgnore
	public String getDbName(){
		return AppConfiguration.getInstance().getString("jdbc.type");
	}
	
    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    
	/**
	 * 删除标记（0：正常；1：删除；2：审核；）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";
	
}
