/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  OrderController.java 2016-01-18 15:55:24 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.Order;
import com.kong.shop.domain.UserArea;
import com.kong.shop.service.ex.IExOrderService;
import com.kong.shop.service.impl.OrderServiceImpl;
import net.sf.json.JSONObject;
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
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    IExOrderService exOrderService;

    @ResponseBody
    @RequestMapping(value = "/addOrder.do")
    public HashMap<String, Object> addOrder(HttpServletRequest request, HttpServletResponse response, String userArea, String message, String orderId) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        JSONObject jsonObject = JSONObject.fromObject(userArea);
        UserArea user = (UserArea) JSONObject.toBean(jsonObject, UserArea.class);
        Order order = new Order();
        order.setId(Integer.parseInt(orderId));
        order.setAddress(user.getAddress());
        order.setMessage(message);
        order.setPhone(user.getPhone());
        order.setPost(user.getPost());
        order.setName(user.getName());
        order.setStatus(6);
        int flag = orderService.update(order);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteOrder.do")
    public HashMap<String, Object> deleteOrder(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int result = orderService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyOrder.do")
    public HashMap<String, Object> modifyOrder(HttpServletRequest request, HttpServletResponse response, Order order) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        order.setStatus(0);
        int flag = orderService.update(order);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getOrder.do")
    public HashMap<String, Object> getOrder(HttpServletRequest request, HttpServletResponse response, String userId) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("userId", Integer.parseInt(userId));
        condition.put("status", 6);
        Order order = orderService.queryOne(condition);
        map.put("order", order);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryOrderPage.do")
    public Map<String, Object> queryOrderPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, Order order) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Order> orderList = new ArrayList<Order>();
        Map<String, Object> condition = new HashMap<String, Object>();

        if (order != null) {
            if (order.getName() != null && order.getName() != "") {
                SearchField searchField = new SearchField();
                searchField.setField("name");
                searchField.setOp("like");
                searchField.setData("%" + order.getName() + "%");
                condition.put("name", searchField);
            }
        }

        int current = 0;
        int size = 10;
        if (pageSize != null || currentPage != null) {
            current = Integer.parseInt(currentPage) - 1;
            size = Integer.parseInt(pageSize);
        }
        SearchField search = new SearchField();
        search.setField("status");
        search.setOp("!=");
        search.setData(8);
        condition.put("status", search);
        SearchField searchField = new SearchField();
        searchField.setField("userId");
        searchField.setOp("=");
        searchField.setData(order.getUserId());
        condition.put("userId", searchField);
        condition.put("groupOp", "AND");
        try {
            orderList = orderService.queryPage(condition, current * size, size);
            map.put("orderList", orderList);
            map.put("currentPage", current);
            map.put("totalRecord", orderService.count(condition));
            search.setOp("=");
            search.setData(0);
            condition.put("status", search);
            map.put("successOrder", orderService.count(condition));
            map.put("totalPrice", exOrderService.countTotalPrice(order.getUserId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getOrderOne.do")
    public HashMap<String, Object> getOrderOne(HttpServletRequest request, HttpServletResponse response, String userId) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("userId", Integer.parseInt(userId));
        condition.put("status", 0);
        Order order = orderService.queryOne(condition);
        map.put("order", order);
        return map;
    }

}
