package com.hlideal.framework.core.service;

import com.hlideal.framework.core.dao.SysfunctionDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hlideal.common.service.CrudService;
import com.hlideal.framework.core.entity.Sysfunction;

/**
 * SysfunctionService
 * @author younger
 * @date 2016-08-21
 */
@Service("sysfunctionService")
@Transactional(readOnly = true)
public class SysfunctionService extends CrudService<SysfunctionDao, Sysfunction>{
	
	
}
