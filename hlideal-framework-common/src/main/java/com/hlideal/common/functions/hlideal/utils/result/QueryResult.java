package com.hlideal.common.functions.hlideal.utils.result;

import com.hlideal.common.functions.hlideal.utils.lang.enums.DataOperationCodeEnum;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class QueryResult<T> extends ResultBase {
	private static final long serialVersionUID = 7213101111353450302L;
	private T resultInfo;
	private DataOperationCodeEnum code = DataOperationCodeEnum.EXECUTE_FAIL;

	public QueryResult() {
	}

	public QueryResult(T resultInfo, DataOperationCodeEnum code) {
		this.resultInfo = resultInfo;
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

	public T getResultInfo() {
		return this.resultInfo;
	}

	public void setResultInfo(T resultInfo) {
		this.resultInfo = resultInfo;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}