/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  OrderFacadeImpl.java 2016-01-18 15:55:24 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.IOrderFacade;
import com.kong.shop.facade.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OrderFacadeImpl")
public class OrderFacadeImpl implements IOrderFacade {
    @Autowired
    private IOrderService orderService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        orderService.add();
//    }
}
