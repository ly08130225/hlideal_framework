package com.hlideal.common.service.transaction.exception;

import com.hlideal.common.functions.hlideal.utils.lang.exception.ApplicationNestException;
import com.hlideal.common.service.transaction.enums.TranResultEnum;

public class TranException extends ApplicationNestException {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = 554229467642044021L;

	private TranResultEnum resultCode;

	private String errorMsg;

	/**
	 * 构建一个<code>EstateException.java</code>
	 */
	public TranException() {
		super();
	}

	/**
	 * 构建一个<code>EstateException.java</code>
	 * 
	 * @param arg0
	 * @param arg1
	 */
	public TranException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * 构建一个<code>EstateException.java</code>
	 * 
	 * @param arg0
	 */
	public TranException(String arg0) {
		super(arg0);
	}

	/**
	 * 构建一个<code>EstateException.java</code>
	 * 
	 * @param arg0
	 */
	public TranException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * 构建一个<code>EstateException.java</code>
	 * 
	 * @param resultCode
	 * @param errorMsg
	 */
	public TranException(TranResultEnum resultCode, String errorMsg) {
		super(errorMsg);
		this.resultCode = resultCode;
		this.errorMsg = errorMsg;
	}

	public TranResultEnum getResultCode() {
		return resultCode;
	}

	public void setResultCode(TranResultEnum resultCode) {
		this.resultCode = resultCode;
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
		builder.append("EstateException [resultCode=");
		builder.append(resultCode);
		builder.append(", errorMsg=");
		builder.append(errorMsg);
		builder.append("]");
		return builder.toString();
	}

}
