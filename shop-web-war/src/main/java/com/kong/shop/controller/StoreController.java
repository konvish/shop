/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  StoreController.java 2016-03-01 17:36:47 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.Store;
import com.kong.shop.service.impl.StoreServiceImpl;
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
@RequestMapping(value = "/store")
public class StoreController{

    @Autowired
    StoreServiceImpl storeService;

    @ResponseBody
    @RequestMapping(value = "/addStore.do")
    public HashMap<String, Object> addStore(HttpServletRequest request, HttpServletResponse response, Store store) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = storeService.add(store);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteStore.do")
    public HashMap<String, Object> deleteStore(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Map condition = new HashMap();
        condition.put("status",8);
        condition.put("id",id);
        int re = storeService.updateMap(condition);
        //int result = storeService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyStore.do")
    public HashMap<String, Object> modifyStore(HttpServletRequest request, HttpServletResponse response, Store store) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = storeService.update(store);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getStore.do")
    public HashMap<String, Object> getStore(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Store store = storeService.fetch(id);
        map.put("store", store);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryStorePage.do")
    public Map<String, Object> queryStorePage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, Store store) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Store> storeList = new ArrayList<Store>();
        Map<String, Object> condition = new HashMap<String, Object>();

        if (store != null) {
            if (store.getName() != null && store.getName() != "") {
                SearchField searchField = new SearchField();
                searchField.setField("name");
                searchField.setOp("like");
                searchField.setData("%" + store.getName() + "%");
                condition.put("name", searchField);
            }
        }

        int current = 0;
        int size = 10;
        if (pageSize != null || currentPage != null) {
            current = Integer.parseInt(currentPage) - 1;
            size = Integer.parseInt(pageSize);
        }
        SearchField searchField = new SearchField();
        searchField.setField("status");
        searchField.setOp("!=");
        searchField.setData("8");
        condition.put("status",searchField);
        try {
            storeList = storeService.queryPage(condition, current * size, size);
            map.put("storeList", storeList);
            map.put("currentPage", current);
            map.put("totalRecord", storeService.count(condition));
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
                storeService.updateMap(condition);
            }
            map.put("msg", "删除成功");
        } else
            map.put("msg", "失败");
        return map;
    }

}
