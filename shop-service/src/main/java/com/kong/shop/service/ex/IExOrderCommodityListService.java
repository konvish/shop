package com.kong.shop.service.ex;

import com.kong.shop.domain.ex.ExOrderCommodityList;

import java.util.List;

/**
 * Created by kong on 2016/3/20 0020.
 */
public interface IExOrderCommodityListService {
    List<ExOrderCommodityList> queryOrderCommodityListByOrderId(Integer orderId);
}
