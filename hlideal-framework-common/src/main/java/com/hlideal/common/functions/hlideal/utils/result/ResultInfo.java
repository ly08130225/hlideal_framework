package com.hlideal.common.functions.hlideal.utils.result;

import java.io.Serializable;
import com.hlideal.common.functions.hlideal.utils.lang.exception.ResultException;

public abstract interface ResultInfo extends Serializable
{
  public abstract Status getStatus();

  public abstract String getCode();

  public abstract String getDescription();

  public abstract ResultException getResultException();
}