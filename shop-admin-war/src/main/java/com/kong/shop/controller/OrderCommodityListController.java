/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  OrderCommodityListController.java 2016-01-18 15:55:25 $
 */
package com.kong.shop.controller;

import com.kong.shop.domain.OrderCommodityList;
import com.kong.shop.service.impl.OrderCommodityListServiceImpl;
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
@RequestMapping(value = "/orderCommodityList")
public class OrderCommodityListController extends BaseController {

    @Autowired
    OrderCommodityListServiceImpl orderCommodityListService;

    @Override
    public String getTemplateRoot() {
        return "/html/orderCommodityList";
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        return new ModelAndView(this.getTemplateRoot() + "/index", map);
    }

    @ResponseBody
    @RequestMapping(value = "/addOrderCommodityList.do")
    public HashMap<String, Object> addOrderCommodityList(HttpServletRequest request, HttpServletResponse response, OrderCommodityList orderCommodityList) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = orderCommodityListService.add(orderCommodityList);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteOrderCommodityList.do")
    public HashMap<String, Object> deleteOrderCommodityList(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int result = orderCommodityListService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyOrderCommodityList.do")
    public HashMap<String, Object> modifyOrderCommodityList(HttpServletRequest request, HttpServletResponse response, OrderCommodityList orderCommodityList) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = orderCommodityListService.update(orderCommodityList);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getOrderCommodityList.do")
    public HashMap<String, Object> getOrderCommodityList(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        OrderCommodityList orderCommodityList = orderCommodityListService.fetch(id);
        map.put("orderCommodityList", orderCommodityList);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryOrderCommodityListPage.do")
    public Map<String, Object> queryOrderCommodityListPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, OrderCommodityList orderCommodityList) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<OrderCommodityList> orderCommodityListList = new ArrayList<OrderCommodityList>();
        Map<String, Object> condition = new HashMap<String, Object>();

//        if (orderCommodityList != null) {
//            if (orderCommodityList.getName() != null && orderCommodityList.getName() != "") {
//                SearchField searchField = new SearchField();
//                searchField.setField("name");
//                searchField.setOp("like");
//                searchField.setData("%" + orderCommodityList.getName() + "%");
//                condition.put("name", searchField);
//            }
//        }

        int current = 0;
        int size = 10;
        if (pageSize != null || currentPage != null) {
            current = Integer.parseInt(currentPage) - 1;
            size = Integer.parseInt(pageSize);
        }
        try {
            orderCommodityListList = orderCommodityListService.queryPage(condition, current * size, size);
            map.put("orderCommodityListList", orderCommodityListList);
            map.put("currentPage", current);
            map.put("totalRecord", orderCommodityListService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
