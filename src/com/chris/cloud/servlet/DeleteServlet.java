package com.chris.cloud.servlet;

import com.chris.cloud.dao.FileDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("html/text;charset=utf-8");

        PrintWriter out = response.getWriter();
        //获取要删除文件的路径
        String src = request.getParameter("info");
        try {
            //int num = FileDao.deleteFile(src);
            //out.println(num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
