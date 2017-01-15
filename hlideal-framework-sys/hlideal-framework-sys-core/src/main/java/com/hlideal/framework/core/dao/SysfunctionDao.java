package com.hlideal.framework.core.dao;

import com.hlideal.common.mybatis.CrudDao;
import com.hlideal.common.mybatis.annotation.MyBatisDao;
import com.hlideal.framework.core.entity.Sysfunction;

import java.util.List;




/**
 * SysfunctionDAO接口
 * @author younger
 * @date 2016-08-21
 */
 
@MyBatisDao
public interface SysfunctionDao extends CrudDao<Sysfunction> {
	 List<Sysfunction> findByUserId(Sysfunction menu);
	 List<Sysfunction> findByRoleID(Sysfunction menu);
	 Sysfunction findMaxId();

}