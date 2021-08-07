package com.limaolin.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 包名:com.itheima.entry
 *
 * @author Leevi
 * 日期2020-07-18  12:03
 */
public class PageBean<T> implements Serializable {
    //总条数
    private Long totalSize;
    //总页数
    private Long totalPage;
    //当前页数
    private Long currentPage;
    //每页条数
    private Integer pageSize;
    //当前页的数据集合
    private List<T> list;

    @Override
    public String toString() {
        return "PageBean{" +
                "totalSize=" + totalSize +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", list=" + list +
                '}';
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
