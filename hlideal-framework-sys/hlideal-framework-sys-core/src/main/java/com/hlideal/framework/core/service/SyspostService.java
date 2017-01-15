package com.hlideal.framework.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hlideal.common.service.CrudService;
import com.hlideal.framework.core.dao.SyspostDao;
import com.hlideal.framework.core.entity.Syspost;

/**
 * SyspostService
 * @author younger
 * @date 2016-08-21
 */
@Service("syspostService")
@Transactional(readOnly = true)
public class SyspostService extends CrudService<SyspostDao, Syspost>{
	
	
}
