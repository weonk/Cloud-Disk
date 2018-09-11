package com.chris.cloud.servlet.file;

import com.chris.cloud.dao.FileDao;
import com.chris.cloud.dao.LogDao;
import com.chris.cloud.dao.impl.FileDaoImpl;
import com.chris.cloud.dao.impl.LogDaoImpl;
import com.chris.cloud.entity.FileList;
import com.chris.cloud.entity.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/deleteFile")
public class DeleteFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileId = request.getParameter("fileId");
        int userId = (Integer) request.getSession().getAttribute("userId");
        FileDao fileDao = new FileDaoImpl();
        try {
            FileList fileList = fileDao.getOneFileByIndex(Integer.parseInt(fileId));
            String path = fileList.getSrc();
            String fileName = fileList.getName();
            File file = new File(path + "\\" + fileName);
            if (file.exists()) {
                boolean flag = file.delete();
                //获取删除时的时间
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                long now = System.currentTimeMillis();
                String date = simpleDateFormat.format(now);
                if (flag) {
                    //从文件数据库删除
                    fileDao.deleteFile(Integer.parseInt(fileId));
                    System.out.println("删除成功！");
                    //增加一条记录
                    LogDao logDao = new LogDaoImpl();
                    Log log = new Log();
                    log.setUserId(userId);
                    log.setFileId(Integer.parseInt(fileId));
                    log.setAction(-1);
                    log.setData(date);
                    logDao.insertLog(log);
                    //跳转
                    response.sendRedirect("/cloudMain");
                } else {
                    request.setAttribute("message", "服务器繁忙，删除失败！！");
                    request.getRequestDispatcher("/message.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("message", "文件不存在！！");
                request.getRequestDispatcher("/message.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
