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
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (password.equals(confirmPassword)) {
            UserDao userDao = new UserDaoImpl();
            try {
                User user = new User();
                user.setUserName(userName);
                user.setPassword(MD5.md5(password));
                if (userDao.insertUser(user) == 1) {
                    response.sendRedirect("index.jsp");
                } else {
                    response.sendRedirect("register.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("register.jsp");
        }
    }
}
