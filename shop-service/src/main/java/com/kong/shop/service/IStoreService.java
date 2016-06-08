/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  StoreService.java 2016-03-01 17:36:47 $
 */

package com.kong.shop.service;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;

public interface IStoreService<IStoreDAO extends IBaseDAO<Store>, Store extends BaseDomain> extends IBaseService<IStoreDAO, Store>,IPageService<IStoreDAO, Store>{

}
