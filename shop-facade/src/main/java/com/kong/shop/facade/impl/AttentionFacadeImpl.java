/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  AttentionFacadeImpl.java 2016-01-18 15:55:22 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.IAttentionFacade;
import com.kong.shop.facade.service.IAttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AttentionFacadeImpl")
public class AttentionFacadeImpl implements IAttentionFacade {
    @Autowired
    private IAttentionService attentionService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        attentionService.add();
//    }
}
