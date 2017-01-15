package com.hlideal.framework.core.service;

import com.hlideal.framework.core.entity.Sysdept;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hlideal.framework.core.dao.SysdeptDao;
import com.hlideal.common.service.CrudService;

/**
 * SysdeptService
 * @author younger
 * @date 2016-08-21
 */
@Service("sysdeptService")
@Transactional(readOnly = true)
public class SysdeptService extends CrudService<SysdeptDao, Sysdept>{
	
	
}
