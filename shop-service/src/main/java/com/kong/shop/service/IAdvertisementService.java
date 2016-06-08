/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  AdvertisementService.java 2016-02-24 16:58:19 $
 */

package com.kong.shop.service;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;

public interface IAdvertisementService<IAdvertisementDAO extends IBaseDAO<Advertisement>, Advertisement extends BaseDomain> extends IBaseService<IAdvertisementDAO, Advertisement>,IPageService<IAdvertisementDAO, Advertisement>{

}
