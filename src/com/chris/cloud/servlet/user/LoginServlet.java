package com.chris.cloud.servlet.user;

import com.chris.cloud.dao.UserDao;
import com.chris.cloud.dao.impl.UserDaoImpl;
import com.chris.cloud.entity.User;
import com.chris.cloud.util.MD5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        UserDao userDao = new UserDaoImpl();
        try {
            User user = userDao.getOneUser(userName, MD5.md5(password));
            if (user != null) {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("userId", user.getId());
                response.sendRedirect("/cloudMain");
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
