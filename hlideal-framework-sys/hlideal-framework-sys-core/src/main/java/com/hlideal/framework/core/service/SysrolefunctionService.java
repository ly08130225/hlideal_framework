package com.hlideal.framework.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hlideal.framework.core.dao.SysrolefunctionDao;
import com.hlideal.common.service.CrudService;
import com.hlideal.framework.core.entity.Sysrolefunction;

/**
 * SysrolefunctionService
 * @author younger
 * @date 2016-08-21
 */
@Service("sysrolefunctionService")
@Transactional(readOnly = true)
public class SysrolefunctionService extends CrudService<SysrolefunctionDao, Sysrolefunction>{
	
	@Transactional (propagation = Propagation.REQUIRED,readOnly=false)
	public void deleteByroleid(Sysrolefunction rolefunction){
		dao.deleteByroleid(rolefunction);
	}
}
