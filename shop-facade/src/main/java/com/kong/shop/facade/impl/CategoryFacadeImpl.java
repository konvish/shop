/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  CategoryFacadeImpl.java 2016-01-18 15:55:22 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.ICategoryFacade;
import com.kong.shop.facade.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CategoryFacadeImpl")
public class CategoryFacadeImpl implements ICategoryFacade {
    @Autowired
    private ICategoryService categoryService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        categoryService.add();
//    }
}
