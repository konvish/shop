/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  CommodityArg.java 2016-03-04 08:59:15 $
 */



package com.kong.shop.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class CommodityArg extends CreateBaseDomain<Integer>{
    private String name;
    private Integer parentId;
    private Integer nodeOrder;
    private Integer nodeLevel;
    private String context;
    private Integer commodityId;

	public CommodityArg(){
	}
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setParentId(Integer value) {
        this.parentId = value;
    }

    public Integer getParentId() {
        return this.parentId;
    }
    public void setNodeOrder(Integer value) {
        this.nodeOrder = value;
    }

    public Integer getNodeOrder() {
        return this.nodeOrder;
    }
    public void setNodeLevel(Integer value) {
        this.nodeLevel = value;
    }

    public Integer getNodeLevel() {
        return this.nodeLevel;
    }
    public void setContext(String value) {
        this.context = value;
    }

    public String getContext() {
        return this.context;
    }
    public void setCommodityId(Integer value) {
        this.commodityId = value;
    }

    public Integer getCommodityId() {
        return this.commodityId;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Creator",getCreator())
			.append("CreateDate",getCreateDate())
			.append("LastModifier",getLastModifier())
			.append("LastModDate",getLastModDate())
			.append("Status",getStatus())
			.append("Name",getName())
			.append("ParentId",getParentId())
			.append("NodeOrder",getNodeOrder())
			.append("NodeLevel",getNodeLevel())
			.append("Context",getContext())
			.append("CommodityId",getCommodityId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CommodityArg == false) return false;
		if(this == obj) return true;
		CommodityArg other = (CommodityArg)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

