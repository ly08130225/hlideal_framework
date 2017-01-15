package com.hlideal.framework.core.service;

import com.hlideal.framework.core.dao.SysroleDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hlideal.common.service.CrudService;
import com.hlideal.framework.core.entity.Sysrole;
/**
 * SysroleService
 * @author younger
 * @date 2016-08-21
 */
@Service("sysroleService")
@Transactional(readOnly = true)
public class SysroleService extends CrudService<SysroleDao, Sysrole>{
	
	
}
