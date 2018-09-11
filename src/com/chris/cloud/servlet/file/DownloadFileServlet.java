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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

@WebServlet("/downloadFile")
public class DownloadFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileId = request.getParameter("fileId");

        FileDao fileDao = new FileDaoImpl();
        try {
            FileList fileList = fileDao.getOneFileByIndex(Integer.parseInt(fileId));
            String fileName = fileList.getName();
            String path = fileList.getSrc();
            System.out.println(fileName);
            System.out.println(path);
            File file = new File(path + "\\" + fileName);

            //如果文件不存在
            if(!file.exists()){
                request.setAttribute("message", "您要下载的资源已被删除！！");
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                return;
            }

            //处理文件名
            String realname = fileName.substring(fileName.indexOf("_")+1);
            //设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
            //读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(path + "\\" + fileName);
            //创建输出流
            OutputStream out = response.getOutputStream();
            //创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
            //循环将输入流中的内容读取到缓冲区当中
            while((len=in.read(buffer))>0){
                //输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
            //关闭文件输入流
            in.close();
            //关闭输出流
            out.close();
            //获取时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long now = System.currentTimeMillis();
            String date = simpleDateFormat.format(now);
            //新增一条记录
            LogDao logDao = new LogDaoImpl();
            Log log = new Log();
            HttpSession httpSession = request.getSession();
            log.setUserId((Integer) httpSession.getAttribute("userId"));
            log.setFileId(Integer.parseInt(fileId));
            log.setAction(0);
            log.setData(date);
            logDao.insertLog(log);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "下载失败！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }

    }
}
