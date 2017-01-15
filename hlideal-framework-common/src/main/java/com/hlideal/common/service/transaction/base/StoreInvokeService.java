package com.hlideal.common.service.transaction.base;

import com.hlideal.common.domain.Domain;

public abstract interface StoreInvokeService {
	public abstract void store(Domain paramDomain);
}