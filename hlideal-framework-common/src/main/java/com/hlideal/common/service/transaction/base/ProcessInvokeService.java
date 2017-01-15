package com.hlideal.common.service.transaction.base;

import com.hlideal.common.domain.Domain;

public abstract interface ProcessInvokeService {
	public abstract void process(Domain paramDomain);
}