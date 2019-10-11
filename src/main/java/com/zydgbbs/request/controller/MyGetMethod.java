package com.zydgbbs.request.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class MyGetMethod {

    //模拟get请求
    @RequestMapping(value = "/zydgbbs/get",method = RequestMethod.GET)
    public String get(){
        return "success without cookies.";
    }

    //模拟get请求返回cookie信息
    @RequestMapping(value = "/zydgbbs/getResponseCookies",method = RequestMethod.GET)
    public String getResponseCookies(HttpServletResponse response){
        Cookie userCookie=new Cookie("name","password");
        response.addCookie(userCookie);
        return "success with cookies.";
    }

    //模拟get请求携带cookie信息
    @RequestMapping(value = "/zydgbbs/getwithCookies",method = RequestMethod.GET)
    public String getwithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            return "必须携带cookie信息";
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("name") && cookie.getValue().equals("password")){
                return "用户名和密码匹配成功";
            }
        }
        return "cookie中的用户名和密码无效";
    }

    //模拟get请求携带参数一
    @RequestMapping(value = "/zydgbbs/getwithParam",method = RequestMethod.GET)
    public Map<String,String> getwithParam(@RequestParam("name") String name,
                                           @RequestParam("password") String password){
        Map<String,String> map=new HashMap<>();
        map.put("method","requestParam");
        map.put("name",name);
        map.put("password",password);
        return map;

    }

    //模拟get请求携带参数 @PathVariable
    @RequestMapping(value = "/zydgbbs/getwithParam/{name}/{password}",method = RequestMethod.GET)
    public Map<String,String> getwithParamtwo(@PathVariable("name") String name,
                                              @PathVariable("password") String password){
        Map<String,String> map=new HashMap<>();
        map.put("method","PathVariable");
        map.put("name",name);
        map.put("password",password);
        return map;
    }

}
