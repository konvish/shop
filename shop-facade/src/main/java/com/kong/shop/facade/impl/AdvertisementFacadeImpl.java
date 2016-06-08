/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  AdvertisementFacadeImpl.java 2016-02-24 16:58:19 $
 */
package com.kong.shop.facade.impl;

import com.kong.facade.IAdvertisementFacade;
import com.kong.service.IAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdvertisementFacadeImpl")
public class AdvertisementFacadeImpl implements IAdvertisementFacade {
    @Autowired
    private IAdvertisementService advertisementService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        advertisementService.add();
//    }
}
