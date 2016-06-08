/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  ActivityService.java 2016-03-01 16:40:27 $
 */

package com.kong.shop.service;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;

public interface IActivityService<IActivityDAO extends IBaseDAO<Activity>, Activity extends BaseDomain> extends IBaseService<IActivityDAO, Activity>,IPageService<IActivityDAO, Activity>{

}
