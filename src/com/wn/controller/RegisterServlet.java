package com.wn.controller;

import com.alibaba.fastjson.JSON;
import com.wn.bean.R;
import com.wn.bean.User;
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
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

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
        boolean flag = userService.register(user);

        // 03 回写数据

        String jsonString = JSON.toJSONString(flag ? R.registerSuccess():R.registerFail());
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}


class AddUser{
    public static void main(String[] args) throws IllegalAccessException {
        //反射获取Class对象
        //Class<? extends User> clazz= User.class;
        //上面那段代码的含义
        Class<User> clazz= User.class;
        Map<String,Object> map=new HashMap<>();
        mapToBean(map,clazz);
    }

    static User mapToBean(Map<String,Object> map,Class<User> clazz) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        User user = new User();
        //通过反射遍历属性名
        for (Field field : fields) {
            String key = field.getName();
            Class<?> type = field.getType();
            Object o = map.get(key);
            //对user对象的特定属性进行赋值
            field.set(user,o);
        }
        return user;
    }
}
