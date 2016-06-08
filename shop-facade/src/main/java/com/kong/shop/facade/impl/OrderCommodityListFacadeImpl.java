/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  OrderCommodityListFacadeImpl.java 2016-01-18 15:55:25 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.IOrderCommodityListFacade;
import com.kong.shop.facade.service.IOrderCommodityListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OrderCommodityListFacadeImpl")
public class OrderCommodityListFacadeImpl implements IOrderCommodityListFacade {
    @Autowired
    private IOrderCommodityListService orderCommodityListService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        orderCommodityListService.add();
//    }
}
