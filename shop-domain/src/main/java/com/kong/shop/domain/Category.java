/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  Category.java 2016-03-04 08:59:13 $
 */



package com.kong.shop.domain;

import cn.thinkjoy.common.domain.CreateBaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Category extends CreateBaseDomain<Integer>{
    private String name;
    private Integer parentId;
    private Integer nodeOrder;
    private Integer nodeLevel;
    private String cateUrl;

	public Category(){
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
    public void setCateUrl(String value) {
        this.cateUrl = value;
    }

    public String getCateUrl() {
        return this.cateUrl;
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
			.append("CateUrl",getCateUrl())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Category == false) return false;
		if(this == obj) return true;
		Category other = (Category)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

