/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  SubsystemService.java 2016-01-18 15:55:28 $
 */

package com.kong.shop.service;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;

public interface ISubsystemService<ISubsystemDAO extends IBaseDAO<Subsystem>, Subsystem extends BaseDomain> extends IBaseService<ISubsystemDAO, Subsystem>,IPageService<ISubsystemDAO, Subsystem>{

}
