package com.hlideal.framework.core.service.impl;

import com.hlideal.framework.core.dao.SysstaffDao;
import com.hlideal.framework.core.entity.Sysstaff;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hlideal.common.base.utils.Encodes;
import com.hlideal.common.security.Digests;
import com.hlideal.common.security.MD5Util;
import com.hlideal.common.service.BaseService;

@Service("sysUserService")
@Transactional(readOnly = true)
public class SysUserService extends BaseService implements InitializingBean {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;

	@Autowired
	private SysstaffDao sysstaffDao;


	public Sysstaff getUserByLoginName(String username, String userpwd) {
		Sysstaff param = new Sysstaff();
		param.setLoginname(username);
		param.setLoginpwd(MD5Util.getMD5_32(userpwd));
		return sysstaffDao.getByLoginName(param);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

	public static String entryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
	}
}