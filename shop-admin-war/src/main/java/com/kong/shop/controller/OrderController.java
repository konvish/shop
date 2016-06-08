/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  OrderController.java 2016-01-18 15:55:24 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.Order;
import com.kong.shop.service.impl.OrderServiceImpl;
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
@RequestMapping(value = "/order")
public class OrderController extends BaseController {

    @Autowired
    OrderServiceImpl orderService;

    @Override
    public String getTemplateRoot() {
        return "/html/order";
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        return new ModelAndView(this.getTemplateRoot() + "/index", map);
    }

    @ResponseBody
    @RequestMapping(value = "/addOrder.do")
    public HashMap<String, Object> addOrder(HttpServletRequest request, HttpServletResponse response, Order order) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = orderService.add(order);
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

        int flag = orderService.update(order);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getOrder.do")
    public HashMap<String, Object> getOrder(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Order order = orderService.fetch(id);
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
        try {
            orderList = orderService.queryPage(condition, current * size, size);
            map.put("orderList", orderList);
            map.put("currentPage", current);
            map.put("totalRecord", orderService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
