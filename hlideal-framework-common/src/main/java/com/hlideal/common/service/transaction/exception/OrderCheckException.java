package com.hlideal.common.service.transaction.exception;

import java.util.HashMap;
import java.util.Map;

public class OrderCheckException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;
	private Map<String, String> errorMap = new HashMap();
	private String msg;

	public OrderCheckException() {
	}

	public OrderCheckException(Throwable cause) {
		super(cause);
	}

	public Map<String, String> getErrorMap() {
		return this.errorMap;
	}

	public void addError(String parameter, String msg) {
		this.errorMap.put(parameter, msg);
		this.msg = null;
	}

	public String getMessage() {
		if (this.msg == null) {
			if (this.errorMap.isEmpty()) {
				this.msg = "";
			} else {
				StringBuilder sb = new StringBuilder();
				for (Map.Entry entry : this.errorMap.entrySet()) {
					sb.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
				}

				sb.deleteCharAt(sb.length() - 1);
				this.msg = sb.toString();
			}
		}
		return this.msg;
	}

	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}