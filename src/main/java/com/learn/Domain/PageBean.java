package com.learn.Domain;

import java.util.List;

/**
 * @author DingTian
 * @version 1.0
 */
public class PageBean<T> {
    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", list=" + list +
                '}';
    }

    private int totalCount;
    private List<T> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
