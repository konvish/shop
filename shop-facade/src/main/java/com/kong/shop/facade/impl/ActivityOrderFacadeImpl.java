/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  ActivityOrderFacadeImpl.java 2016-01-18 15:55:21 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.IActivityOrderFacade;
import com.kong.shop.facade.service.IActivityOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ActivityOrderFacadeImpl")
public class ActivityOrderFacadeImpl implements IActivityOrderFacade {
    @Autowired
    private IActivityOrderService activityOrderService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        activityOrderService.add();
//    }
}
