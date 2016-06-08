package com.kong.shop.service.ex.impl;

import com.kong.shop.dao.ex.IExSalesReturnDAO;
import com.kong.shop.domain.ex.ExSalesReturn;
import com.kong.shop.service.ex.IExSalesReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by kong on 2016/3/5 0005.
 */
@Service("ExSalesReturnServiceImpl")
public class ExSalesReturnServiceImpl implements IExSalesReturnService {

    @Autowired
    IExSalesReturnDAO exSalesReturnDAO;

    @Override
    public List<ExSalesReturn> queryPageByCondition(Map condition, Integer page, Integer pageSize, String sortBy, String orderBy) {
        return exSalesReturnDAO.queryPageByCondition(condition,page,pageSize,sortBy,orderBy);
    }
}
