/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  Menu.java 2016-03-04 08:59:18 $
 */



package com.kong.shop.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class Menu extends CreateBaseDomain<Integer>{
    private String name;
    private Integer parentId;
    private Integer nodeOrder;
    private Integer nodeLevel;
    private String menuUrl;
    private String code;

	public Menu(){
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
    public void setMenuUrl(String value) {
        this.menuUrl = value;
    }

    public String getMenuUrl() {
        return this.menuUrl;
    }
    public void setCode(String value) {
        this.code = value;
    }

    public String getCode() {
        return this.code;
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
			.append("MenuUrl",getMenuUrl())
			.append("Code",getCode())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Menu == false) return false;
		if(this == obj) return true;
		Menu other = (Menu)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

