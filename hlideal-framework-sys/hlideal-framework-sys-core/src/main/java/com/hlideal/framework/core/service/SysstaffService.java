package com.hlideal.framework.core.service;

import com.hlideal.framework.core.dao.SysstaffDao;
import com.hlideal.framework.core.entity.Sysstaff;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hlideal.common.service.CrudService;

/**
 * SysstaffService
 * @author younger
 * @date 2016-08-21
 */
@Service("sysstaffService")
@Transactional(readOnly = true)
public class SysstaffService extends CrudService<SysstaffDao, Sysstaff>{
	
	
}
