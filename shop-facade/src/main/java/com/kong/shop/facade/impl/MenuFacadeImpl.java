/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  MenuFacadeImpl.java 2016-01-18 15:55:27 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.IMenuFacade;
import com.kong.shop.facade.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MenuFacadeImpl")
public class MenuFacadeImpl implements IMenuFacade {
    @Autowired
    private IMenuService menuService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        menuService.add();
//    }
}
