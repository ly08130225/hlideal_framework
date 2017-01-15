package com.hlideal.common.domain;

import java.io.Serializable;

public abstract interface Domain extends Serializable {
	public abstract void examSelf() throws Exception;
}
