package com.hx.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hch
 */
public class OperateRecord implements Serializable {

    private static final long serialVersionUID = 1563948516108L;


    /**
     * 主键
     * <p>
     * isNullAble:0
     */
    private Long id;

    /**
     * 当前日期，格式：yyyyMMdd
     * isNullAble:0
     */
    private Date curDate;

    /**
     * 操作的接口
     * isNullAble:0
     */
    private String interfaceUrl;

    /**
     * 操作账号ID
     * isNullAble:0
     */
    private Long operateAccount;

    /**
     * 操作的IP
     * isNullAble:0,defaultVal:
     */
    private String operateIp;

    /**
     * 操作的入参
     * isNullAble:1
     */
    private String params;

    /**
     * 创建时间
     * isNullAble:0,defaultVal:CURRENT_TIMESTAMP
     */
    private String gmtCreate;

    /**
     * 修改时间
     * isNullAble:0,defaultVal:CURRENT_TIMESTAMP
     */
    private String gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCurDate() {
        return curDate;
    }

    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }

    public Long getOperateAccount() {
        return operateAccount;
    }

    public void setOperateAccount(Long operateAccount) {
        this.operateAccount = operateAccount;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }
}
