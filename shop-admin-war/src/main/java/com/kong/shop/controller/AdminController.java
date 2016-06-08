/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  AdminController.java 2016-01-18 15:55:27 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.Admin;
import com.kong.shop.service.common.MD5Util;
import com.kong.shop.service.ex.IExAdminService;
import com.kong.shop.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/10.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    @Autowired
    AdminServiceImpl adminService;

    @Autowired
    IExAdminService exAdminService;

    @Override
    public String getTemplateRoot() {
        return "/html/admin";
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        return new ModelAndView(this.getTemplateRoot() + "/index", map);
    }

    @ResponseBody
    @RequestMapping(value = "/addAdmin.do")
    public HashMap<String, Object> addAdmin(HttpServletRequest request, HttpServletResponse response, Admin admin) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String password = admin.getPassword();
        admin.setPassword(MD5Util.md5CK(password));
        int flag = adminService.add(admin);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAdmin.do")
    public HashMap<String, Object> deleteAdmin(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int result = adminService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAll.do")
    public  HashMap<String, Object> deleteAll(HttpServletRequest request, HttpServletResponse response,String ids){
        HashMap<String, Object> map=new HashMap<String, Object>();
        String idStra[] = ids.split(",");
        if(ids.length()>0){
            exAdminService.deleteAll(idStra);
        }
        else{
            map.put("msg","批量删除失败");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyAdmin.do")
    public HashMap<String, Object> modifyAdmin(HttpServletRequest request, HttpServletResponse response, Admin admin) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = adminService.update(admin);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getAdmin.do")
    public HashMap<String, Object> getAdmin(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Admin admin = adminService.fetch(id);
        map.put("admin", admin);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryAdminPage.do")
    public Map<String, Object> queryAdminPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, Admin admin) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> adminList = null;
        Map<String, Object> condition = new HashMap<String, Object>();

        if (admin != null) {
            if (admin.getName() != null && admin.getName() != "") {
                SearchField searchField = new SearchField();
                searchField.setField("name");
                searchField.setOp("like");
                searchField.setData("%" + admin.getName() + "%");
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
            adminList = exAdminService.queryAdminPageByCondition(condition, current * size, size,null,null);
            map.put("adminList", adminList);
            map.put("currentPage", current);
            map.put("totalRecord", adminService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    //停用
    @ResponseBody
    @RequestMapping(value = "/out.do")
    public Map<String,Object> out(HttpServletRequest request, HttpServletResponse response,
                                  String id){
        HashMap<String, Object> map = new HashMap<String,Object>();
        Admin user = new Admin();
        user.setId(Integer.parseInt(id));
        user.setStatus(1);
        int flag = adminService.update(user);
        map.put("lock", flag);
        return map;
    }

    //启用
    @ResponseBody
    @RequestMapping(value = "/open.do")
    public Map<String,Object> open(HttpServletRequest request, HttpServletResponse response,
                                   String id){
        HashMap<String, Object> map = new HashMap<String,Object>();
        Admin user = new Admin();
        user.setId(Integer.parseInt(id));
        user.setStatus(0);
        int flag = adminService.update(user);
        map.put("lock", flag);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/changePassword.do")
    public Map<String,Object> changePassword(Integer[] adminId,String newPassword){
        HashMap<String,Object> map = new HashMap<String,Object>();
        String MD5password = MD5Util.md5CK(newPassword);

        for (Integer id : adminId) {
            Admin user = new Admin();
            user.setId(id);
            user.setPassword(MD5password);
            adminService.update(user);
        }
        return map;
    }

}
