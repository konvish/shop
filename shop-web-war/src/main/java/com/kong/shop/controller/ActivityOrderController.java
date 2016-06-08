/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  ActivityOrderController.java 2016-01-18 15:55:21 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.ActivityOrder;
import com.kong.shop.service.impl.ActivityOrderServiceImpl;
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
@RequestMapping(value = "/activityOrder")
public class ActivityOrderController{

    @Autowired
    ActivityOrderServiceImpl activityOrderService;

    @ResponseBody
    @RequestMapping(value = "/addActivityOrder.do")
    public HashMap<String, Object> addActivityOrder(HttpServletRequest request, HttpServletResponse response, ActivityOrder activityOrder) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = activityOrderService.add(activityOrder);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteActivityOrder.do")
    public HashMap<String, Object> deleteActivityOrder(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int result = activityOrderService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyActivityOrder.do")
    public HashMap<String, Object> modifyActivityOrder(HttpServletRequest request, HttpServletResponse response, ActivityOrder activityOrder) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = activityOrderService.update(activityOrder);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getActivityOrder.do")
    public HashMap<String, Object> getActivityOrder(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        ActivityOrder activityOrder = activityOrderService.fetch(id);
        map.put("activityOrder", activityOrder);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryActivityOrderPage.do")
    public Map<String, Object> queryActivityOrderPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, ActivityOrder activityOrder) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ActivityOrder> activityOrderList = new ArrayList<ActivityOrder>();
        Map<String, Object> condition = new HashMap<String, Object>();

        if (activityOrder != null) {
            if (activityOrder.getName() != null && activityOrder.getName() != "") {
                SearchField searchField = new SearchField();
                searchField.setField("name");
                searchField.setOp("like");
                searchField.setData("%" + activityOrder.getName() + "%");
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
            activityOrderList = activityOrderService.queryPage(condition, current * size, size);
            map.put("activityOrderList", activityOrderList);
            map.put("currentPage", current);
            map.put("totalRecord", activityOrderService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
