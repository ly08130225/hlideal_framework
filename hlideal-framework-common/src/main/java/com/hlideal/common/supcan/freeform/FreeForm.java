/**
 * Copyright 2014-2015 <a href="http://www.hlideal.com">dingding</a> All rights reserved.
 */
package com.hlideal.common.supcan.freeform;

import com.hlideal.common.supcan.common.Common;
import com.hlideal.common.supcan.common.properties.Properties;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 硕正FreeForm
 * @author WangZhen
 * @version 2013-11-04
 */
@XStreamAlias("FreeForm")
public class FreeForm extends Common {

	public FreeForm() {
		super();
	}
	
	public FreeForm(Properties properties) {
		this();
		this.properties = properties;
	}
	
}
