/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  Activity.java 2016-03-04 08:59:09 $
 */



package com.kong.shop.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class Activity extends CreateBaseDomain<Integer>{
    private Integer commodityId;
    private String name;
    private Double activityPrice;
    private Long startDate;
    private Long endDate;

	public Activity(){
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
    public void setActivityPrice(Double value) {
        this.activityPrice = value;
    }

    public Double getActivityPrice() {
        return this.activityPrice;
    }
    public void setStartDate(Long value) {
        this.startDate = value;
    }

    public Long getStartDate() {
        return this.startDate;
    }
    public void setEndDate(Long value) {
        this.endDate = value;
    }

    public Long getEndDate() {
        return this.endDate;
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
			.append("Name",getName())
			.append("ActivityPrice",getActivityPrice())
			.append("StartDate",getStartDate())
			.append("EndDate",getEndDate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Activity == false) return false;
		if(this == obj) return true;
		Activity other = (Activity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

