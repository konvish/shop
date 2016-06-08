package com.kong.shop.service.ex;

import com.kong.shop.domain.User;

import java.util.Map;

/**
 * Created by kong on 2016/3/8.
 */
public interface IExUserService {
    User checkAccountAndPassword(Map<String,Object> con);
}
