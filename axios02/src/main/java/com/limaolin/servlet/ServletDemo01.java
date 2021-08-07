package com.limaolin.servlet;

import com.limaolin.utils.JsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet("/demo01")
public class ServletDemo01 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //获取"name=value&name=value"类型的参数
        /*String id = request.getParameter("id");
        String username = request.getParameter("username");*/

        //获取json类型的请求参数
        Map user = JsonUtils.parseJSON2Object(request, Map.class);

        //int num = 10/0;

        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().write("你好世界:"+user.get("id")+","+user.get("username"));
    }
}
