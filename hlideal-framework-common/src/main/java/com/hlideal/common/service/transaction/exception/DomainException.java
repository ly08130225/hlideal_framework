package com.hlideal.common.service.transaction.exception;

import com.hlideal.common.service.transaction.enums.DomainResultEnum;
import com.hlideal.common.functions.hlideal.utils.lang.exception.ApplicationNestException;

public class DomainException extends ApplicationNestException {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -2527668261623906864L;

	private DomainResultEnum domainResult;

	private String errorMsg;

	/**
	 * 构建一个<code>EstateDomainException.java</code>
	 */
	public DomainException() {
		super();
	}

	/**
	 * 构建一个<code>EstateDomainException.java</code>
	 * 
	 * @param domainResult
	 * @param errorMsg
	 */
	public DomainException(DomainResultEnum domainResult, String errorMsg) {
		super(errorMsg);
		this.domainResult = domainResult;
		this.errorMsg = errorMsg;
	}

	/**
	 * 构建一个<code>EstateDomainException.java</code>
	 * 
	 * @param message
	 */
	public DomainException(String message) {
		super(message);
	}

	/**
	 * 构建一个<code>EstateDomainException.java</code>
	 * 
	 * @param cause
	 */
	public DomainException(Throwable cause) {
		super(cause);
	}

	public DomainResultEnum getDomainResult() {
		return domainResult;
	}

	public void setDomainResult(DomainResultEnum domainResult) {
		this.domainResult = domainResult;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EstateDomainException [domainResult=");
		builder.append(domainResult);
		builder.append(", errorMsg=");
		builder.append(errorMsg);
		builder.append("]");
		return builder.toString();
	}

}
