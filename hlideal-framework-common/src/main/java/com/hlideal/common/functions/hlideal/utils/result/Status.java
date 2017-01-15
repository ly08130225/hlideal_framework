package com.hlideal.common.functions.hlideal.utils.result;

import java.util.ArrayList;
import java.util.List;

public enum Status {
	SUCCESS("success", "成功"),

	FAIL("fail", "失败"),

	PROCESSING("processing", "处理中");

	private final String code;
	private final String message;

	private Status(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

	public String code() {
		return this.code;
	}

	public String message() {
		return this.message;
	}

	public static Status findStatus(String code) {
		for (Status status : values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		throw new IllegalArgumentException("ResultInfo Status not legal:" + code);
	}

	public static List<Status> getAllStatus() {
		List list = new ArrayList();
		for (Status status : values()) {
			list.add(status);
		}
		return list;
	}

	public static List<String> getAllStatusCode() {
		List list = new ArrayList();
		for (Status status : values()) {
			list.add(status.code());
		}
		return list;
	}
}