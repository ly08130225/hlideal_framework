package com.hlideal.common.functions.hlideal.utils.result;

import com.hlideal.common.functions.hlideal.utils.lang.enums.DataOperationCodeEnum;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class DataOperationResult extends ResultBase {
	private static final long serialVersionUID = -3103564323080304914L;
	private DataOperationCodeEnum code = DataOperationCodeEnum.EXECUTE_FAIL;

	public DataOperationResult() {
	}

	public DataOperationResult(DataOperationCodeEnum code) {
		this.code = code;
	}

	public boolean isExecuted() {
		return DataOperationCodeEnum.EXECUTE_SUCCESS == this.code;
	}

	public DataOperationCodeEnum getCode() {
		return this.code;
	}

	public void setCode(DataOperationCodeEnum code) {
		this.code = code;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}