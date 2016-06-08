/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  SalesReturnController.java 2016-01-18 15:55:25 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.SalesReturn;
import com.kong.shop.domain.ex.ExSalesReturn;
import com.kong.shop.service.ex.IExSalesReturnService;
import com.kong.shop.service.impl.SalesReturnServiceImpl;
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
@RequestMapping(value = "/salesReturn")
public class SalesReturnController {

    @Autowired
    SalesReturnServiceImpl salesReturnService;

    @Autowired
    IExSalesReturnService exSalesReturnService;

    @ResponseBody
    @RequestMapping(value = "/addSalesReturn.do")
    public HashMap<String, Object> addSalesReturn(HttpServletRequest request, HttpServletResponse response, SalesReturn salesReturn) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = salesReturnService.add(salesReturn);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteSalesReturn.do")
    public HashMap<String, Object> deleteSalesReturn(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Map condition = new HashMap();
        condition.put("status",8);
        condition.put("id",id);
        salesReturnService.updateMap(condition);
        //int result = salesReturnService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifySalesReturn.do")
    public HashMap<String, Object> modifySalesReturn(HttpServletRequest request, HttpServletResponse response, SalesReturn salesReturn) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = salesReturnService.update(salesReturn);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getSalesReturn.do")
    public HashMap<String, Object> getSalesReturn(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        SalesReturn salesReturn = salesReturnService.fetch(id);
        map.put("salesReturn", salesReturn);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/querySalesReturnPage.do")
    public Map<String, Object> querySalesReturnPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, SalesReturn salesReturn, String type) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ExSalesReturn> salesReturnList = new ArrayList<ExSalesReturn>();
        Map<String, Object> condition = new HashMap<String, Object>();
        Integer is = Integer.parseInt(type);
        if (is > -1) {
            SearchField searchField = new SearchField();
            searchField.setField("status");
            searchField.setOp("=");
            searchField.setData(is);
            condition.put("status", searchField);
        }

        int current = 0;
        int size = 10;
        if (pageSize != null || currentPage != null) {
            current = Integer.parseInt(currentPage) - 1;
            size = Integer.parseInt(pageSize);
        }

        try {
            salesReturnList = exSalesReturnService.queryPageByCondition(condition, current * size, size, null, null);
            map.put("salesReturnList", salesReturnList);
            map.put("currentPage", current);
            map.put("totalRecord", salesReturnService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAll.do")
    public Map deleteAll(String ids,String status) {
        Map map = new HashMap();
        Map condition = new HashMap();
        condition.put("status", Integer.parseInt(status));
        String[] id = ids.split(",");
        if (id.length > 0) {
            for (int i = 0; i < id.length; i++) {
                condition.put("id", id[i]);
                salesReturnService.updateMap(condition);
            }
            map.put("msg", "删除成功");
        } else
            map.put("msg", "失败");
        return map;
    }

}
