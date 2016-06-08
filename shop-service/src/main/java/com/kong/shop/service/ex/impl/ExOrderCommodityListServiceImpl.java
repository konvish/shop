package com.kong.shop.service.ex.impl;

import com.kong.shop.dao.ex.IExOrderCommodityListDAO;
import com.kong.shop.domain.ex.ExOrderCommodityList;
import com.kong.shop.service.ex.IExOrderCommodityListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kong on 2016/3/20 0020.
 */
@Service("ExOrderCommodityListServiceImpl")
public class ExOrderCommodityListServiceImpl implements IExOrderCommodityListService {

    @Autowired
    IExOrderCommodityListDAO exOrderCommodityListDAO;

    @Override
    public List<ExOrderCommodityList> queryOrderCommodityListByOrderId(Integer orderId) {
        return exOrderCommodityListDAO.queryOrderCommodityListByOrderId(orderId);
    }
}
