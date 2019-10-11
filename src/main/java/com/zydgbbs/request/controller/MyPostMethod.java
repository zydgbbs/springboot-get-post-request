package com.zydgbbs.request.controller;

import com.zydgbbs.request.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MyPostMethod {
    //定义cookie全局变量，供需要携带cookie的接口使用
    Cookie cookie;
    //创建users对象，getusers接口会用到该对象创建用户登录信息。
    User users;
    //定义一个login的post方法，然后返回cookie信息
    @RequestMapping(value = "/zydgbbs/login", method = RequestMethod.POST)
    public String Login(HttpServletResponse response,
                        @RequestParam("name") String name,
                        @RequestParam("password") String password) {

        //如果用户名和密码成功，就添加cookie信息，然后添加到response中，然后成功信息
        if (name.equals("zydgbbs") && password.equals("123456")) {
            cookie = new Cookie("token", "1234567890");
            //在response中添加cookie并返回cookie
            response.addCookie(cookie);
            return "返回cookie，登录成功";
        }
        return "success";
    }

    //用postman请求Login接口，会自动存储cookie，再请求getusers接口时会自动把cookie带上，所以请求时不需要再单独处理cookie
    //携带cookie信息请求接口，同时返回用户列表的post接口
    @RequestMapping(value = "/zydgbbs/postResponseUsers", method = RequestMethod.POST)
    public User getusers(HttpServletRequest request,
                         @RequestParam("name") String name,
                         @RequestParam("password") String password) {

        //上一个接口返回的cookie，本接口去获取
        Cookie[] cookie=request.getCookies();
        for(Cookie cookie1 : cookie){
            if(cookie1.getName().equals("token") && cookie1.getValue().equals("1234567890") &&
                    name.equals("zydgbbs") && password.equals("123456")){
                //接口的请求参数是Loginname和Password
                //接口的返回参数是Loginname、Password、Name、Age、Sex
                //请求和返回的格式都是json
                //给user类中的属性赋值并返回
                users=new User();
                users.setName("zydgbbs");
                users.setPassword("123456");
                users.setName("sunqihua");
                users.setAge(28);
                users.setGender("男");
                return users;
            }
        }
        return null;

    }
}
