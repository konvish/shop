/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  AreasController.java 2016-01-18 15:55:22 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.Areas;
import com.kong.shop.service.impl.AreasServiceImpl;
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
@RequestMapping(value = "/areas")
public class AreasController extends BaseController {

    @Autowired
    AreasServiceImpl areasService;

    @Override
    public String getTemplateRoot() {
        return "/html/areas";
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        return new ModelAndView(this.getTemplateRoot() + "/index", map);
    }

    @ResponseBody
    @RequestMapping(value = "/addAreas.do")
    public HashMap<String, Object> addAreas(HttpServletRequest request, HttpServletResponse response, Areas areas) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = areasService.add(areas);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAreas.do")
    public HashMap<String, Object> deleteAreas(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int result = areasService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyAreas.do")
    public HashMap<String, Object> modifyAreas(HttpServletRequest request, HttpServletResponse response, Areas areas) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = areasService.update(areas);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getAreas.do")
    public HashMap<String, Object> getAreas(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Areas areas = areasService.fetch(id);
        map.put("areas", areas);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryAreasPage.do")
    public Map<String, Object> queryAreasPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, Areas areas) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Areas> areasList = new ArrayList<Areas>();
        Map<String, Object> condition = new HashMap<String, Object>();

        if (areas != null) {
            if (areas.getName() != null && areas.getName() != "") {
                SearchField searchField = new SearchField();
                searchField.setField("name");
                searchField.setOp("like");
                searchField.setData("%" + areas.getName() + "%");
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
            areasList = areasService.queryPage(condition, current * size, size);
            map.put("areasList", areasList);
            map.put("currentPage", current);
            map.put("totalRecord", areasService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
