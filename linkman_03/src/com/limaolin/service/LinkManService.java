package com.limaolin.service;

import com.limaolin.dao.LinkManDao;
import com.limaolin.entry.PageBean;
import com.limaolin.pojo.LinkMan;

import java.sql.SQLException;
import java.util.List;

/**
 * 包名:com.itheima.service
 *
 * @author Leevi
 * 日期2020-07-23  11:55
 */
public class LinkManService {
    private LinkManDao linkManDao = new LinkManDao();
    public List<LinkMan> findAll() throws SQLException {
        return linkManDao.findAll();
    }

    public void add(LinkMan linkMan) throws SQLException {
        linkManDao.add(linkMan);
    }

    public void deleteById(Integer id) throws SQLException {
        linkManDao.deleteById(id);
    }

    public LinkMan findById(Integer id) throws SQLException {
        return linkManDao.findById(id);
    }

    public void update(LinkMan linkMan) throws SQLException {
        linkManDao.update(linkMan);
    }

    public PageBean<LinkMan> findByPage(Long currentPage, Integer pageSize) throws SQLException {
        //1. 创建PageBean对象
        PageBean<LinkMan> pageBean = new PageBean<>();
        //2. 设置pageBean的属性
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);

        // 调用dao层的方法，获取总数据条数
        Long totalSize = linkManDao.findTotalSize();
        pageBean.setTotalSize(totalSize);

        //计算出总页数
        Long totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
        pageBean.setTotalPage(totalPage);

        //调用dao层的方法获取当前页的联系人集合
        List<LinkMan> linkManList = linkManDao.findPageList(currentPage,pageSize);
        pageBean.setList(linkManList);
        return pageBean;
    }
}
