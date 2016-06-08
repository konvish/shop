/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  MenuController.java 2016-01-18 15:55:27 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.Admin;
import com.kong.shop.domain.Menu;
import com.kong.shop.domain.ex.ExMenu;
import com.kong.shop.service.ex.IExAdminService;
import com.kong.shop.service.ex.IExMenuService;
import com.kong.shop.service.impl.MenuServiceImpl;
import net.sf.json.JSONArray;
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
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {

    @Autowired
    MenuServiceImpl menuService;

    @Autowired
    IExMenuService exMenuService;

    @Autowired
    IExAdminService exAdminService;

    @Override
    public String getTemplateRoot() {
        return "/html/menu";
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        return new ModelAndView(this.getTemplateRoot() + "/index", map);
    }

    @ResponseBody
    @RequestMapping(value = "/addMenu.do")
    public HashMap<String, Object> addMenu(HttpServletRequest request, HttpServletResponse response, Menu menu) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = menuService.add(menu);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/addMenus.do")
    public HashMap<String, Object> addMenus(HttpServletRequest request, HttpServletResponse response,String jsonMenus){
        HashMap<String, Object> map=new HashMap<String, Object>();
        /*Knowledge knowledge = new Knowledge();
        knowledge.setName(name);*/
        //int flag = knowledgeService.add(knowledge);
        JSONArray jsonArray = JSONArray.fromObject(jsonMenus);

        exMenuService.addMenus(jsonArray,-1);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteMenu.do")
    public HashMap<String, Object> deleteMenu(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int result = menuService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAll.do")
    public  HashMap<String, Object> deleteAll(HttpServletRequest request, HttpServletResponse response,String ids){
        HashMap<String, Object> map=new HashMap<String, Object>();
        String idStr[] = ids.split(",");
        if(ids.length()>0){
            exMenuService.deleteAll(idStr);
        }
        else{
            map.put("msg","批量删除失败");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyMenu.do")
    public HashMap<String, Object> modifyMenu(HttpServletRequest request, HttpServletResponse response, Menu menu) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = menuService.update(menu);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getMenu.do")
    public HashMap<String, Object> getMenu(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Menu menu = menuService.fetch(id);
        map.put("menu", menu);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryMenuPage.do")
    public Map<String, Object> queryMenuPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, Menu menu) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Menu> menuList = new ArrayList<Menu>();
        Map<String, Object> condition = new HashMap<String, Object>();

        if (menu != null) {
            if (menu.getName() != null && menu.getName() != "") {
                SearchField searchField = new SearchField();
                searchField.setField("name");
                searchField.setOp("like");
                searchField.setData("%" + menu.getName() + "%");
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
            menuList = menuService.queryPage(condition, current * size, size);
            map.put("menuList", menuList);
            map.put("currentPage", current);
            map.put("totalRecord", menuService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getExMenu.do")
    public Map<String,Object> getExMenu(HttpServletRequest request, HttpServletResponse response,Integer id ,Integer parentId){
        Map<String, Object> map = new HashMap<String, Object>();
        List<ExMenu> menuList= new ArrayList<ExMenu>();
        Map<String,Object> params = new HashMap();
        params.put("id",id);
        params.put("parentId",parentId);
        try{
            menuList = exMenuService.getExMenu(params);
            map.put("menuList", menuList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/deleteMenuByIds.do")
    public Map<String,Object> deleteMenuByIds(HttpServletRequest request, HttpServletResponse response,String ids){
        Map<String, Object> map = new HashMap<String, Object>();

        if (ids==null)
            return map;
        try{
            int flag = exMenuService.deleteMenuByIds(ids);
            //map.put("chapterList", chapterList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/queryMenuAll.do")
    public Map<String,Object> queryMenuAll(HttpServletRequest request, HttpServletResponse response ){
        Map<String, Object> map = new HashMap<String, Object>();
        List menuList= new ArrayList();
        Map<String, Object> condition = new HashMap<String, Object>();

        String orderBy = null;
        String sortBy = null;
        try{
            menuList = exMenuService.queryMenuAll(condition);
            map.put("menuList", menuList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/queryMenuByAdmin.do")
    public Map<String,Object> queryMenuByAdmin(HttpServletRequest request, HttpServletResponse response ){
        Map<String, Object> map = new HashMap<String, Object>();
        Admin admin = getAdmin();
        if(admin==null){
            admin = (Admin)request.getSession().getAttribute("admin");
        }
        if(admin==null){
            admin.setId(1);
        }

        String menuIds = exAdminService.getAdminMenuByAdminId(admin);
        List menuList= new ArrayList();
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("menuIds",menuIds);
        String orderBy = null;
        String sortBy = null;
        try{
            List<ExMenu> menus = exMenuService.queryMenuByAdmin(condition);
            ExMenu menuTemp = null;

            Map<Object,ExMenu> menusMap = new HashMap();
            List topMenus = new ArrayList();
            for(ExMenu menu:menus){
                if(menusMap.get(menu.getId())==null){
                    menusMap.put(menu.getId(),menu);
                }
                if(menusMap.get(menu.getParentId())!=null){
                    if(menusMap.get(menu.getParentId()).getMenuList()==null){
                        menusMap.get(menu.getParentId()).setMenuList(new ArrayList());
                    }
                    menusMap.get(menu.getParentId()).getMenuList().add(menu);
                }
                if(menu.getNodeLevel()==1){
                    topMenus.add(menu);
                }

            }
            map.put("menuList", topMenus);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

}
