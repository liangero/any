package com.any.pub;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * tyl
 * Created by avaio on 2016/12/23.
 */
public class Page<T> {
    private Integer pageIndex;
    private Integer pageSize;
    private List<PageOrder> pageOrder = new ArrayList<PageOrder>();
    private List<T> recordList;
    private Integer total;

    public int getStart() {
        Assert.notNull(pageIndex, "pageIndex can't be null");
        Assert.isTrue(pageIndex >= 1, "pageIndex should more than 1");
        Assert.notNull(pageSize, "pageSize can't be null");
        return (pageIndex - 1) * pageSize;
    }


    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<PageOrder> getPageOrder() {
        return pageOrder;
    }

    public void setPageOrder(List<PageOrder> pageOrder) {
        this.pageOrder = pageOrder;
    }

    public List<T> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<T> recordList) {
        this.recordList = recordList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
