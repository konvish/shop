/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  CollectionFacadeImpl.java 2016-01-18 15:55:23 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.ICollectionFacade;
import com.kong.shop.facade.service.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CollectionFacadeImpl")
public class CollectionFacadeImpl implements ICollectionFacade {
    @Autowired
    private ICollectionService collectionService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        collectionService.add();
//    }
}
