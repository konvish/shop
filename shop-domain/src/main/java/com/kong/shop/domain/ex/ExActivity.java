package com.kong.shop.domain.ex;

import com.kong.shop.domain.Activity;

/**
 * Created by kong on 2016/3/2.
 */
public class ExActivity extends Activity {
    private String commodityName;
    private String categoryName;

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
