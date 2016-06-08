/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  OrderCommodityList.java 2016-03-04 08:59:16 $
 */



package com.kong.shop.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class OrderCommodityList extends CreateBaseDomain<Integer>{
    private Integer commodityId;
    private Integer orderId;
    private Integer number;
    private Double price;

	public OrderCommodityList(){
	}
    public void setCommodityId(Integer value) {
        this.commodityId = value;
    }

    public Integer getCommodityId() {
        return this.commodityId;
    }
    public void setOrderId(Integer value) {
        this.orderId = value;
    }

    public Integer getOrderId() {
        return this.orderId;
    }
    public void setNumber(Integer value) {
        this.number = value;
    }

    public Integer getNumber() {
        return this.number;
    }
    public void setPrice(Double value) {
        this.price = value;
    }

    public Double getPrice() {
        return this.price;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Creator",getCreator())
			.append("CreateDate",getCreateDate())
			.append("LastModifier",getLastModifier())
			.append("LastModDate",getLastModDate())
			.append("Status",getStatus())
			.append("CommodityId",getCommodityId())
			.append("OrderId",getOrderId())
			.append("Number",getNumber())
			.append("Price",getPrice())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof OrderCommodityList == false) return false;
		if(this == obj) return true;
		OrderCommodityList other = (OrderCommodityList)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

