/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  CategoryController.java 2016-01-18 15:55:22 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.Category;
import com.kong.shop.domain.ex.ExCategory;
import com.kong.shop.service.ex.IExCategoryService;
import com.kong.shop.service.impl.CategoryServiceImpl;
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
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    IExCategoryService exCategoryService;

    @ResponseBody
    @RequestMapping(value = "/addCategory.do")
    public HashMap<String, Object> addCategory(HttpServletRequest request, HttpServletResponse response, Category category) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = categoryService.add(category);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/addCategorys.do")
    public HashMap<String, Object> addCategorys(HttpServletRequest request, HttpServletResponse response, String jsonCategorys) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        /*Knowledge knowledge = new Knowledge();
        knowledge.setName(name);*/
        //int flag = knowledgeService.add(knowledge);
        JSONArray jsonArray = JSONArray.fromObject(jsonCategorys);

        exCategoryService.addCategorys(jsonArray, -1);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCategory.do")
    public HashMap<String, Object> deleteCategory(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int result = categoryService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyCategory.do")
    public HashMap<String, Object> modifyCategory(HttpServletRequest request, HttpServletResponse response, Category category) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = categoryService.update(category);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getCategory.do")
    public HashMap<String, Object> getCategory(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Category category = categoryService.fetch(id);
        map.put("category", category);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryCategoryPage.do")
    public Map<String, Object> queryCategoryPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, Category category) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ExCategory> categoryList = new ArrayList<ExCategory>();
        Map<String, Object> condition = new HashMap<String, Object>();

        if (category != null) {
            if (category.getName() != null && category.getName() != "") {
                SearchField searchField = new SearchField();
                searchField.setField("name");
                searchField.setOp("like");
                searchField.setData("%" + category.getName() + "%");
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
            categoryList = exCategoryService.queryPageByCondition(condition, current * size, size, null, null);
            map.put("categoryList", categoryList);
            map.put("currentPage", current);
            map.put("totalRecord", categoryService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 根据节点位置父节点查询类别
     *
     * @param nodeLevel
     * @param parentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryCategoryList.do")
    public Map queryCategoryList(String nodeLevel, String parentId) {
        Map map = new HashMap();
        Map condition = new HashMap();
        if (nodeLevel == null || nodeLevel == "" || nodeLevel.equals(""))
            nodeLevel = "1";
        condition.put("nodeLevel", nodeLevel);
        if (parentId == null ||parentId == "" || parentId.equals(""))
            parentId = null;
        condition.put("parentId", parentId);
        List<Category> categoryList = categoryService.queryList(condition, null, null);
        map.put("categoryList", categoryList);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getExCategory.do")
    public Map<String, Object> getExCategory(HttpServletRequest request, HttpServletResponse response, Integer id, Integer parentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ExCategory> categoryList = new ArrayList<ExCategory>();
        Map<String, Object> params = new HashMap();
        params.put("id", id);
        params.put("parentId", parentId);
        try {
            categoryList = exCategoryService.getExCategory(params);
            map.put("categoryList", categoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 多条删除
     *
     * @param request
     * @param response
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteCategoryByIds.do")
    public Map<String, Object> deleteCategoryByIds(HttpServletRequest request, HttpServletResponse response, String ids) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (ids == null)
            return map;
        try {
            int flag = exCategoryService.deleteCategoryByIds(ids);
            //map.put("chapterList", chapterList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 批量物理删除类别
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAll.do")
    public Map deleteAll(String ids) {
        Map map = new HashMap();
        String[] id = ids.split(",");
        for (int i = 0; i < id.length; i++) {
            int flag = categoryService.delete(id[i]);
        }
        map.put("msg", "删除成功");
        return map;
    }

    /**
     * 根据类别Id获取同级的所有类别
     *
     * @param categoryId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryCategoryListById.do")
    public Map queryCategoryListById(String categoryId) {
        Map map = new HashMap();
        Map condition = new HashMap();
        Category category = categoryService.fetch(categoryId);
        condition.put("parentId", category.getParentId());
        condition.put("nodeLevel", category.getNodeLevel());
        List categoryList = categoryService.queryList(condition, "nodeOrder", null);
        map.put("categoryList", categoryList);
        return map;
    }

    /**
     * 根据节点位置获取整个类目树
     *
     * @param nodeLevel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryExCategoryByNodeLevel.do")
    public Map<String, Object> queryExCategoryByNodeLevel(String nodeLevel) {
        Map map = new HashMap();
        Map params = new HashMap();
        params.put("nodeLevel", nodeLevel);
        List<ExCategory> exCategoryList = exCategoryService.getExCategory(params);
        map.put("exCategoryList", exCategoryList);
        return map;
    }

}
