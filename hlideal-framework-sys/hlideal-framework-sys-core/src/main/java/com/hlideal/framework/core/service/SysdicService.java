package com.hlideal.framework.core.service;

import com.hlideal.framework.core.dao.SysdicDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hlideal.common.service.CrudService;
import com.hlideal.framework.core.entity.Sysdic;

/**
 * SysdicService
 * @author younger
 * @date 2016-08-21
 */
@Service("sysdicService")
@Transactional(readOnly = true)
public class SysdicService extends CrudService<SysdicDao, Sysdic>{
	
	public Sysdic getDicByKey(Sysdic sysdic){
		return dao.getDicByKey(sysdic);
	}
}
