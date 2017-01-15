package com.hlideal.framework.admin.excle_model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Intellij idea
 * User: liu.y
 * Date: 2016/12/28 0028 15:19
 * Description:
 * To change this template use File | Setting | File and Code Templates
 */
public class SysroleExcel implements Serializable {
    private String id;
    private String name;
    private String createName;
    private Date createTime;

    @Override
    public String toString() {
        return "SysroleExcel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createName='" + createName + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
