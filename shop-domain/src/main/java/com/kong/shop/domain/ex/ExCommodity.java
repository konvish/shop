package com.kong.shop.domain.ex;

import com.kong.shop.domain.Commodity;

/**
 * Created by kong on 2016/3/4.
 */
public class ExCommodity extends Commodity {
    private String categoryName;
    private String storeName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
