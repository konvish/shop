/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  ActivityController.java 2016-03-01 16:40:27 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.Activity;
import com.kong.shop.domain.Category;
import com.kong.shop.domain.Commodity;
import com.kong.shop.domain.ex.ExActivity;
import com.kong.shop.service.ICategoryService;
import com.kong.shop.service.ICommodityService;
import com.kong.shop.service.impl.ActivityServiceImpl;
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
@RequestMapping(value = "/activity")
public class ActivityController extends BaseController {

    @Autowired
    ActivityServiceImpl activityService;

    @Autowired
    ICommodityService commodityService;

    @Autowired
    ICategoryService categoryService;

    @Override
    public String getTemplateRoot() {
        return "/html/activity";
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        return new ModelAndView(this.getTemplateRoot() + "/index", map);
    }

    @ResponseBody
    @RequestMapping(value = "/addActivity.do")
    public HashMap<String, Object> addActivity(HttpServletRequest request, HttpServletResponse response, Activity activity) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = activityService.add(activity);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteActivity.do")
    public HashMap<String, Object> deleteActivity(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int result = activityService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyActivity.do")
    public HashMap<String, Object> modifyActivity(HttpServletRequest request, HttpServletResponse response, Activity activity) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = activityService.update(activity);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getActivity.do")
    public HashMap<String, Object> getActivity(HttpServletRequest request, HttpServletResponse response, String id, String commodityId) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        ExActivity activity = (ExActivity) activityService.fetch(id);
        Commodity commodity = (Commodity) commodityService.fetch(commodityId);
        Category category = (Category) categoryService.fetch(commodity.getTypeId());
        activity.setCategoryName(category.getName());
        activity.setCommodityName(commodity.getName());
        map.put("activity", activity);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryActivityPage.do")
    public Map<String, Object> queryActivityPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, Activity activity) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Activity> activityList = new ArrayList<Activity>();
        Map<String, Object> condition = new HashMap<String, Object>();

        if (activity != null) {
            if (activity.getName() != null && activity.getName() != "") {
                SearchField searchField = new SearchField();
                searchField.setField("name");
                searchField.setOp("like");
                searchField.setData("%" + activity.getName() + "%");
                condition.put("name", searchField);
                condition.put("groupOp","AND");
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
        searchField.setData(8);
        condition.put("status", searchField);
        try {
            activityList = activityService.queryPage(condition, current * size, size);
            map.put("activityList", activityList);
            map.put("currentPage", current);
            map.put("totalRecord", activityService.count(condition));
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
                activityService.updateMap(condition);
            }
            map.put("msg", "删除成功");
        } else
            map.put("msg", "失败");
        return map;
    }
}
