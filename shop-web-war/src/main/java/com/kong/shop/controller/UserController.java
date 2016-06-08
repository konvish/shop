/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  UserController.java 2016-01-18 15:55:26 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.User;
import com.kong.shop.service.IShoppingCartService;
import com.kong.shop.service.common.MD5Util;
import com.kong.shop.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/10.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    IShoppingCartService shoppingCartService;

    @ResponseBody
    @RequestMapping(value = "/addUser.do")
    public HashMap<String, Object> addUser(HttpServletRequest request, HttpServletResponse response, User user) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Map condition = new HashMap();
        condition.put("phone", user.getPhone());
        condition.put("email", user.getEmail());
        User is = userService.queryOne(condition);
        if (is != null) {
            map.put("msg", "该手机号码或者email已经注册过了。");
            return map;
        }
        String pass = user.getPassword();
        user.setPassword(MD5Util.md5CK(pass));
        user.setJfen(1);
        user.setLevel(1);
        int flag = userService.add(user);
        map.put("user", user);
        map.put("msg", "注册成功！");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUser.do")
    public HashMap<String, Object> deleteUser(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Map condition = new HashMap();
        condition.put("status", 8);
        condition.put("id", id);
        int re = userService.updateMap(condition);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyUser.do")
    public HashMap<String, Object> modifyUser(HttpServletRequest request, HttpServletResponse response, User user) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = userService.update(user);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getUser.do")
    public HashMap<String, Object> getUser(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        User user = userService.fetch(id);
        map.put("user", user);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryUserPage.do")
    public Map<String, Object> queryUserPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<User> userList = new ArrayList<User>();
        Map<String, Object> condition = new HashMap<String, Object>();

        if (user != null) {
            if (user.getName() != null && user.getName() != "") {
                SearchField searchField = new SearchField();
                searchField.setField("name");
                searchField.setOp("like");
                searchField.setData("%" + user.getName() + "%");
                condition.put("name", searchField);
            }
        }

        int current = 0;
        int size = 10;
        if (pageSize != null || currentPage != null) {
            current = Integer.parseInt(currentPage) - 1;
            size = Integer.parseInt(pageSize);
        }
        try {
            userList = userService.queryPage(condition, current * size, size);
            map.put("userList", userList);
            map.put("currentPage", current);
            map.put("totalRecord", userService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 用户登录，返回用户信息以及购物车商品数量
     * @param request
     * @param response
     * @param account
     * @param password
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login.do")
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response, String account, String password, String code) {
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> condition = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        int flag=0;
        if (session.getAttribute("randCode")!=null){
            if (!(code.equalsIgnoreCase(session.getAttribute("randCode").toString()))) {
                map.put("msg", "验证码不正确");
                map.put("flag",flag);
                return map;
            }
        }else if (session.getAttribute("randCode")==null){
            map.put("msg", "验证码过期");
            map.put("flag",flag);
            return map;
        }
        condition.put("phone", account);
        condition.put("password", MD5Util.md5CK(password));
        User user = userService.queryOne(condition);
        if (user == null) {
            condition.put("phone", null);
            condition.put("email", account);
            user = userService.queryOne(condition);
        }
        if (user == null) {
            map.put("msg", "用户不存在！");
            map.put("flag",flag);
            return map;
        }
        Map<String,Object> count = new HashMap<String, Object>();
        SearchField search = new SearchField();
        search.setField("status");
        search.setOp("!=");
        search.setData(8);
        count.put("status",search);
        SearchField searchField = new SearchField();
        searchField.setField("userId");
        searchField.setOp("=");
        searchField.setData(user.getId());
        count.put("userId", searchField);
        count.put("groupOp","AND");
        int number = shoppingCartService.count(count);
        flag=1;
        map.put("flag",flag);
        map.put("count",number);
        map.put("msg", "登录成功！");
        map.put("user",user);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/alterPassword.do")
    public Map<String,Object> alterPassword(String old,String newpass,String id){
        Map<String,Object> map = new HashMap<String, Object>();
        User user = userService.fetch(id);
        if (MD5Util.md5CK(old).equals(user.getPassword())){
            user.setPassword(MD5Util.md5CK(newpass));
            userService.update(user);
            map.put("msg","修改成功");
        }else
        map.put("msg","修改失败，旧密码不一致");
        return map;
    }

}
