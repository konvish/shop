package com.kong.shop.service.ex.impl;

import com.kong.shop.dao.ex.IExActivityDAO;
import com.kong.shop.service.ex.IExActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kong on 2016/2/29.
 */
@Service("ExActivityServiceImpl")
public class ExActivityServiceImpl implements IExActivityService {

    @Autowired
    private IExActivityDAO exActivityDAO;

    @Override
    public List queryActivityPage() {
        return exActivityDAO.queryActivityPage();
    }
}
