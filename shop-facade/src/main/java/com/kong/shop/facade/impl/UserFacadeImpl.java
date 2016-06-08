/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  UserFacadeImpl.java 2016-01-18 15:55:26 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.IUserFacade;
import com.kong.shop.facade.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserFacadeImpl")
public class UserFacadeImpl implements IUserFacade {
    @Autowired
    private IUserService userService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        userService.add();
//    }
}
