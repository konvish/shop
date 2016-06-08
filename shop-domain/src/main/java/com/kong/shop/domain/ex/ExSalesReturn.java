package com.kong.shop.domain.ex;

import com.kong.shop.domain.SalesReturn;

/**
 * Created by Administrator on 2016/3/5 0005.
 */
public class ExSalesReturn extends SalesReturn {
    private String commodityName;
    private String userName;

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
