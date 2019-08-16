package com.hx.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author hch
 */
@ApiModel(value = "网站访问PV记录")
public class PageVisitRecord implements Serializable {

    private static final long serialVersionUID = 1563948520146L;


    /**
     * 主键
     * <p>
     * isNullAble:0
     */
    @ApiModelProperty(value = "主键ID", example = "1")
    private Long id;

    /**
     * 当前日期，格式：yyyyMMdd
     * isNullAble:0
     */
    @ApiModelProperty(value = "当前日期，后端生成")
    private String curDate;

    /**
     * 用户ID(访问用户唯一标识)
     * isNullAble:0
     */
    @ApiModelProperty(value = "用户ID(访问用户唯一标识)")
    private String userId;

    /**
     * 访问的IP
     * isNullAble:0,defaultVal:
     */
    @ApiModelProperty(value = "访问的IP，由后端从header中获取")
    private String visitIp;

    /**
     * 访问的userAgent信息
     * isNullAble:0,defaultVal:
     */
    @ApiModelProperty(value = "访问的userAgent信息，由后端从header中获取")
    private String userAgent;

    /**
     * 访问的页面ID
     * isNullAble:0,defaultVal:0
     */
    @ApiModelProperty(value = "访问的页面ID", example = "1")
    private Integer pageId;

    /**
     * 访问的文章ID
     * isNullAble:0,defaultVal:0
     */
    @ApiModelProperty(value = "访问的文章ID", example = "1")
    private Integer articleId;

    /**
     * 创建时间
     * isNullAble:0,defaultVal:CURRENT_TIMESTAMP
     */
    @ApiModelProperty(value = "创建时间，后端生成")
    private String gmtCreate;

    /**
     * 修改时间
     * isNullAble:0,defaultVal:CURRENT_TIMESTAMP
     */
    @ApiModelProperty(value = "修改时间，后端生成")
    private String gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurDate() {
        return curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVisitIp() {
        return visitIp;
    }

    public void setVisitIp(String visitIp) {
        this.visitIp = visitIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
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
