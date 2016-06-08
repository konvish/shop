/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  CommodityArgFacadeImpl.java 2016-01-18 15:55:24 $
 */
package com.kong.shop.facade.impl;


import com.kong.shop.facade.ICommodityArgFacade;
import com.kong.shop.facade.service.ICommodityArgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CommodityArgFacadeImpl")
public class CommodityArgFacadeImpl implements ICommodityArgFacade {
    @Autowired
    private ICommodityArgService commodityArgService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        commodityArgService.add();
//    }
}
