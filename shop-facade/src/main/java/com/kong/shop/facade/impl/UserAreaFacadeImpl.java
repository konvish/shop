/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  UserAreaFacadeImpl.java 2016-03-21 09:02:39 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.IUserAreaFacade;
import com.kong.shop.service.IUserAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserAreaFacadeImpl")
public class UserAreaFacadeImpl implements IUserAreaFacade {
    @Autowired
    private IUserAreaService userAreaService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        userAreaService.add();
//    }
}
