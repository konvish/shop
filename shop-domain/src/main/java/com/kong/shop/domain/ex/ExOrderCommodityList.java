package com.kong.shop.domain.ex;

import com.kong.shop.domain.OrderCommodityList;

/**
 * Created by kong on 2016/3/20 0020.
 */
public class ExOrderCommodityList extends OrderCommodityList {
    private String commodityName;

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
}
