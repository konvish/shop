/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  UserArea.java 2016-03-21 09:02:39 $
 */



package com.kong.shop.domain;

import cn.thinkjoy.common.domain.CreateBaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserArea extends CreateBaseDomain<Integer>{
    private String name;
    private String address;
    private String post;
    private String phone;
    private Integer userId;

	public UserArea(){
	}
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
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
    public void setPhone(String value) {
        this.phone = value;
    }

    public String getPhone() {
        return this.phone;
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
			.append("Name",getName())
			.append("Address",getAddress())
			.append("Post",getPost())
			.append("Phone",getPhone())
			.append("UserId",getUserId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserArea == false) return false;
		if(this == obj) return true;
		UserArea other = (UserArea)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

