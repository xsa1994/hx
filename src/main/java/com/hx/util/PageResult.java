package com.hx.util;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huangch on 2019/7/24 19:13
 * description:
 *
 * @since JDK 1.6
 */
public class PageResult<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1622602182262632184L;

    private long totalCount;

    private int totalPage;

    private List<T> list;

    private T sum;

    public PageResult() {
    }

    public PageResult(long totalCount, List<T> list, int pageSize) {
        this.totalCount = totalCount;
        this.list = list;
        this.totalPage = this.setTotalPage(pageSize);
    }

    public PageResult(long totalCount, List<T> list, T sum, int pageSize) {
        this.totalCount = totalCount;
        this.list = list;
        this.sum = sum;
        this.totalPage = this.setTotalPage(pageSize);
    }

    private int setTotalPage(int pageSize) {
        return this.totalCount < 0L ? 0 : (int) (this.totalCount % (long) pageSize == 0L ? this.totalCount / (long) pageSize : this.totalCount / (long) pageSize + 1L);
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public T getSum() {
        return this.sum;
    }

    public void setSum(T sum) {
        this.sum = sum;
    }

}
