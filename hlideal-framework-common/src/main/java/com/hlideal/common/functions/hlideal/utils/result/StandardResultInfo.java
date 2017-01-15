package com.hlideal.common.functions.hlideal.utils.result;



import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.hlideal.common.functions.hlideal.utils.lang.ParameterHolder;
import com.hlideal.common.functions.hlideal.utils.lang.exception.ResultException;

public class StandardResultInfo implements ResultInfo, ParameterHolder {
	private static final long serialVersionUID = -656083826615770161L;
	protected Status status;
	protected String code;
	protected String description;
	protected ResultException resultException;
	protected Map<Object, Object> params = new LinkedHashMap();

	public StandardResultInfo() {
	}

	public StandardResultInfo(Status status) {
		this(status, null, null);
	}

	public StandardResultInfo(Status status, String code) {
		this(status, code, null);
	}

	public StandardResultInfo(Status status, String code, String description) {
		this.status = status;
		this.code = code;
		this.description = description;
	}

	public Status getStatus() {
		return this.status;
	}

	public String getCode() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<Object, Object> getParams() {
		return this.params;
	}

	public void setParams(Map<Object, Object> params) {
		this.params = params;
	}

	public ResultException getResultException() {
		if (!Status.FAIL.equals(this.status)) {
			return null;
		}
		if (this.resultException == null) {
			this.resultException = new ResultException(this.code, this.description, null, null, false, false);
		}

		return this.resultException;
	}

	public void setParameter(Object name, Object value) {
		this.params.put(name, value);
	}

	public Object getParameter(Object name) {
		return this.params.get(name);
	}

	public Object removeParameter(Object name) {
		return this.params.remove(name);
	}

	public Set<Object> getParameterNames() {
		return this.params.keySet();
	}

	public Collection<Object> getParameterValues() {
		return this.params.values();
	}

	public boolean hasParameter(Object name) {
		return this.params.containsKey(name);
	}

	public void copy(ParameterHolder parameterHolder) {
		if (parameterHolder == null) {
			return;
		}
		for (Map.Entry entry : this.params.entrySet())
			parameterHolder.setParameter(entry.getKey(), entry.getValue());
	}

	/** @deprecated */
	public boolean isSucess() {
		return this.status == Status.SUCCESS;
	}

	public boolean isSuccess() {
		return this.status == Status.SUCCESS;
	}

	public boolean isFail() {
		return this.status == Status.FAIL;
	}

	public boolean isProcessing() {
		return this.status == Status.PROCESSING;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("StandardResultInfo{");
		sb.append("status=").append(this.status);
		sb.append(", code='").append(this.code).append('\'');
		sb.append(", description='").append(this.description).append('\'');
		sb.append(", resultException=").append(this.resultException);
		sb.append(", params=").append(this.params);
		sb.append('}');
		return sb.toString();
	}
}