package com.hlideal.framework.core.service;

import com.hlideal.framework.core.dao.SysstaffdicDao;
import com.hlideal.framework.core.entity.Sysstaffdic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hlideal.common.service.CrudService;

/**
 * SysstaffdicService
 * @author younger
 * @date 2016-11-20
 */
@Service("sysstaffdicService")
@Transactional(readOnly = true)
public class SysstaffdicService extends CrudService<SysstaffdicDao, Sysstaffdic>{
	
	
}
