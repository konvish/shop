/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  UserController.java 2016-01-18 15:55:26 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.User;
import com.kong.shop.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/10.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    UserServiceImpl userService;

    @Override
    public String getTemplateRoot() {
        return "/html/user";
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        return new ModelAndView(this.getTemplateRoot() + "/index", map);
    }

    @ResponseBody
    @RequestMapping(value = "/addUser.do")
    public HashMap<String, Object> addUser(HttpServletRequest request, HttpServletResponse response, User user) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = userService.add(user);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUser.do")
    public HashMap<String, Object> deleteUser(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Map condition = new HashMap();
        condition.put("status",8);
        condition.put("id",id);
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

    @ResponseBody
    @RequestMapping(value = "/deleteAll.do")
    public Map deleteAll(String ids) {
        Map map = new HashMap();
        Map condition = new HashMap();
        condition.put("status", 8);
        String[] id = ids.split(",");
        if (id.length > 0) {
            for (int i = 0; i < id.length; i++) {
                condition.put("id", id[i]);
                userService.updateMap(condition);
            }
            map.put("msg", "删除成功");
        } else
            map.put("msg", "失败");
        return map;
    }

    //停用
    @ResponseBody
    @RequestMapping(value = "/out.do")
    public Map<String,Object> out(HttpServletRequest request, HttpServletResponse response,
                                  String id){
        HashMap<String, Object> map = new HashMap<String,Object>();
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setStatus(8);
        int flag = userService.update(user);
        map.put("lock", flag);
        return map;
    }

    //启用
    @ResponseBody
    @RequestMapping(value = "/open.do")
    public Map<String,Object> open(HttpServletRequest request, HttpServletResponse response,
                                   String id){
        HashMap<String, Object> map = new HashMap<String,Object>();
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setStatus(0);
        int flag = userService.update(user);
        map.put("lock", flag);
        return map;
    }
}
