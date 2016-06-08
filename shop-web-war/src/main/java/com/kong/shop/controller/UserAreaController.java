/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  UserAreaController.java 2016-03-21 09:02:39 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.UserArea;
import com.kong.shop.service.impl.UserAreaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping(value = "/userArea")
public class UserAreaController {

    @Autowired
    UserAreaServiceImpl userAreaService;

    @ResponseBody
    @RequestMapping(value = "/addUserArea.do")
    public HashMap<String, Object> addUserArea(HttpServletRequest request, HttpServletResponse response, UserArea userArea) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int flag = userAreaService.add(userArea);
        List user = userAreaService.findList("userId", userArea.getUserId());
        map.put("userArea",user);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUserArea.do")
    public HashMap<String, Object> deleteUserArea(HttpServletRequest request, HttpServletResponse response, String id,String userId) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int result = userAreaService.delete(id);
        List userArea = userAreaService.findList("userId", userId);
        map.put("userArea",userArea);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyUserArea.do")
    public HashMap<String, Object> modifyUserArea(HttpServletRequest request, HttpServletResponse response, UserArea userArea) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = userAreaService.update(userArea);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getUserArea.do")
    public HashMap<String, Object> getUserArea(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        UserArea userArea = userAreaService.fetch(id);
        map.put("userArea", userArea);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryUserAreaPage.do")
    public Map<String, Object> queryUserAreaPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, UserArea userArea) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<UserArea> userAreaList = new ArrayList<UserArea>();
        Map<String, Object> condition = new HashMap<String, Object>();

        if (userArea != null) {
            if (userArea.getName() != null && userArea.getName() != "") {
                SearchField searchField = new SearchField();
                searchField.setField("name");
                searchField.setOp("like");
                searchField.setData("%" + userArea.getName() + "%");
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
            userAreaList = userAreaService.queryPage(condition, current * size, size);
            map.put("userAreaList", userAreaList);
            map.put("currentPage", current);
            map.put("totalRecord", userAreaService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "queryUserAreaAll.do")
    public Map<String,Object> queryUserAreaAll(String userId){
        Map<String,Object> map = new HashMap<String, Object>();
        List userArea = userAreaService.findList("userId", userId);
        map.put("userArea",userArea);
        return map;
    }

}
