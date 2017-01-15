package com.hlideal.common.service.transaction.enums;

import java.util.ArrayList;
import java.util.List;

public enum TranResultEnum {
	UN_KNOWN_EXCEPTION("UN_KNOWN_EXCEPTION", "未知异常"), 
	INCOMPLETE_REQ_PARAM("INCOMPLETE_REQ_PARAM", "请求参数不完整"), 
	DATABASE_EXCEPTION("DATABASE_EXCEPTION", "数据库异常"), 
	HAVE_NOT_DATA("HAVE_NOT_DATA", "没有数据"), 
	EXECUTE_SUCCESS("EXECUTE_SUCCESS", "执行成功"), 
	EXECUTE_FAILURE("EXECUTE_FAILURE", "执行失败"), 
	EXECUTE_FAIL("EXECUTE_FAIL", "执行失败");

	/** 枚举值 */
	private final String code;

	/** 枚举描述 */
	private final String message;

	/**
	 * 构造一个<code>EstateResultEnum</code>枚举对象
	 * 
	 * @param code
	 * @param message
	 */
	private TranResultEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return Returns the code.
	 */
	public String code() {
		return code;
	}

	/**
	 * @return Returns the message.
	 */
	public String message() {
		return message;
	}

	/**
	 * 通过枚举<code>code</code>获得枚举
	 * 
	 * @param code
	 * @return EstateResultEnum
	 */
	public static TranResultEnum getByCode(String code) {
		for (TranResultEnum _enum : values()) {
			if (_enum.getCode().equals(code)) {
				return _enum;
			}
		}
		return null;
	}

	/**
	 * 获取全部枚举
	 * 
	 * @return List<EstateResultEnum>
	 */
	public List<TranResultEnum> getAllEnum() {
		List<TranResultEnum> list = new ArrayList<TranResultEnum>();
		for (TranResultEnum _enum : values()) {
			list.add(_enum);
		}
		return list;
	}

	/**
	 * 获取全部枚举值
	 * 
	 * @return List<String>
	 */
	public List<String> getAllEnumCode() {
		List<String> list = new ArrayList<String>();
		for (TranResultEnum _enum : values()) {
			list.add(_enum.code());
		}
		return list;
	}
}
