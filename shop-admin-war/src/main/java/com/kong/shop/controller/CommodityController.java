/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  CommodityController.java 2016-01-18 15:55:24 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.Category;
import com.kong.shop.domain.Commodity;
import com.kong.shop.domain.ex.ExCommodity;
import com.kong.shop.service.ICategoryService;
import com.kong.shop.service.ex.IExCommodityService;
import com.kong.shop.service.impl.CommodityServiceImpl;
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
@RequestMapping(value = "/commodity")
public class CommodityController extends BaseController {

    @Autowired
    CommodityServiceImpl commodityService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    IExCommodityService exCommodityService;

    @Override
    public String getTemplateRoot() {
        return "/html/commodity";
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        return new ModelAndView(this.getTemplateRoot() + "/index", map);
    }

    @ResponseBody
    @RequestMapping(value = "/addCommodity.do")
    public HashMap<String, Object> addCommodity(HttpServletRequest request, HttpServletResponse response, Commodity commodity) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = commodityService.add(commodity);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCommodity.do")
    public HashMap<String, Object> deleteCommodity(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int result = commodityService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyCommodity.do")
    public HashMap<String, Object> modifyCommodity(HttpServletRequest request, HttpServletResponse response, Commodity commodity) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = commodityService.update(commodity);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getCommodity.do")
    public HashMap<String, Object> getCommodity(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Integer ids = Integer.parseInt(id);
        ExCommodity commodity = exCommodityService.fetch(ids);
        map.put("commodity", commodity);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryCommodityPage.do")
    public Map<String, Object> queryCommodityPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, Commodity commodity, String is) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ExCommodity> commodityList = new ArrayList<ExCommodity>();
        Map<String, Object> condition = new HashMap<String, Object>();

        if (commodity != null) {
            if (commodity.getName() != null && commodity.getName() != "") {
                SearchField searchField = new SearchField();
                searchField.setField("name");
                searchField.setOp("like");
                searchField.setData("%" + commodity.getName() + "%");
                condition.put("name", searchField);
            }
        }

        int isInt = Integer.parseInt(is);
        if (isInt > -1) {
            SearchField searchField = new SearchField();
            searchField.setField("storeId");
            if (isInt == 0) {
                searchField.setOp("!=");
            } else searchField.setOp("=");
            searchField.setData(0);
            condition.put("storeId",searchField);
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
        condition.put("groupOp", "AND");
        try {
            commodityList = exCommodityService.queryPageByCondition(condition, current * size, size, null, null);
            map.put("commodityList", commodityList);
            map.put("currentPage", current);
            map.put("totalRecord", commodityService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 根据商品类别id和商店id查询商品
     *
     * @param categoryId
     * @param storeId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryCommodityByCategoryId.do")
    public Map queryCommodityByCategoryId(String categoryId, String storeId) {
        Map map = new HashMap();
        Map condition = new HashMap();
        condition.put("typeId", categoryId);
        condition.put("storeId", storeId);
        condition.put("status", 0);
        List<Commodity> commodityList = commodityService.queryList(condition, null, null);
        map.put("commodityList", commodityList);
        return map;
    }

    /**
     * 批量逻辑删除
     *
     * @param ids
     * @return
     */
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
                commodityService.updateMap(condition);
            }
            map.put("msg", "删除成功");
        } else
            map.put("msg", "失败");
        return map;
    }

    /**
     * 根据商品类别Id和商店id,商品名称包含词查询商品
     *
     * @param typeId
     * @param storeId
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryCommodityList.do")
    public Map queryCommodityList(String typeId, String storeId, String name) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Commodity> commodityList = new ArrayList<Commodity>();
        Map<String, Object> condition = new HashMap<String, Object>();

        if (name != "" && name != null) {
            SearchField searchField = new SearchField();
            searchField.setField("name");
            searchField.setOp("like");
            searchField.setData("%" + name + "%");
            condition.put("name", searchField);
        }
        SearchField searchField = new SearchField();
        searchField.setField("typeId");
        searchField.setOp("=");
        searchField.setData(Integer.parseInt(typeId));
        condition.put("typeId", searchField);

        SearchField searchField1 = new SearchField();
        searchField1.setField("storeId");
        searchField1.setOp("=");
        searchField1.setData(Integer.parseInt(storeId));
        condition.put("storeId", searchField1);

        SearchField searchField2 = new SearchField();
        searchField2.setField("status");
        searchField2.setOp("!=");
        searchField2.setData(8);
        condition.put("status", searchField2);
        condition.put("groupOp", "AND");

        int current = 0;
        int size = 100; //一次最多一百条
        try {
            commodityList = commodityService.queryPage(condition, current * size, size);
            map.put("commodityList", commodityList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
