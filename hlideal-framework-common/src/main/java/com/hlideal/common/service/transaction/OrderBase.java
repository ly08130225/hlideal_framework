package com.hlideal.common.service.transaction;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.hlideal.common.functions.hlideal.utils.lang.validator.YrValidatorFactory;
import com.hlideal.common.service.transaction.exception.OrderCheckException;

public abstract class OrderBase implements Order {
	private static final long serialVersionUID = 1L;
	private String gid;

	public String getGid() {
		return this.gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public abstract boolean isCheckGid();

	public void check() {
		Set constraintViolations = YrValidatorFactory.INSTANCE.getValidator().validate(this, new Class[0]);

		validate(constraintViolations);
	}

	public void checkWithGroup(Class<?>[] groups) {
		Set constraintViolations = YrValidatorFactory.INSTANCE.getValidator().validate(this, groups);

		validate(constraintViolations);
	}

	private void validate(Set<ConstraintViolation<OrderBase>> constraintViolations) {
		OrderCheckException exception = null;
		if ((constraintViolations != null) && (!constraintViolations.isEmpty())) {
			exception = new OrderCheckException();
			for (ConstraintViolation constraintViolation : constraintViolations) {
				exception.addError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
			}
		}

		if ((isCheckGid()) && ((getGid() == null) || (getGid().length() != 35))) {
			if (exception == null) {
				exception = new OrderCheckException();
			}
			exception.addError("gid", "gid[" + getGid() + "]长度应该为" + 35 + "位");
		}

		if (exception != null)
			throw exception;
	}
}