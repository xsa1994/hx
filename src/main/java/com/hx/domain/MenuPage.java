package com.hx.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author hch
 */
@ApiModel(value = "单页管理")
public class MenuPage implements Serializable {

    private static final long serialVersionUID = 1563948512057L;

    /**
     * 主键
     * <p>
     * isNullAble:0
     */
    @ApiModelProperty(value = "页面ID，更新、查询、删除的时候需要传入该ID", example = "1")
    private Long id;

    /**
     * 文章分类栏目ID
     * isNullAble:0
     */
    @ApiModelProperty(value = "文章分类栏目ID", example = "1")
    private Long kindId;

    /**
     * 页面名称
     * isNullAble:0
     */
    @ApiModelProperty(value = "页面名称")
    private String pageName;

    /**
     * 页面关键词，多个用逗号分隔
     * isNullAble:1,defaultVal:
     */
    @ApiModelProperty(value = "页面关键词，多个用逗号分隔")
    private String pageKeyWord;

    /**
     * 页面描述
     * isNullAble:1,defaultVal:
     */
    @ApiModelProperty(value = "页面描述")
    private String pageDesc;

    /**
     * 页面图片URL，多个用逗号分隔
     * isNullAble:1,defaultVal:
     */
    @ApiModelProperty(value = "页面图片URL，多个用逗号分隔")
    private String pageImg;

    /**
     * 页面内容
     * isNullAble:1
     */
    @ApiModelProperty(value = "页面内容")
    private String pageContent;

    /**
     * 页面模板
     * isNullAble:1,defaultVal:
     */
    @ApiModelProperty(value = "页面模板")
    private String pageTemplate;

    /**
     * 页面排序
     * isNullAble:1,defaultVal:0
     */
    @ApiModelProperty(value = "页面排序(值越大排序越靠前，若排序值一样，则按ID排序)", example = "1")
    private Integer pageSort;

    /**
     * 展示状态,1-展示、0-不展示
     * isNullAble:1,defaultVal:1
     */
    @ApiModelProperty(value = "展示状态,1-展示、0-不展示", example = "1")
    private Integer displayStatus;

    /**
     * 是否删除，1-是、0-否
     * isNullAble:1,defaultVal:0
     */
    @ApiModelProperty(value = "是否删除，1-是、0-否(暂时无用)", example = "1")
    private Integer isDeleted;

    /**
     * 创建时间
     * isNullAble:0,defaultVal:CURRENT_TIMESTAMP
     */
    @ApiModelProperty(value = "页面创建时间，由后端生成，yyyy-MM-dd HH:mm:ss格式")
    private String gmtCreate;

    /**
     * 修改时间
     * isNullAble:0,defaultVal:CURRENT_TIMESTAMP
     */
    @ApiModelProperty(value = "页面最近修改时间，由后端生成，yyyy-MM-dd HH:mm:ss格式")
    private String gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKindId() {
        return kindId;
    }

    public void setKindId(Long kindId) {
        this.kindId = kindId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageKeyWord() {
        return pageKeyWord;
    }

    public void setPageKeyWord(String pageKeyWord) {
        this.pageKeyWord = pageKeyWord;
    }

    public String getPageDesc() {
        return pageDesc;
    }

    public void setPageDesc(String pageDesc) {
        this.pageDesc = pageDesc;
    }

    public String getPageImg() {
        return pageImg;
    }

    public void setPageImg(String pageImg) {
        this.pageImg = pageImg;
    }

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    public String getPageTemplate() {
        return pageTemplate;
    }

    public void setPageTemplate(String pageTemplate) {
        this.pageTemplate = pageTemplate;
    }

    public Integer getPageSort() {
        return pageSort;
    }

    public void setPageSort(Integer pageSort) {
        this.pageSort = pageSort;
    }

    public Integer getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(Integer displayStatus) {
        this.displayStatus = displayStatus;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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
