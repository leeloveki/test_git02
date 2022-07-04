package com.wn.controller;

import com.alibaba.fastjson.JSON;
import com.wn.bean.R;
//import com.wn.bean.Student;
import com.wn.bean.User;
//import com.wn.service.StudentService;
//import com.wn.service.impl.StudentServiceImpl;
import com.wn.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yellow Docter
 * @date 2022 -06-30
 * @desc
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");

        // 01 拿到数据
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();


        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("user = " + user);

        // 02 调用业务
        UserServiceImpl userService = new UserServiceImpl();
        boolean flag = userService.login(user);

        // 03 回写数据

        String jsonString = JSON.toJSONString(flag ? R.loginSuccess():R.loginFail());
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

