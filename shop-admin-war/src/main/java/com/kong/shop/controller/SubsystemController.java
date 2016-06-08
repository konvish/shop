/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  SubsystemController.java 2016-01-18 15:55:28 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.Subsystem;
import com.kong.shop.service.impl.SubsystemServiceImpl;
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
@RequestMapping(value = "/subsystem")
public class SubsystemController extends BaseController {

    @Autowired
    SubsystemServiceImpl subsystemService;

    @Override
    public String getTemplateRoot() {
        return "/html/subsystem";
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        return new ModelAndView(this.getTemplateRoot() + "/index", map);
    }

    @ResponseBody
    @RequestMapping(value = "/addSubsystem.do")
    public HashMap<String, Object> addSubsystem(HttpServletRequest request, HttpServletResponse response, Subsystem subsystem) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = subsystemService.add(subsystem);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteSubsystem.do")
    public HashMap<String, Object> deleteSubsystem(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int result = subsystemService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifySubsystem.do")
    public HashMap<String, Object> modifySubsystem(HttpServletRequest request, HttpServletResponse response, Subsystem subsystem) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = subsystemService.update(subsystem);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getSubsystem.do")
    public HashMap<String, Object> getSubsystem(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Subsystem subsystem = subsystemService.fetch(id);
        map.put("subsystem", subsystem);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/querySubsystemPage.do")
    public Map<String, Object> querySubsystemPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, Subsystem subsystem) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Subsystem> subsystemList = new ArrayList<Subsystem>();
        Map<String, Object> condition = new HashMap<String, Object>();

        if (subsystem != null) {
            if (subsystem.getName() != null && subsystem.getName() != "") {
                SearchField searchField = new SearchField();
                searchField.setField("name");
                searchField.setOp("like");
                searchField.setData("%" + subsystem.getName() + "%");
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
            subsystemList = subsystemService.queryPage(condition, current * size, size);
            map.put("subsystemList", subsystemList);
            map.put("currentPage", current);
            map.put("totalRecord", subsystemService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
