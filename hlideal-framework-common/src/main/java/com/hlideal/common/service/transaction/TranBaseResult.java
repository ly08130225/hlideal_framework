package com.hlideal.common.service.transaction;

import org.apache.commons.lang3.StringUtils;

import com.hlideal.common.functions.hlideal.utils.result.ResultBase;
import com.hlideal.common.service.transaction.enums.TranResultEnum;

public class TranBaseResult extends ResultBase {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = 5156892170604621621L;
	/** 返回结果码 */
	TranResultEnum tranResultEnum = TranResultEnum.UN_KNOWN_EXCEPTION;

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public boolean isExecuted() {

		return TranResultEnum.EXECUTE_SUCCESS == tranResultEnum ? true : false;
	}

	public TranResultEnum getTranResultEnum() {
		return tranResultEnum;
	}

	public void setTranResultEnum(TranResultEnum TranResultEnum) {
		this.tranResultEnum = TranResultEnum;
		if (this.tranResultEnum != null) {
			if (StringUtils.isEmpty(this.getMessage())) {
				this.setMessage(this.tranResultEnum.getMessage());
			}

		}
	}

	@Override
	public void setSuccess(boolean success) {
		super.setSuccess(success);
		if (success)
			tranResultEnum = TranResultEnum.EXECUTE_SUCCESS;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TranBaseResult [TranResultEnum=");
		builder.append(tranResultEnum);
		builder.append(", url=");
		builder.append(url);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
