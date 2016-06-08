/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  SalesReturnFacadeImpl.java 2016-01-18 15:55:25 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.ISalesReturnFacade;
import com.kong.shop.facade.service.ISalesReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SalesReturnFacadeImpl")
public class SalesReturnFacadeImpl implements ISalesReturnFacade {
    @Autowired
    private ISalesReturnService salesReturnService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        salesReturnService.add();
//    }
}
