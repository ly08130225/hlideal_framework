package com.hlideal.common.service.transaction.exception;

import com.hlideal.common.service.transaction.enums.TranResultEnum;

public class ExceptionFactory {

	public static TranException newTranException(TranResultEnum resultCode, String errorMsg) {
		return new TranException(resultCode, errorMsg);
	}
}
