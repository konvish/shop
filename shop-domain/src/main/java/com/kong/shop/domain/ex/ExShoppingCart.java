package com.kong.shop.domain.ex;

import com.kong.shop.domain.ShoppingCart;

/**
 * Created by kong on 2016/3/13 0013.
 */
public class ExShoppingCart extends ShoppingCart {
    private String commodityName;
    private String userName;
    private Double price;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
