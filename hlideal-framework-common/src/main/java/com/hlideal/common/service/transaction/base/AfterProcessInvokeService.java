package com.hlideal.common.service.transaction.base;

import com.hlideal.common.domain.Domain;

public abstract interface AfterProcessInvokeService {
	public abstract Domain after(Domain paramDomain);
}