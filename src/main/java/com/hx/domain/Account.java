package com.hx.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author hch
 */
@ApiModel(value = "用户管理")
public class Account implements Serializable {

    private static final long serialVersionUID = 1563948503656L;


    /**
     * 主键
     * <p>
     * isNullAble:0
     */
    @ApiModelProperty(value = "账号ID，更新、查询、删除的时候需要传入该ID", example = "1")
    private Long id;

    /**
     * 密码
     * isNullAble:0
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 用户名
     * isNullAble:1
     */
    @ApiModelProperty(value = "用户名")
    private String accountName;

    /**
     * 手机号
     * isNullAble:0
     */
    @ApiModelProperty(value = "手机号", example = "13123213213")
    private Long mobilePhone;

    /**
     * 邮箱账号
     * isNullAble:1,defaultVal:
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 联系人姓名
     * isNullAble:1,defaultVal:
     */
    @ApiModelProperty(value = "联系人姓名")
    private String linkName;

    /**
     * 证件号
     * isNullAble:1,defaultVal:
     */
    @ApiModelProperty(value = "证件号")
    private String idNum;

    /**
     * QQ
     * isNullAble:1,defaultVal:
     */
    @ApiModelProperty(value = "QQ")
    private String linkQq;

    /**
     * 账号状态，1-有效、0-无效
     * isNullAble:1,defaultVal:1
     */
    @ApiModelProperty(value = "账号状态，可不传，默认是有效的", example = "1")
    private Integer accountStatus;

    /**
     * 角色类型(1-超级管理员、2-普通账号)
     * isNullAble:1,defaultVal:1
     */
    @ApiModelProperty(value = "角色类型，1-超级管理员、2-普通管理员", example = "1")
    private Integer roleType;

    /**
     * 账号所属的系统，1-管理端、2-官网web
     * isNullAble:1,defaultVal:1
     */
    @ApiModelProperty(value = "账号所属系统，1-管理端、2-官网web，默认1", example = "1")
    private Integer belongSystem;

    /**
     * 创建时间
     * isNullAble:0,defaultVal:CURRENT_TIMESTAMP
     */
    @ApiModelProperty(value = "账号创建时间，由后台生成，yyyy-MM-dd HH:mm:ss格式")
    private String gmtCreate;

    /**
     * 修改时间
     * isNullAble:0,defaultVal:CURRENT_TIMESTAMP
     */
    @ApiModelProperty(value = "账号最近修改时间，由后台生成，yyyy-MM-dd HH:mm:ss格式")
    private String gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Long getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(Long mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getLinkQq() {
        return linkQq;
    }

    public void setLinkQq(String linkQq) {
        this.linkQq = linkQq;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getBelongSystem() {
        return belongSystem;
    }

    public void setBelongSystem(Integer belongSystem) {
        this.belongSystem = belongSystem;
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
