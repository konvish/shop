/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  ActivityOrderService.java 2016-01-18 15:55:21 $
 */

package com.kong.shop.service;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;

public interface IActivityOrderService<IActivityOrderDAO extends IBaseDAO<ActivityOrder>, ActivityOrder extends BaseDomain> extends IBaseService<IActivityOrderDAO, ActivityOrder>,IPageService<IActivityOrderDAO, ActivityOrder>{

}
