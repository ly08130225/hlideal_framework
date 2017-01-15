package com.hlideal.common.mybatis.mapper;

import java.util.Comparator;

import com.hlideal.common.base.utils.StringUtils;

public class CompareCommonVo implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		CommonVo vo01 = (CommonVo) o1;
		CommonVo vo02 = (CommonVo) o2;
		Integer vo1value = StringUtils.toInteger(vo01.getCommon03());
		Integer vo2value = StringUtils.toInteger(vo02.getCommon03());
		if(vo1value >= vo2value) return -1;
		return 1;
	}

}
