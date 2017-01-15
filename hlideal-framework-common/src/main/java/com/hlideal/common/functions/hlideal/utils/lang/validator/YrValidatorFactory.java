package com.hlideal.common.functions.hlideal.utils.lang.validator;

import javax.validation.Validator;

public enum YrValidatorFactory {
	INSTANCE {
		@Override
		public Validator getValidator() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	public abstract Validator getValidator();
}