/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  CollectOptionService.java 2016-02-24 16:58:20 $
 */

package com.kong.shop.service;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;

public interface ICollectOptionService<ICollectOptionDAO extends IBaseDAO<CollectOption>, CollectOption extends BaseDomain> extends IBaseService<ICollectOptionDAO, CollectOption>,IPageService<ICollectOptionDAO, CollectOption>{

}
