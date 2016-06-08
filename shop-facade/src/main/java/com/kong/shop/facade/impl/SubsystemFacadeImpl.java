/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  SubsystemFacadeImpl.java 2016-01-18 15:55:28 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.ISubsystemFacade;
import com.kong.shop.facade.service.ISubsystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SubsystemFacadeImpl")
public class SubsystemFacadeImpl implements ISubsystemFacade {
    @Autowired
    private ISubsystemService subsystemService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        subsystemService.add();
//    }
}
