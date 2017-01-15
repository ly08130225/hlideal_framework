package com.hlideal.common.functions.hlideal.utils.result;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.hlideal.common.functions.hlideal.utils.lang.enums.ServiceInvocationCodeEnum;

public class ServiceInvocationResult<T extends ResultBase> extends ResultBase {
	private static final long serialVersionUID = 544353470843095233L;
	private T serviceResult;
	private ServiceInvocationCodeEnum code = ServiceInvocationCodeEnum.INVOCATE_FAIL;

	public ServiceInvocationResult() {
	}

	public ServiceInvocationResult(ServiceInvocationCodeEnum code) {
		this.code = code;
	}

	public ServiceInvocationCodeEnum getCode() {
		return this.code;
	}

	public void setCode(ServiceInvocationCodeEnum code) {
		this.code = code;
	}

	public T getServiceResult() {
		return this.serviceResult;
	}

	public void setServiceResult(T serviceResult) {
		this.serviceResult = serviceResult;
	}

	public boolean isExecuted() {
		return this.code == ServiceInvocationCodeEnum.EXECUTE_SUCCESS;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}