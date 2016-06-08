/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  CommentFacadeImpl.java 2016-01-18 15:55:23 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.ICommentFacade;
import com.kong.shop.facade.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CommentFacadeImpl")
public class CommentFacadeImpl implements ICommentFacade {
    @Autowired
    private ICommentService commentService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        commentService.add();
//    }
}
