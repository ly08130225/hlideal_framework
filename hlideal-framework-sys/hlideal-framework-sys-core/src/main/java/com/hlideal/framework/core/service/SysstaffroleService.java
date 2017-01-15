package com.hlideal.framework.core.service;

import com.hlideal.framework.core.dao.SysstaffroleDao;
import com.hlideal.framework.core.entity.Sysstaffrole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hlideal.common.service.CrudService;


/**
 * SysstaffroleService
 * @author younger
 * @date 2016-08-21
 */
@Service("sysstaffroleService")
@Transactional(readOnly = true)
public class SysstaffroleService extends CrudService<SysstaffroleDao, Sysstaffrole>{
	
	@Transactional (propagation = Propagation.REQUIRED,readOnly=false)
	public void deleteBystaffid(Sysstaffrole staffrole){
		dao.deleteBystaffid(staffrole);
	}
}
