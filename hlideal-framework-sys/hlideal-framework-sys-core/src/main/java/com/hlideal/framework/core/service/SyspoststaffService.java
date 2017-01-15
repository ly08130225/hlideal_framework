package com.hlideal.framework.core.service;

import com.hlideal.framework.core.dao.SyspoststaffDao;
import com.hlideal.framework.core.entity.Syspoststaff;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hlideal.common.service.CrudService;

/**
 * SyspoststaffService
 * @author younger
 * @date 2016-08-21
 */
@Service("syspoststaffService")
@Transactional(readOnly = true)
public class SyspoststaffService extends CrudService<SyspoststaffDao, Syspoststaff>{
	
	
}
