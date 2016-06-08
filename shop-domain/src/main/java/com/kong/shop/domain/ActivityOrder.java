/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  ActivityOrder.java 2016-03-04 08:59:11 $
 */



package com.kong.shop.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class ActivityOrder extends CreateBaseDomain<Integer>{
    private Integer activityId;
    private Integer commodityId;
    private String name;
    private String phone;
    private String address;
    private String post;
    private String total;
    private String payway;
    private Integer userId;

	public ActivityOrder(){
	}
    public void setActivityId(Integer value) {
        this.activityId = value;
    }

    public Integer getActivityId() {
        return this.activityId;
    }
    public void setCommodityId(Integer value) {
        this.commodityId = value;
    }

    public Integer getCommodityId() {
        return this.commodityId;
    }
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setPhone(String value) {
        this.phone = value;
    }

    public String getPhone() {
        return this.phone;
    }
    public void setAddress(String value) {
        this.address = value;
    }

    public String getAddress() {
        return this.address;
    }
    public void setPost(String value) {
        this.post = value;
    }

    public String getPost() {
        return this.post;
    }
    public void setTotal(String value) {
        this.total = value;
    }

    public String getTotal() {
        return this.total;
    }
    public void setPayway(String value) {
        this.payway = value;
    }

    public String getPayway() {
        return this.payway;
    }
    public void setUserId(Integer value) {
        this.userId = value;
    }

    public Integer getUserId() {
        return this.userId;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Creator",getCreator())
			.append("CreateDate",getCreateDate())
			.append("LastModifier",getLastModifier())
			.append("LastModDate",getLastModDate())
			.append("Status",getStatus())
			.append("ActivityId",getActivityId())
			.append("CommodityId",getCommodityId())
			.append("Name",getName())
			.append("Phone",getPhone())
			.append("Address",getAddress())
			.append("Post",getPost())
			.append("Total",getTotal())
			.append("Payway",getPayway())
			.append("UserId",getUserId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ActivityOrder == false) return false;
		if(this == obj) return true;
		ActivityOrder other = (ActivityOrder)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

