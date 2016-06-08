/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  CommodityArgController.java 2016-01-18 15:55:24 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.CommodityArg;
import com.kong.shop.domain.ex.ExCommodityArg;
import com.kong.shop.service.ex.IExCommodityArgService;
import com.kong.shop.service.impl.CommodityArgServiceImpl;
import net.sf.json.JSONArray;
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
@RequestMapping(value = "/commodityArg")
public class CommodityArgController {

    @Autowired
    CommodityArgServiceImpl commodityArgService;

    @Autowired
    IExCommodityArgService exCommodityArgService;

    @ResponseBody
    @RequestMapping(value = "/addCommodityArg.do")
    public HashMap<String, Object> addCommodityArg(HttpServletRequest request, HttpServletResponse response, CommodityArg commodityArg) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = commodityArgService.add(commodityArg);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/addCommodityArgs.do")
    public HashMap<String, Object> addCommodityArgs(HttpServletRequest request, HttpServletResponse response, String jsonCommodityArgs) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        JSONArray jsonArray = JSONArray.fromObject(jsonCommodityArgs);

        exCommodityArgService.addCommodityArgs(jsonArray, -1);
        return map;
    }


    @ResponseBody
    @RequestMapping(value = "/deleteCommodityArg.do")
    public HashMap<String, Object> deleteCommodityArg(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int result = commodityArgService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyCommodityArg.do")
    public HashMap<String, Object> modifyCommodityArg(HttpServletRequest request, HttpServletResponse response, CommodityArg commodityArg) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = commodityArgService.update(commodityArg);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getCommodityArg.do")
    public HashMap<String, Object> getCommodityArg(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        CommodityArg commodityArg = commodityArgService.fetch(id);
        map.put("commodityArg", commodityArg);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryCommodityArgPage.do")
    public Map<String, Object> queryCommodityArgPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, CommodityArg commodityArg) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ExCommodityArg> commodityArgList = new ArrayList<ExCommodityArg>();
        Map<String, Object> condition = new HashMap<String, Object>();

        if (commodityArg != null) {
            if (commodityArg.getName() != null && commodityArg.getName() != "") {
                SearchField searchField = new SearchField();
                searchField.setField("name");
                searchField.setOp("like");
                searchField.setData("%" + commodityArg.getName() + "%");
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
            commodityArgList = exCommodityArgService.queryPageByCondition(condition, current * size, size, null, null);
            map.put("commodityArgList", commodityArgList);
            map.put("currentPage", current);
            map.put("totalRecord", commodityArgService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAll.do")
    public Map deleteAll(String ids) {
        Map map = new HashMap();
        String[] id = ids.split(",");
        if (id.length > 0) {
            for (int i = 0; i < id.length; i++) {
                commodityArgService.deleteById(id[i]);
            }
            map.put("msg", "删除成功");
        } else
            map.put("msg", "失败");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCommodityArgByIds.do")
    public Map<String, Object> deleteCommodityArgByIds(HttpServletRequest request, HttpServletResponse response, String ids) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (ids == null)
            return map;
        try {
            int flag = exCommodityArgService.deleteCommodityArgByIds(ids);
            //map.put("chapterList", chapterList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getExCommodityArg.do")
    public Map<String, Object> getExCommodityArg(HttpServletRequest request, HttpServletResponse response, Integer id, Integer parentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ExCommodityArg> commodityArgList = new ArrayList<ExCommodityArg>();
        Map<String, Object> params = new HashMap();
        params.put("id", id);
        params.put("parentId", parentId);
        try {
            commodityArgList = exCommodityArgService.getExCommodityArg(params);
            map.put("commodityArgList", commodityArgList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
