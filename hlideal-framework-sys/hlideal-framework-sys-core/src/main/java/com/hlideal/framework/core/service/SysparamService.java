package com.hlideal.framework.core.service;

import com.hlideal.framework.core.dao.SysparamDao;
import com.hlideal.framework.core.entity.Sysparam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hlideal.common.service.CrudService;

import java.util.List;

/**
 * SysparamService
 * @author younger
 * @date 2016-08-21
 */
@Service("sysparamService")
@Transactional(readOnly = true)
public class SysparamService extends CrudService<SysparamDao, Sysparam>{
	

}
