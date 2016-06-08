/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  AdminService.java 2016-01-18 15:55:27 $
 */

package com.kong.shop.service;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;

public interface IAdminService<IAdminDAO extends IBaseDAO<Admin>, Admin extends BaseDomain> extends IBaseService<IAdminDAO, Admin>,IPageService<IAdminDAO, Admin>{

}
