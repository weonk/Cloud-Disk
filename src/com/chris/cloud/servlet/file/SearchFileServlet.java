package com.chris.cloud.servlet.file;

import com.chris.cloud.dao.FileDao;
import com.chris.cloud.dao.impl.FileDaoImpl;
import com.chris.cloud.entity.FileList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchFile")
public class SearchFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyWord = request.getParameter("keyWord");

        FileDao fileDao = new FileDaoImpl();
        try {
            List<FileList> fileLists = fileDao.getFilesByKey(keyWord);
            if (fileLists != null) {
                for (FileList f: fileLists
                ) {
                    f.setName(f.getName().substring(f.getName().indexOf("_")+1));
                }
            }
            request.setAttribute("fileLists", fileLists);
            request.getRequestDispatcher("searchFile.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("服务器繁忙！请稍后再试！");
            response.sendRedirect("cloudMain");
        }
    }
}
