/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  AttentionController.java 2016-01-18 15:55:22 $
 */
package com.kong.shop.controller;

import com.kong.shop.domain.Attention;
import com.kong.shop.service.impl.AttentionServiceImpl;
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
@RequestMapping(value = "/attention")
public class AttentionController {

    @Autowired
    AttentionServiceImpl attentionService;

    @ResponseBody
    @RequestMapping(value = "/addAttention.do")
    public HashMap<String, Object> addAttention(HttpServletRequest request, HttpServletResponse response, Attention attention) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = attentionService.add(attention);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAttention.do")
    public HashMap<String, Object> deleteAttention(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int result = attentionService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyAttention.do")
    public HashMap<String, Object> modifyAttention(HttpServletRequest request, HttpServletResponse response, Attention attention) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = attentionService.update(attention);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getAttention.do")
    public HashMap<String, Object> getAttention(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Attention attention = attentionService.fetch(id);
        map.put("attention", attention);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryAttentionPage.do")
    public Map<String, Object> queryAttentionPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, Attention attention) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Attention> attentionList = new ArrayList<Attention>();
        Map<String, Object> condition = new HashMap<String, Object>();

//        if (attention != null) {
//            if (attention.getName() != null && attention.getName() != "") {
//                SearchField searchField = new SearchField();
//                searchField.setField("name");
//                searchField.setOp("like");
//                searchField.setData("%" + attention.getName() + "%");
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
            attentionList = attentionService.queryPage(condition, current * size, size);
            map.put("attentionList", attentionList);
            map.put("currentPage", current);
            map.put("totalRecord", attentionService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
