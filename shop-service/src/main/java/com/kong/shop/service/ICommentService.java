/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  CommentService.java 2016-01-18 15:55:23 $
 */

package com.kong.shop.service;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;

public interface ICommentService<ICommentDAO extends IBaseDAO<Comment>, Comment extends BaseDomain> extends IBaseService<ICommentDAO, Comment>,IPageService<ICommentDAO, Comment>{

}
