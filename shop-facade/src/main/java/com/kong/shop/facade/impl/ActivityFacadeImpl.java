/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  ActivityFacadeImpl.java 2016-03-01 16:40:27 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.IActivityFacade;
import com.kong.shop.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ActivityFacadeImpl")
public class ActivityFacadeImpl implements IActivityFacade {
    @Autowired
    private IActivityService activityService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        activityService.add();
//    }
}
