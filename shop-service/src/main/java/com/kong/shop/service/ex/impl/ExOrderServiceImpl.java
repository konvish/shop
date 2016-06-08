package com.kong.shop.service.ex.impl;

import com.kong.shop.dao.ex.IExOrderDAO;
import com.kong.shop.service.ex.IExOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kong on 2016/3/22 0022.
 */
@Service("ExOrderServiceImpl")
public class ExOrderServiceImpl implements IExOrderService {

    @Autowired
    IExOrderDAO exOrderDAO;

    @Override
    public Long countTotalPrice(Integer userId) {
        return exOrderDAO.countTotalPrice(userId);
    }
}
