/**
 * Copyright 2014-2015 <a href="http://www.hlideal.com">dingding</a> All rights reserved.
 */
package com.hlideal.common.service;

import java.util.Date;

import com.hlideal.common.service.transaction.TranBaseResult;
import com.hlideal.common.service.transaction.exception.TranException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.hlideal.common.service.transaction.base.AfterProcessInvokeService;
import com.hlideal.common.service.transaction.base.BeforeProcessInvokeService;
import com.hlideal.common.domain.Domain;
import com.hlideal.common.service.transaction.Order;
import com.hlideal.common.service.transaction.TranDomainContext;
import com.hlideal.common.service.transaction.TranDomainHolder;
import com.hlideal.common.service.transaction.enums.TranResultEnum;
import com.hlideal.common.service.transaction.base.CheckBeforeProcessService;
import com.hlideal.common.service.transaction.base.ProcessInvokeService;
import com.hlideal.common.service.transaction.exception.DomainException;
import com.hlideal.common.service.transaction.exception.ExceptionFactory;

@Transactional(readOnly = true)
public abstract class BaseService {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/** 事务模板 --暂时不启用*/
	//@Autowired  
	protected TransactionTemplate transactionTemplate;

	protected void checkOrder(Order order) {
		logger.info("[order={}]", order);

		if (null == order) {
			throw ExceptionFactory.newTranException(TranResultEnum.EXECUTE_FAILURE, "order must not be null");
		}

		try {
			order.check();
		} catch (IllegalArgumentException ex) {
			throw ExceptionFactory.newTranException(TranResultEnum.INCOMPLETE_REQ_PARAM, "请求参数异常--" + ex.getLocalizedMessage());

		}
	}

	@SuppressWarnings("rawtypes")
	protected TranBaseResult commonTranProcess(String reclassname, final Order order, final String processBizName, final CheckBeforeProcessService checkBeforeProcessService, final BeforeProcessInvokeService beforeProcessInvokeService,
                                               final ProcessInvokeService processInvokeService, final AfterProcessInvokeService successProcessInvokeService) {
		logger.info("-进入{} " + this.getClass().getName() + "  commonProcess processBizName={} order={} ", processBizName, order);
		TranBaseResult result = new TranBaseResult();
		try {
			if(!StringUtils.isEmpty(reclassname)){
				Class resultClass = Class.forName(reclassname);
				result = (TranBaseResult) resultClass.newInstance();
			}
		} catch (Exception e) {
			result = new TranBaseResult();
		}
		
		final Date nowDate = new Date();
		boolean isClear = false;
		if (TranDomainHolder.get() == null) {
			TranDomainHolder.set(new TranDomainContext<Order>(nowDate, order, null));
			isClear = true;
		}
		try {

			checkOrder(order);
			if (checkBeforeProcessService != null)
				checkBeforeProcessService.check();
			TranDomainHolder.get().addAttribute("reclassname", result.getClass().getName());
			result = transactionTemplate.execute(new TransactionCallback<TranBaseResult>() {

				@Override
				public TranBaseResult doInTransaction(TransactionStatus status) {
					String classname = TranDomainHolder.get().getAttribute("reclassname").toString();					
					TranBaseResult result = new TranBaseResult();
					try {
						if(!StringUtils.isEmpty(classname)){
							Class resultClass = Class.forName(classname);
							result = (TranBaseResult) resultClass.newInstance();
						}
					} catch (Exception e) {
						result = new TranBaseResult();
					}
					
					try {
						// 激活领域模型
						Domain domain = null;
						TranDomainHolder.get().addAttribute("result", result);
						if (beforeProcessInvokeService != null) {
							domain = beforeProcessInvokeService.before();
							logger.info("beforeProcessInvokeService.before():" + domain);
						}

						if (domain != null) {
							TranDomainHolder.get().setDomain(domain);
						}
						if (processInvokeService != null) {
							processInvokeService.process(domain);
							logger.info("processInvokeService.process():" + domain);
						}

						if (result.getTranResultEnum() == TranResultEnum.UN_KNOWN_EXCEPTION) {
							result.setSuccess(true);
						}
						addOperationLog(processBizName, processBizName, order.toString());
					} catch (TranException eex) {
						setTranException(status, result, eex, eex.getErrorMsg());

					} catch (DomainException e) {
						setDomainException(status, result, e, e.getErrorMsg());
					} catch (Exception e) {
						setDbException(status, result, e);
					} catch (Throwable e) {
						setDbException(status, result, e);
					}

					return result;
				}
			});
		} catch (TranException eex) {
			logger.error(eex.getErrorMsg(), eex);
			result.setSuccess(false);
			result.setTranResultEnum(eex.getResultCode());
			result.setMessage(processBizName + "异常[" + eex.getErrorMsg() + "]");

		} catch (Exception ex) {
			setUnknownException(result, ex);
		} catch (Throwable e) {
			setUnknownException(result, e);
		}
		if (result.isSuccess()) {
			try {
				if (successProcessInvokeService != null) {
					successProcessInvokeService.after(TranDomainHolder.get().getDomain());
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} catch (Throwable e) {
				logger.error(e.getMessage(), e);
			}
		}
		if (isClear) {
			TranDomainHolder.clear();
		}
		logger.info("-处理结束{}  commonProcess processBizName={} result={} ", this.getClass().getName(), processBizName, result);
		return result;
	}

	protected void setTranException(TransactionStatus status, TranBaseResult result, TranException eex, String errorMessage) {
		if (status != null)
			status.setRollbackOnly();
		result.setSuccess(false);
		result.setTranResultEnum(eex.getResultCode());
		result.setMessage(errorMessage);
		logger.error(eex.getLocalizedMessage() + " ==errMesaage=" + eex.getErrorMsg() + " == result  =" + result);
	}

	protected void setDomainException(TransactionStatus status, TranBaseResult result, DomainException eex, String errorMessage) {
		status.setRollbackOnly();
		result.setSuccess(false);
		result.setTranResultEnum(TranResultEnum.getByCode(eex.getDomainResult().getCode()));
		result.setMessage(errorMessage);
		logger.error(eex.getLocalizedMessage() + " ==errMesaage=" + eex.getErrorMsg(), eex);
	}

	protected void setUnknownException(TranBaseResult result, Throwable ex) {
		logger.error(ex.getLocalizedMessage(), ex);
		result.setSuccess(false);
		result.setTranResultEnum(TranResultEnum.UN_KNOWN_EXCEPTION);
	}

	protected void setDbException(TransactionStatus status, TranBaseResult result, Throwable e) {
		logger.error(e.getLocalizedMessage(), e);
		status.setRollbackOnly();
		result.setTranResultEnum(TranResultEnum.DATABASE_EXCEPTION);
		result.setSuccess(false);
	}

	protected void addOperationLog(String permissionName, String operationContent, String memo) {
		try {
			// 系统自动加操作日志
			/*
			 * OperationJournalAddOrder order = new OperationJournalAddOrder();
			 * order.setMemo(memo); SessionLocal local =
			 * SessionLocalManager.getSessionLocal(); if (local == null ||
			 * local.getUserId() == null) { order.setOperatorId(-1);
			 * order.setOperatorName("系统自动"); order.setOperatorIp("127.0.0.1");
			 * 
			 * } else { order.setOperatorId(local.getUserId());
			 * order.setOperatorName(local.getRealName()); if
			 * (local.getRealName() == null) {
			 * order.setOperatorName(local.getUserName()); }
			 * order.setOperatorIp(local.getRemoteAddr());
			 * 
			 * } if (YrdDomainHolder.get() != null) { OperationJournalAddOrder
			 * tempOrder = (OperationJournalAddOrder)
			 * YrdDomainHolder.get().getAttribute("loginLogOrder"); if
			 * (tempOrder != null) { order = tempOrder; }
			 * 
			 * } order.setBaseModuleName(AppConstantsUtil.getProductName());
			 * order.setPermissionName(permissionName);
			 * order.setOperationContent(operationContent);
			 * 
			 * operationJournalService.addOperationJournalInfo(order);
			 */
		} catch (Exception e) {
			logger.error("添加操作日志失败,失败原因：{}", e.getMessage(), e);
		}
	}

}
