package com.kong.shop.service.ex;

import com.kong.shop.domain.ex.ExShoppingCart;

import java.util.List;

/**
 * Created by kong on 2016/3/13 0013.
 */
public interface IExShoppingCartService {
    List<ExShoppingCart> queryShoppingCartByUserId(Integer userId);
}
