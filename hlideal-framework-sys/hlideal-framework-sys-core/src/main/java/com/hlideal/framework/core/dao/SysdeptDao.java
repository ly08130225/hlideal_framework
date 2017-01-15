package com.hlideal.framework.core.dao;


import com.hlideal.common.mybatis.CrudDao;
import com.hlideal.common.mybatis.annotation.MyBatisDao;
import com.hlideal.framework.core.entity.Sysdept;

/**
 * SysdeptDAO接口
 * @author younger
 * @date 2016-08-21
 */
 
@MyBatisDao
public interface SysdeptDao extends CrudDao<Sysdept> {

}