package com.hx.domain;

import java.io.Serializable;

/**
 * @author hch
 */
public class ArticleKind implements Serializable {

    private static final long serialVersionUID = 1563948508148L;


    /**
     * 主键
     * <p>
     * isNullAble:0
     */
    private Long id;

    /**
     * 分类栏目名称
     * isNullAble:0
     */
    private String kindName;

    /**
     * 分类描述
     * isNullAble:1,defaultVal:
     */
    private String kindDesc;

    /**
     * 是否删除，1-是、0-否
     * isNullAble:1,defaultVal:0
     */
    private Integer isDeleted;

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

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getKindDesc() {
        return kindDesc;
    }

    public void setKindDesc(String kindDesc) {
        this.kindDesc = kindDesc;
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
