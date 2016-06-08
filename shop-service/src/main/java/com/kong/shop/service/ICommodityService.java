/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  CommodityService.java 2016-01-18 15:55:24 $
 */

package com.kong.shop.service;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;

public interface ICommodityService<ICommodityDAO extends IBaseDAO<Commodity>, Commodity extends BaseDomain> extends IBaseService<ICommodityDAO, Commodity>,IPageService<ICommodityDAO, Commodity>{

}
