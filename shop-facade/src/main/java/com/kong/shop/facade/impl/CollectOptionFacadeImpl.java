/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  CollectOptionFacadeImpl.java 2016-02-24 16:58:20 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.ICollectOptionFacade;
import com.kong.shop.service.ICollectOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CollectOptionFacadeImpl")
public class CollectOptionFacadeImpl implements ICollectOptionFacade {
    @Autowired
    private ICollectOptionService collectOptionService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        collectOptionService.add();
//    }
}
