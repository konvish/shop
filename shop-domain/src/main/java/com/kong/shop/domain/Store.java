/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  Store.java 2016-03-04 08:59:17 $
 */



package com.kong.shop.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class Store extends CreateBaseDomain<Integer>{
    private String name;
    private String type;
    private String operator;
    private String operatorInfo;

	public Store(){
	}
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setType(String value) {
        this.type = value;
    }

    public String getType() {
        return this.type;
    }
    public void setOperator(String value) {
        this.operator = value;
    }

    public String getOperator() {
        return this.operator;
    }
    public void setOperatorInfo(String value) {
        this.operatorInfo = value;
    }

    public String getOperatorInfo() {
        return this.operatorInfo;
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
			.append("Type",getType())
			.append("Operator",getOperator())
			.append("OperatorInfo",getOperatorInfo())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Store == false) return false;
		if(this == obj) return true;
		Store other = (Store)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

