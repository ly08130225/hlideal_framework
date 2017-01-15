/**
 * Copyright 2014-2015 <a href="http://www.hlideal.com">dingding</a> All rights reserved.
 */
package com.hlideal.common.mybatis;

import com.hlideal.common.base.utils.IdGen;

/**
 * 数据Entity类
 * 
 * @author ThinkGem
 * @version 2014-05-16
 */
public abstract class ExEntity<T> extends BaseEntity<T> {

	private static final long serialVersionUID = 1L;

	public ExEntity() {
		super();
	}

	public ExEntity(String id) {
		super(id);
	}

	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert() {
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord) {
			setId(IdGen.uuid());
		}
	}

	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate() {

	}

}
