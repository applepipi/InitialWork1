package com.xiaozl.initialwork1.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiaozl.initialwork1.entity.User;
import com.xiaozl.initialwork1.service.UserService;

/**
 * @author xiaozl
 * @date 2017/11/20
 */
@Controller
@RequestMapping(value = "")
public class IndexController {

    @Resource
    private UserService userService;

    @RequestMapping(value = {"", "/", "login"}, method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request)
    {
        //If session have attribute "user", jump to index page, else jump to login page.
        if (request.getSession().getAttribute("userName") != null){
            return "index";
        }
        else {
            return "login";
        }
    }

    //Login
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(User user, Model model, HttpServletRequest request) throws Exception{
        try {
            //If pass, set attribute to session, then redirect to index page.
            if (userService.checkLogin(user)) {
                request.getSession().setAttribute("userName", user.getUserName());
                model.addAttribute("userName",user.getUserName());
                return "index";
            }
            //If not pass, send error attribute.
            else{
                model.addAttribute("login_err", "登录失败!");
                return "login";
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    //logout
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) throws Exception {
        request.getSession().removeAttribute("userName");
        return "login";
    }

    //toSignIn
    @RequestMapping(value = "signIn", method = RequestMethod.GET)
    public String toSignIn() throws Exception {
        return "signIn";
    }

    //signIn
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public String signIn(User user, Model model) throws Exception {
        try {
            User u = userService.newUser(user);
            if(u != null)
                return "login";
            model.addAttribute("signIn_fail","用户名和密码不能为空 或 用户名已存在！");
        } catch (Exception e) {
            model.addAttribute("signIn_err", "注册失败!");
        }
        return "signIn";
    }

    //toIndex
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String toIndex() throws Exception {
        return "index";
    }
}