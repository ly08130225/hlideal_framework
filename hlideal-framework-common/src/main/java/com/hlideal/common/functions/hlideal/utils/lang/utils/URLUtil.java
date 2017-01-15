// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   URLUtil.java

package com.hlideal.common.functions.hlideal.utils.lang.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Referenced classes of package com.yjf.common.lang.util:
//			StringUtil

public class URLUtil
{

	private static final Logger logger = LoggerFactory.getLogger(URLUtil.class);

	  public static String urlEncode(String source)
	  {
	    if (StringUtil.isBlank(source)) {
	      return null;
	    }

	    try
	    {
	      return URLEncoder.encode(source, "UTF-8");
	    }
	    catch (UnsupportedEncodingException e) {
	      logger.error("URLEncoder 异常 ; 参数  ：source = " + source, e);
	    }
	    return null;
	  }

	  public static String urlDecode(String source)
	  {
	    if (StringUtil.isBlank(source)) {
	      return null;
	    }

	    try
	    {
	      return URLDecoder.decode(source, "UTF-8");
	    }
	    catch (UnsupportedEncodingException e) {
	      logger.error("URLDecode 异常 ; 参数  ：source = " + source, e);
	    }
	    return null;
	  }
}
