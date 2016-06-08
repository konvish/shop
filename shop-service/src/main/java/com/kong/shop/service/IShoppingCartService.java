/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  ShoppingCartService.java 2016-01-18 15:55:25 $
 */

package com.kong.shop.service;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;

public interface IShoppingCartService<IShoppingCartDAO extends IBaseDAO<ShoppingCart>, ShoppingCart extends BaseDomain> extends IBaseService<IShoppingCartDAO, ShoppingCart>,IPageService<IShoppingCartDAO, ShoppingCart>{

}
