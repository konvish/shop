/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  RolesController.java 2016-01-18 15:55:27 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.Roles;
import com.kong.shop.service.impl.RolesServiceImpl;
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
@RequestMapping(value = "/roles")
public class RolesController extends BaseController {

    @Autowired
    RolesServiceImpl rolesService;

    @Override
    public String getTemplateRoot() {
        return "/html/roles";
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        return new ModelAndView(this.getTemplateRoot() + "/index", map);
    }

    @ResponseBody
    @RequestMapping(value = "/addRoles.do")
    public HashMap<String, Object> addRoles(HttpServletRequest request, HttpServletResponse response, Roles roles) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = rolesService.add(roles);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteRoles.do")
    public HashMap<String, Object> deleteRoles(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int result = rolesService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyRoles.do")
    public HashMap<String, Object> modifyRoles(HttpServletRequest request, HttpServletResponse response, Roles roles) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = rolesService.update(roles);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getRoles.do")
    public HashMap<String, Object> getRoles(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Roles roles = rolesService.fetch(id);
        map.put("roles", roles);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryRolesPage.do")
    public Map<String, Object> queryRolesPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, Roles roles) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Roles> rolesList = new ArrayList<Roles>();
        Map<String, Object> condition = new HashMap<String, Object>();

        if (roles != null) {
            if (roles.getName() != null && roles.getName() != "") {
                SearchField searchField = new SearchField();
                searchField.setField("name");
                searchField.setOp("like");
                searchField.setData("%" + roles.getName() + "%");
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
            rolesList = rolesService.queryPage(condition, current * size, size);
            map.put("rolesList", rolesList);
            map.put("currentPage", current);
            map.put("totalRecord", rolesService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryRolesAll.do")
    public Map<String,Object> queryRolesAll(HttpServletRequest request, HttpServletResponse response ){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Roles> rolesList= new ArrayList<Roles>();
        Map<String, Roles> condition = new HashMap<String, Roles>();

        String orderBy = null;
        String sortBy = null;
        try{
            rolesList = rolesService.queryList(condition, orderBy,sortBy);
            map.put("rolesList", rolesList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

}
