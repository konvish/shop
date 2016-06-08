/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  CommodityFacadeImpl.java 2016-01-18 15:55:24 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.ICommodityFacade;
import com.kong.shop.facade.service.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CommodityFacadeImpl")
public class CommodityFacadeImpl implements ICommodityFacade {
    @Autowired
    private ICommodityService commodityService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        commodityService.add();
//    }
}
