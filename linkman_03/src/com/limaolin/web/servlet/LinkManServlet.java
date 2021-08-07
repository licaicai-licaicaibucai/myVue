package com.limaolin.web.servlet;

import com.limaolin.entry.PageBean;
import com.limaolin.entry.ResultBean;
import com.limaolin.pojo.LinkMan;
import com.limaolin.service.LinkManService;
import com.limaolin.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 包名:${PACKAGE_NAME}
 *
 * @author Leevi
 * 日期2020-07-23  11:55
 */
@WebServlet("/linkman")
public class LinkManServlet extends HttpServlet {
    private LinkManService linkManService = new LinkManService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取请求参数action的值
        String action = request.getParameter("action");
        try {
            Class clazz = this.getClass();
            //根据方法名获取方法
            Method method = clazz.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //调用方法
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultBean resultBean = new ResultBean(true);
        try {
            //1. 调用业务层的方法，获取所有联系人信息
            List<LinkMan> linkManList = linkManService.findAll();
            //2. 将查询到的数据封装到ResultBean对象
            resultBean.setData(linkManList);
        } catch (Exception e) {
            e.printStackTrace();
            //服务器异常
            resultBean.setFlag(false);
            resultBean.setErrorMsg("获取联系人列表失败");
        }

        //使用JsonUtil将resultBean对象转换成json字符串输出到客户端
        JsonUtils.printResult(response,resultBean);
    }

    /**
     * 添加联系人
     * @param request
     * @param response
     * @throws IOException
     */
    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultBean resultBean = new ResultBean(true);
        try {
            //1. 获取客户端提交的联系人的信息,使用JsonUtils获取
            LinkMan linkMan = JsonUtils.parseJSON2Object(request, LinkMan.class);
            //2. 调用业务层的方法添加联系人信息
            linkManService.add(linkMan);
        } catch (Exception e) {
            e.printStackTrace();

            resultBean.setFlag(false);
            resultBean.setErrorMsg("添加联系人失败");
        }

        //将resultBean转换成json字符串输出到客户端
        JsonUtils.printResult(response,resultBean);
    }

    /**
     * 删除联系人
     * @param request
     * @param response
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultBean resultBean = new ResultBean(true);
        try {
            //1. 获取要删除的联系人的id
            Integer id = Integer.valueOf(request.getParameter("id"));
            //2. 调用业务层的方法删除联系人
            linkManService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            resultBean.setFlag(false);
            resultBean.setErrorMsg("删除失败");
        }

        //将resultBean对象转换成json输出给客户端
        JsonUtils.printResult(response,resultBean);
    }

    /**
     * 数据回显
     * @param request
     * @param response
     * @throws IOException
     */
    private void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultBean resultBean = new ResultBean(true);
        try {
            //1. 获取联系人的id
            Integer id = Integer.valueOf(request.getParameter("id"));
            //2. 调用业务层的方法，根据id获取联系人信息
            LinkMan linkMan = linkManService.findById(id);
            //3. 将响应数据封装到resultBean对象
            resultBean.setData(linkMan);
        } catch (Exception e) {
            e.printStackTrace();

            resultBean.setFlag(false);
            resultBean.setErrorMsg("获取联系人失败");
        }

        //将resultBean对象转换成json输出给客户端
        JsonUtils.printResult(response,resultBean);
    }

    /**
     * 修改联系人
     * @param request
     * @param response
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultBean resultBean = new ResultBean(true);
        try {
            //1. 使用JsonUtils获取请求参数封装到LinkMan对象
            LinkMan linkMan = JsonUtils.parseJSON2Object(request, LinkMan.class);
            //2. 调用业务层的方法修改联系人
            linkManService.update(linkMan);
        } catch (Exception e) {
            e.printStackTrace();

            resultBean.setFlag(false);
            resultBean.setErrorMsg("修改失败");
        }

        //将resultBean对象转换成json输出给客户端
        JsonUtils.printResult(response,resultBean);
    }

    /**
     * 分页查看联系人
     * @param request
     * @param response
     */
    private void findByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultBean resultBean = new ResultBean(true);
        try {
            //1. 获取请求参数currentPage以及pageSize
            Long currentPage = Long.valueOf(request.getParameter("currentPage"));
            Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
            //2. 调用业务层的方法，查询分页信息
            PageBean<LinkMan> pageBean = linkManService.findByPage(currentPage,pageSize);

            resultBean.setData(pageBean);
        } catch (Exception e) {
            e.printStackTrace();

            resultBean.setFlag(false);
            resultBean.setErrorMsg("分页查询失败");
        }

        //将resultBean对象转换成json输出给客户端
        JsonUtils.printResult(response,resultBean);
    }
}
