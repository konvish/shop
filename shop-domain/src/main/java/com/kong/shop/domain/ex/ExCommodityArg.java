package com.kong.shop.domain.ex;

import com.kong.shop.domain.CommodityArg;

import java.util.List;

/**
 * Created by kong on 2016/3/5 0005.
 */
public class ExCommodityArg extends CommodityArg {
    private String parentName;
    private String commodityName;
    private List<ExCommodityArg> commodityArgList;

    public List<ExCommodityArg> getCommodityArgList() {
        return commodityArgList;
    }

    public void setCommodityArgList(List<ExCommodityArg> commodityArgList) {
        this.commodityArgList = commodityArgList;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
