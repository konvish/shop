/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  StoreFacadeImpl.java 2016-03-01 17:36:47 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.IStoreFacade;
import com.kong.shop.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreFacadeImpl")
public class StoreFacadeImpl implements IStoreFacade {
    @Autowired
    private IStoreService storeService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        storeService.add();
//    }
}
