/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  ShoppingCartFacadeImpl.java 2016-01-18 15:55:25 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.IShoppingCartFacade;
import com.kong.shop.facade.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ShoppingCartFacadeImpl")
public class ShoppingCartFacadeImpl implements IShoppingCartFacade {
    @Autowired
    private IShoppingCartService shoppingCartService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        shoppingCartService.add();
//    }
}
