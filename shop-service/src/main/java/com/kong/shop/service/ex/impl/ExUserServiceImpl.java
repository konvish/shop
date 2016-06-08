package com.kong.shop.service.ex.impl;

import com.kong.shop.dao.ex.IExUserDAO;
import com.kong.shop.domain.User;
import com.kong.shop.service.ex.IExUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by kong on 2016/3/8.
 */
@Service("ExUserServiceImpl")
public class ExUserServiceImpl implements IExUserService {

    @Autowired
    IExUserDAO exUserDAO;

    @Override
    public User checkAccountAndPassword(Map<String, Object> con) {
        return exUserDAO.checkAccountAndPassword(con);
    }
}
