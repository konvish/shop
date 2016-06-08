/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  Roles.java 2016-03-04 08:59:18 $
 */



package com.kong.shop.domain;

import cn.thinkjoy.common.domain.CreateBaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Roles extends CreateBaseDomain<Integer>{
    private String name;
    private String description;
    private Integer needCheck;
    private String menuIds;

	public Roles(){
	}
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }
    public void setNeedCheck(Integer value) {
        this.needCheck = value;
    }

    public Integer getNeedCheck() {
        return this.needCheck;
    }
    public void setMenuIds(String value) {
        this.menuIds = value;
    }

    public String getMenuIds() {
        return this.menuIds;
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
			.append("Description",getDescription())
			.append("NeedCheck",getNeedCheck())
			.append("MenuIds",getMenuIds())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Roles == false) return false;
		if(this == obj) return true;
		Roles other = (Roles)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

