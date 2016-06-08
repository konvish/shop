/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  AdminFacadeImpl.java 2016-01-18 15:55:27 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.IAdminFacade;
import com.kong.shop.facade.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminFacadeImpl")
public class AdminFacadeImpl implements IAdminFacade {
    @Autowired
    private IAdminService adminService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        adminService.add();
//    }
}
