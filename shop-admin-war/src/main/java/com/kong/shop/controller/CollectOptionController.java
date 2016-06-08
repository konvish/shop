/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  CollectOptionController.java 2016-02-24 16:58:20 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.CollectOption;
import com.kong.shop.service.impl.CollectOptionServiceImpl;
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
@RequestMapping(value = "/collectOption")
public class CollectOptionController extends  BaseController{

    @Autowired
    CollectOptionServiceImpl collectOptionService;

    @Override
    public String getTemplateRoot() {
        return "/html/collectOption";
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> map=new HashMap<String, Object>();
        return new ModelAndView(this.getTemplateRoot() +"/index",map);
    }

    @ResponseBody
    @RequestMapping(value = "/addCollectOption.do")
    public HashMap<String, Object> addCollectOption(HttpServletRequest request, HttpServletResponse response,CollectOption collectOption){
        HashMap<String, Object> map=new HashMap<String, Object>();

        int flag = collectOptionService.add(collectOption);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCollectOption.do")
    public  HashMap<String, Object> deleteCollectOption(HttpServletRequest request, HttpServletResponse response,String id){
        HashMap<String, Object> map=new HashMap<String, Object>();
        int result = collectOptionService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyCollectOption.do")
    public HashMap<String, Object> modifyCollectOption(HttpServletRequest request,HttpServletResponse response,CollectOption collectOption){
        HashMap<String, Object> map=new HashMap<String, Object>();

        int flag = collectOptionService.update(collectOption);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getCollectOption.do")
    public  HashMap<String, Object> getCollectOption(HttpServletRequest request, HttpServletResponse response,String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        CollectOption collectOption = collectOptionService.fetch(id);
        map.put("collectOption", collectOption);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryCollectOptionPage.do")
    public Map<String,Object> queryCollectOptionPage(HttpServletRequest request, HttpServletResponse response,String pageSize,String currentPage,CollectOption collectOption){
        Map<String, Object> map = new HashMap<String, Object>();
        List<CollectOption> collectOptionList= new ArrayList<CollectOption>();
        Map<String, Object> condition = new HashMap<String, Object>();

        if(collectOption != null){
            if (collectOption.getName() != null&&collectOption.getName() != "") {
                SearchField searchField=new SearchField();
                searchField.setField("name");
                searchField.setOp("like");
                searchField.setData("%"+collectOption.getName()+"%");
                condition.put("name",searchField);
            }
        }

        int current=0;
        int size=10;
        if(pageSize!=null||currentPage!=null){
            current=Integer.parseInt(currentPage)-1;
            size= Integer.parseInt(pageSize);
        }
        try{
            collectOptionList=collectOptionService.queryPage(condition,current*size,size);
            map.put("collectOptionList", collectOptionList);
            map.put("currentPage", current);
            map.put("totalRecord", collectOptionService.count(condition));
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

}
