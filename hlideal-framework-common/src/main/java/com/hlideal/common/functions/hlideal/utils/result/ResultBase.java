package com.hlideal.common.functions.hlideal.utils.result;

public class ResultBase implements Result {
	private static final long serialVersionUID = 4165926587298446217L;
	private boolean success = false;

	private String message = "";

	public boolean isSuccess() {
		return this.success;
	}

	public boolean isExecuted() {
		return false;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String toString() {
		return "ResultBase [success=" + this.success + ", message=" + this.message + "]";
	}
}