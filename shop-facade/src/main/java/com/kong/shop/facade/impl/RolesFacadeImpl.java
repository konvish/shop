/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  RolesFacadeImpl.java 2016-01-18 15:55:27 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.IRolesFacade;
import com.kong.shop.facade.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RolesFacadeImpl")
public class RolesFacadeImpl implements IRolesFacade {
    @Autowired
    private IRolesService rolesService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        rolesService.add();
//    }
}
