package com.hlideal.common.service.transaction;

import java.io.Serializable;

public class TranDomainHolder implements Serializable {

	private static final long serialVersionUID = -6099836956384599949L;

	private static ThreadLocal<TranDomainContext<?>> contextLocal = new ThreadLocal<TranDomainContext<?>>();

	public TranDomainHolder() {
	}

	/**
	 * 获取上下文
	 * 
	 * @return Returns the ContractContext
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Order> TranDomainContext<T> get() {
		return (TranDomainContext<T>) contextLocal.get();
	}

	/**
	 * 赋予上下文
	 * 
	 * @param TranDomainContext
	 */
	public static <T extends Order> void set(TranDomainContext<T> context) {
		contextLocal.set(context);
	}

	/**
	 * 清理充值上下文
	 */
	public static void clear() {
		contextLocal.set(null);
	}
}