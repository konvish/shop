/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  ShoppingCart.java 2016-03-04 08:59:16 $
 */



package com.kong.shop.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class ShoppingCart extends CreateBaseDomain<Integer>{
    private Integer commodityId;
    private Integer userId;
    private String color;
	private Integer number;

	public ShoppingCart(){
	}
    public void setCommodityId(Integer value) {
        this.commodityId = value;
    }

    public Integer getCommodityId() {
        return this.commodityId;
    }
    public void setUserId(Integer value) {
        this.userId = value;
    }

    public Integer getUserId() {
        return this.userId;
    }
    public void setColor(String value) {
        this.color = value;
    }

    public String getColor() {
        return this.color;
    }

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
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
			.append("UserId",getUserId())
			.append("Color",getColor())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ShoppingCart == false) return false;
		if(this == obj) return true;
		ShoppingCart other = (ShoppingCart)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

