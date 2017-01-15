package com.hlideal.common.service.transaction.base;

import com.hlideal.common.domain.Domain;
import com.hlideal.common.functions.hlideal.utils.result.Result;

public abstract interface ConvertInvokeService {
	public abstract void convert(Result paramResult, Domain paramDomain);
}