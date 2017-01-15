package com.hlideal.common.mybatis;

import java.io.Serializable;

public class SQLAdapter implements Serializable {
	private static final long serialVersionUID = 1L;

	private String sql;

	public SQLAdapter(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}
