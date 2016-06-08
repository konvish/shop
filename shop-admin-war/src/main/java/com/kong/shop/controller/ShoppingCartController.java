/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  ShoppingCartController.java 2016-01-18 15:55:25 $
 */
package com.kong.shop.controller;

import com.kong.shop.domain.ShoppingCart;
import com.kong.shop.service.impl.ShoppingCartServiceImpl;
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
@RequestMapping(value = "/shoppingCart")
public class ShoppingCartController extends BaseController {

    @Autowired
    ShoppingCartServiceImpl shoppingCartService;

    @Override
    public String getTemplateRoot() {
        return "/html/shoppingCart";
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        return new ModelAndView(this.getTemplateRoot() + "/index", map);
    }

    @ResponseBody
    @RequestMapping(value = "/addShoppingCart.do")
    public HashMap<String, Object> addShoppingCart(HttpServletRequest request, HttpServletResponse response, ShoppingCart shoppingCart) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = shoppingCartService.add(shoppingCart);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteShoppingCart.do")
    public HashMap<String, Object> deleteShoppingCart(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int result = shoppingCartService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyShoppingCart.do")
    public HashMap<String, Object> modifyShoppingCart(HttpServletRequest request, HttpServletResponse response, ShoppingCart shoppingCart) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = shoppingCartService.update(shoppingCart);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getShoppingCart.do")
    public HashMap<String, Object> getShoppingCart(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        ShoppingCart shoppingCart = shoppingCartService.fetch(id);
        map.put("shoppingCart", shoppingCart);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryShoppingCartPage.do")
    public Map<String, Object> queryShoppingCartPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, ShoppingCart shoppingCart) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ShoppingCart> shoppingCartList = new ArrayList<ShoppingCart>();
        Map<String, Object> condition = new HashMap<String, Object>();

//        if (shoppingCart != null) {
//            if (shoppingCart.getName() != null && shoppingCart.getName() != "") {
//                SearchField searchField = new SearchField();
//                searchField.setField("name");
//                searchField.setOp("like");
//                searchField.setData("%" + shoppingCart.getName() + "%");
//                condition.put("name", searchField);
//            }
//        }

        int current = 0;
        int size = 10;
        if (pageSize != null || currentPage != null) {
            current = Integer.parseInt(currentPage) - 1;
            size = Integer.parseInt(pageSize);
        }
        try {
            shoppingCartList = shoppingCartService.queryPage(condition, current * size, size);
            map.put("shoppingCartList", shoppingCartList);
            map.put("currentPage", current);
            map.put("totalRecord", shoppingCartService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
