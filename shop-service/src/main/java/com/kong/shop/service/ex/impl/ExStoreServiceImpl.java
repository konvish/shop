package com.kong.shop.service.ex.impl;

import com.kong.shop.dao.ex.IExStoreDAO;
import com.kong.shop.domain.Store;
import com.kong.shop.service.ex.IExStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/5 0005.
 */
@Service("ExStoreServiceImpl")
public class ExStoreServiceImpl implements IExStoreService {

    @Autowired
    IExStoreDAO exStoreDAO;

    @Override
    public List<Store> queryPageByCondition(Map condition, Integer page, Integer pageSize, String sortBy, String orderBy) {
        return exStoreDAO.queryPageByCondition(condition,page,pageSize,sortBy,orderBy);
    }
}
