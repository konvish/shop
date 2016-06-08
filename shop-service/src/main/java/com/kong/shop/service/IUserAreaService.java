/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  UserAreaService.java 2016-03-21 09:02:39 $
 */

package com.kong.shop.service;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;

public interface IUserAreaService<IUserAreaDAO extends IBaseDAO<UserArea>, UserArea extends BaseDomain> extends IBaseService<IUserAreaDAO, UserArea>,IPageService<IUserAreaDAO, UserArea>{

}
