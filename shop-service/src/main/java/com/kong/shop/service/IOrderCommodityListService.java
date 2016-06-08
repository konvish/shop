/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  OrderCommodityListService.java 2016-01-18 15:55:25 $
 */

package com.kong.shop.service;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;

public interface IOrderCommodityListService<IOrderCommodityListDAO extends IBaseDAO<OrderCommodityList>, OrderCommodityList extends BaseDomain> extends IBaseService<IOrderCommodityListDAO, OrderCommodityList>,IPageService<IOrderCommodityListDAO, OrderCommodityList>{

}
