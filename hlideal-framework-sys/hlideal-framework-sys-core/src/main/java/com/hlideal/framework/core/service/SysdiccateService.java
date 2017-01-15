package com.hlideal.framework.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hlideal.framework.core.dao.SysdiccateDao;
import com.hlideal.common.service.CrudService;
import com.hlideal.framework.core.entity.Sysdiccate;

/**
 * SysdiccateService
 * @author younger
 * @date 2016-08-21
 */
@Service("sysdiccateService")
@Transactional(readOnly = true)
public class SysdiccateService extends CrudService<SysdiccateDao, Sysdiccate>{
	
	
}
