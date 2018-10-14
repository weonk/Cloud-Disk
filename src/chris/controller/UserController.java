package com.chris.controller;

import com.chris.pojo.User;
import com.chris.pojo.UserCustom;
import com.chris.pojo.UserQueryVo;
import com.chris.service.UserService;
import com.chris.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register(HttpServletResponse response, User user, Model model) throws Exception{
        if (userService.insertUser(user) == 1) {
            model.addAttribute("userName",user.getUserName());
            return "index";
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("<script>alert('服务器繁忙，注册失败！');window.history.go(-1);</script>");
        return "index";
    }

    @RequestMapping("/login")
    public void login(HttpServletResponse response, HttpServletRequest request, UserQueryVo userQueryVo) throws Exception {
        UserCustom userCustom = userService.findUserByUserNameAndPassword(userQueryVo);
        if (userCustom != null) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", userCustom);
            request.getRequestDispatcher("cloudMain.action").forward(request, response);
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("<script>alert('用户名或密码错误！');window.history.go(-1);</script>");
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "index";
    }

    @RequestMapping("/modifyUserInfo")
    public String modifyUserInfo(HttpServletRequest request, UserQueryVo userQueryVo) throws Exception {
        HttpSession httpSession = request.getSession();
        userQueryVo.getUserCustom().setId(((UserCustom) httpSession.getAttribute("user")).getId());
        userService.UpdateUserInfo(userQueryVo);
        httpSession.setAttribute("user",userService.findUserById(userQueryVo));
        return "forward:cloudMain.action";
    }

    @RequestMapping("modifyPassword")
    public String modifyPassword(HttpServletRequest request, UserQueryVo userQueryVo) throws Exception {
        userQueryVo.getUserCustom().setPassword(MD5.md5(userQueryVo.getUserCustom().getPassword()));
        userQueryVo.getUserCustom().setId(((UserCustom) request.getSession().getAttribute("user")).getId());
        userService.UpdateUserPassword(userQueryVo);
        return "forward:logout.action";
    }

    @RequestMapping(value = "/userNameCheck", method = {RequestMethod.POST})
    public @ResponseBody UserCustom userNameCheck(UserQueryVo userQueryVo) throws Exception {
        return userService.findUserByUserName(userQueryVo);
    }

    @RequestMapping(value = "/passwordCheck", method = {RequestMethod.POST})
    public @ResponseBody String passwordCheck(String oldPassword, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        if (((UserCustom) httpSession.getAttribute("user")).getPassword().equals(MD5.md5(oldPassword))) {
            return "success";
        }
        return "error";
    }

}
