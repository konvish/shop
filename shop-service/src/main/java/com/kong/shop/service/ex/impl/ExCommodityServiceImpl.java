package com.kong.shop.service.ex.impl;

import com.kong.shop.dao.ex.IExCommodityDAO;
import com.kong.shop.domain.ex.ExCommodity;
import com.kong.shop.service.ex.IExCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by kong on 2016/3/6 0006.
 */
@Service("ExCommodityServiceImpl")
public class ExCommodityServiceImpl implements IExCommodityService {

    @Autowired
    IExCommodityDAO exCommodityDAO;

    @Override
    public List<ExCommodity> queryPageByCondition(Map condition, Integer page, Integer pageSize, String sortBy, String orderBy) {
        return exCommodityDAO.queryPageByCondition(condition,page,pageSize,sortBy,orderBy);
    }

    @Override
    public ExCommodity fetch(Integer id) {
        return exCommodityDAO.fetch(id);
    }

}
