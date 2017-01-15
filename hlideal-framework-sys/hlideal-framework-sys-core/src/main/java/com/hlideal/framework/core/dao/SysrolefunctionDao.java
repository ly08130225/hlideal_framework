package com.hlideal.framework.core.dao;


import com.hlideal.common.mybatis.CrudDao;
import com.hlideal.common.mybatis.annotation.MyBatisDao;
import com.hlideal.framework.core.entity.Sysrolefunction;

/**
 * SysrolefunctionDAO接口
 * @author younger
 * @date 2016-08-21
 */
 
@MyBatisDao
public interface SysrolefunctionDao extends CrudDao<Sysrolefunction> {
	 void deleteByroleid(Sysrolefunction rolefunction);
}