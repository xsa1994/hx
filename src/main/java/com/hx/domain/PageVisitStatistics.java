package com.hx.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author hch
 */
@ApiModel(value = "网站访问统计汇总")
public class PageVisitStatistics implements Serializable {

    private static final long serialVersionUID = 1563948520146L;

    /**
     * 当前日期，格式：yyyyMMdd
     * isNullAble:0
     */
    @ApiModelProperty(value = "当前日期，格式：yyyy-MM-dd")
    private String curDate;

    /**
     * 网站总访问的PV
     * isNullAble:0,defaultVal:
     */
    @ApiModelProperty(value = "网站总访问的PV")
    private String visitPv;

    @ApiModelProperty(value = "网站总访问的UV")
    private String visitUv;

    /**
     * 星期(1~7)
     * isNullAble:0,defaultVal:0
     */
    @ApiModelProperty(value = "星期数(1~7)", example = "1")
    private Integer weekDays;

    public String getCurDate() {
        return curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate;
    }

    public String getVisitPv() {
        return visitPv;
    }

    public void setVisitPv(String visitPv) {
        this.visitPv = visitPv;
    }

    public String getVisitUv() {
        return visitUv;
    }

    public void setVisitUv(String visitUv) {
        this.visitUv = visitUv;
    }

    public Integer getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(Integer weekDays) {
        this.weekDays = weekDays;
    }
}
