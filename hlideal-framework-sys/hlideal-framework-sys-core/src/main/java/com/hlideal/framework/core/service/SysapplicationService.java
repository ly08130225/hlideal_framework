package com.hlideal.framework.core.service;

import com.hlideal.framework.core.entity.Sysapplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hlideal.common.service.CrudService;
import com.hlideal.framework.core.dao.SysapplicationDao;

/**
 * SysapplicationService
 * @author younger
 * @date 2016-08-21
 */
@Service("sysapplicationService")
@Transactional(readOnly = true)
public class SysapplicationService extends CrudService<SysapplicationDao, Sysapplication>{
	
	
}
