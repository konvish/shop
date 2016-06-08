package com.kong.shop.service.ex.impl;

import com.kong.shop.dao.ex.IExShoppingCartDAO;
import com.kong.shop.domain.ex.ExShoppingCart;
import com.kong.shop.service.ex.IExShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kong on 2016/3/13 0013.
 */
@Service("ExShoppingCartServiceImpl")
public class ExShoppingCartServiceImpl implements IExShoppingCartService {

    @Autowired
    IExShoppingCartDAO exShoppingCartDAO;

    @Override
    public List<ExShoppingCart> queryShoppingCartByUserId(Integer userId) {
        return exShoppingCartDAO.queryShoppingCartByUserId(userId);
    }
}
