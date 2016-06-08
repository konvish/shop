/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  AreasService.java 2016-01-18 15:55:22 $
 */

package com.kong.shop.service;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;

public interface IAreasService<IAreasDAO extends IBaseDAO<Areas>, Areas extends BaseDomain> extends IBaseService<IAreasDAO, Areas>,IPageService<IAreasDAO, Areas>{

}
