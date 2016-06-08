/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  TrackFacadeImpl.java 2016-01-18 15:55:26 $
 */
package com.kong.shop.facade.impl;

import com.kong.shop.facade.ITrackFacade;
import com.kong.shop.facade.service.ITrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TrackFacadeImpl")
public class TrackFacadeImpl implements ITrackFacade {
    @Autowired
    private ITrackService trackService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        trackService.add();
//    }
}
