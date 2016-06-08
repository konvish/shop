/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  TrackService.java 2016-01-18 15:55:26 $
 */

package com.kong.shop.service;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;

public interface ITrackService<ITrackDAO extends IBaseDAO<Track>, Track extends BaseDomain> extends IBaseService<ITrackDAO, Track>,IPageService<ITrackDAO, Track>{

}
