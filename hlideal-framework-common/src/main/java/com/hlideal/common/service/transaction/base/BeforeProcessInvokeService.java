package com.hlideal.common.service.transaction.base;

import com.hlideal.common.domain.Domain;

public abstract interface BeforeProcessInvokeService {
	public abstract Domain before();
}