/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  AreasFacadeImpl.java 2016-01-18 15:55:22 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.IAreasFacade;
import com.kong.shop.facade.service.IAreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AreasFacadeImpl")
public class AreasFacadeImpl implements IAreasFacade {
    @Autowired
    private IAreasService areasService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        areasService.add();
//    }
}
