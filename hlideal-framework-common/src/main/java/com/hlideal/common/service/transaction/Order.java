package com.hlideal.common.service.transaction;

import java.io.Serializable;

public abstract interface Order extends Serializable
{
  public abstract void check();

  public abstract String getGid();

  public abstract void setGid(String paramString);
}