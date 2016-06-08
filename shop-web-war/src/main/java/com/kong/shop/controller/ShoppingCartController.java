/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  ShoppingCartController.java 2016-01-18 15:55:25 $
 */
package com.kong.shop.controller;

import cn.thinkjoy.common.domain.SearchField;
import com.kong.shop.domain.ShoppingCart;
import com.kong.shop.domain.ex.ExShoppingCart;
import com.kong.shop.service.ex.IExShoppingCartService;
import com.kong.shop.service.impl.ShoppingCartServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Administrator on 2015/11/10.
 */
@Controller
@RequestMapping(value = "/shoppingCart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartServiceImpl shoppingCartService;

    @Autowired
    IExShoppingCartService exShoppingCartService;

    @ResponseBody
    @RequestMapping(value = "/addShoppingCart.do")
    public HashMap<String, Object> addShoppingCart(HttpServletRequest request, HttpServletResponse response, ShoppingCart shoppingCart) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> con = new HashMap<String, Object>();
        con.put("userId", shoppingCart.getUserId());
        con.put("commodityId", shoppingCart.getCommodityId());
        con.put("status", 0);
        ShoppingCart isHave = shoppingCartService.queryOne(con);
        if (isHave != null) {
            Integer number = isHave.getNumber();
            isHave.setNumber(number + 1);
            shoppingCartService.update(isHave);
        } else {
            shoppingCart.setNumber(1);
            int flag = shoppingCartService.add(shoppingCart);
        }
        Map<String, Object> condition = new HashMap<String, Object>();
        SearchField searchField = new SearchField();
        searchField.setField("userId");
        searchField.setOp("=");
        searchField.setData(shoppingCart.getUserId());
        SearchField searchField1 = new SearchField();
        searchField1.setField("status");
        searchField1.setOp("!=");
        searchField1.setData(8);
        condition.put("userId", searchField);
        condition.put("status", searchField1);
        condition.put("groupOp", "AND");
        int count = shoppingCartService.count(condition);
        map.put("number", count);
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

    /**
     * 逻辑删除购物车的商品
     *
     * @param shoppingCart
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteShoppingCommodity.do")
    public Map<String, Object> deleteShoppingCommodity(ShoppingCart shoppingCart) {
        Map<String, Object> map = new HashMap<String, Object>();
        shoppingCart.setStatus(8);
        int flag = shoppingCartService.update(shoppingCart);
        List<ExShoppingCart> shoppingCartList = exShoppingCartService.queryShoppingCartByUserId(shoppingCart.getUserId());
        map.put("msg", "删除成功");
        map.put("shoppingCartList", shoppingCartList);
        return map;
    }

    /**
     * 根据用户Id查询购物车的商品
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryShoppingCartByUserId.do")
    public Map queryShoppingCartByUserId(String userId) {
        Map map = new HashMap();
        List<ExShoppingCart> shoppingCartList = exShoppingCartService.queryShoppingCartByUserId(Integer.valueOf(userId));
        map.put("shoppingCartList", shoppingCartList);
        return map;
    }

    /**
     * 逻辑删除购物车所有商品
     *
     * @param shoppingCarts
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteShoppingCartAll.do")
    public Map<String, Object> deleteShoppingCartAll(String shoppingCarts) {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONArray jsonArray = JSONArray.fromObject(shoppingCarts);
        JSONObject jsonObject = null;
        ShoppingCart shoppingCart = null;
        for (Object obj : jsonArray) {
            jsonObject = JSONObject.fromObject(obj);
            shoppingCart = (ShoppingCart) JSONObject.toBean(jsonObject, ShoppingCart.class);
            shoppingCart.setStatus(8);
            shoppingCartService.update(shoppingCart);
        }
        return map;
    }

}
