/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  AdvertisementController.java 2016-02-24 16:58:19 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.Advertisement;
import com.kong.shop.service.impl.AdvertisementServiceImpl;
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
@RequestMapping(value = "/advertisement")
public class AdvertisementController extends  BaseController{

    @Autowired
    AdvertisementServiceImpl advertisementService;

    @Override
    public String getTemplateRoot() {
        return "/html/advertisement";
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> map=new HashMap<String, Object>();
        return new ModelAndView(this.getTemplateRoot() +"/index",map);
    }

    @ResponseBody
    @RequestMapping(value = "/addAdvertisement.do")
    public HashMap<String, Object> addAdvertisement(HttpServletRequest request, HttpServletResponse response,Advertisement advertisement){
        HashMap<String, Object> map=new HashMap<String, Object>();

        int flag = advertisementService.add(advertisement);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAdvertisement.do")
    public  HashMap<String, Object> deleteAdvertisement(HttpServletRequest request, HttpServletResponse response,String id){
        HashMap<String, Object> map=new HashMap<String, Object>();
        int result = advertisementService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyAdvertisement.do")
    public HashMap<String, Object> modifyAdvertisement(HttpServletRequest request,HttpServletResponse response,Advertisement advertisement){
        HashMap<String, Object> map=new HashMap<String, Object>();

        int flag = advertisementService.update(advertisement);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getAdvertisement.do")
    public  HashMap<String, Object> getAdvertisement(HttpServletRequest request, HttpServletResponse response,String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Advertisement advertisement = advertisementService.fetch(id);
        map.put("advertisement", advertisement);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryAdvertisementPage.do")
    public Map<String,Object> queryAdvertisementPage(HttpServletRequest request, HttpServletResponse response,String pageSize,String currentPage,Advertisement advertisement){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Advertisement> advertisementList= new ArrayList<Advertisement>();
        Map<String, Object> condition = new HashMap<String, Object>();

        if(advertisement != null){
            if (advertisement.getName() != null&&advertisement.getName() != "") {
                SearchField searchField=new SearchField();
                searchField.setField("name");
                searchField.setOp("like");
                searchField.setData("%"+advertisement.getName()+"%");
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
            advertisementList=advertisementService.queryPage(condition,current*size,size);
            map.put("advertisementList", advertisementList);
            map.put("currentPage", current);
            map.put("totalRecord", advertisementService.count(condition));
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAll.do")
    public Map deleteAll(String ids) {
        Map map = new HashMap();
        String[] id = ids.split(",");
        for (int i=0;i<id.length;i++){
            advertisementService.delete(id[i]);
        }
        return map;
    }

}
