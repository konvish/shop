/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  Subsystem.java 2016-03-04 08:59:19 $
 */



package com.kong.shop.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class Subsystem extends CreateBaseDomain<Integer>{
    private String name;
    private Integer sysLevel;
    private String sysUrl;
    private Integer orderNum;
    private Integer higherLevel;
    private Integer orgId;

	public Subsystem(){
	}
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setSysLevel(Integer value) {
        this.sysLevel = value;
    }

    public Integer getSysLevel() {
        return this.sysLevel;
    }
    public void setSysUrl(String value) {
        this.sysUrl = value;
    }

    public String getSysUrl() {
        return this.sysUrl;
    }
    public void setOrderNum(Integer value) {
        this.orderNum = value;
    }

    public Integer getOrderNum() {
        return this.orderNum;
    }
    public void setHigherLevel(Integer value) {
        this.higherLevel = value;
    }

    public Integer getHigherLevel() {
        return this.higherLevel;
    }
    public void setOrgId(Integer value) {
        this.orgId = value;
    }

    public Integer getOrgId() {
        return this.orgId;
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
			.append("SysLevel",getSysLevel())
			.append("SysUrl",getSysUrl())
			.append("OrderNum",getOrderNum())
			.append("HigherLevel",getHigherLevel())
			.append("OrgId",getOrgId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Subsystem == false) return false;
		if(this == obj) return true;
		Subsystem other = (Subsystem)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

