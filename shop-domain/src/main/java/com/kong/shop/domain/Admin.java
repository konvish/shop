/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  Admin.java 2016-03-04 08:59:18 $
 */



package com.kong.shop.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class Admin extends CreateBaseDomain<Integer>{
    private String name;
    private String account;
    private String password;
    private Integer roleId;
    private String storeId;
    private String telephone;
    private String email;
    private String storeName;

	public Admin(){
	}
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setAccount(String value) {
        this.account = value;
    }

    public String getAccount() {
        return this.account;
    }
    public void setPassword(String value) {
        this.password = value;
    }

    public String getPassword() {
        return this.password;
    }
    public void setRoleId(Integer value) {
        this.roleId = value;
    }

    public Integer getRoleId() {
        return this.roleId;
    }
    public void setStoreId(String value) {
        this.storeId = value;
    }

    public String getStoreId() {
        return this.storeId;
    }
    public void setTelephone(String value) {
        this.telephone = value;
    }

    public String getTelephone() {
        return this.telephone;
    }
    public void setEmail(String value) {
        this.email = value;
    }

    public String getEmail() {
        return this.email;
    }
    public void setStoreName(String value) {
        this.storeName = value;
    }

    public String getStoreName() {
        return this.storeName;
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
			.append("Account",getAccount())
			.append("Password",getPassword())
			.append("RoleId",getRoleId())
			.append("StoreId",getStoreId())
			.append("Telephone",getTelephone())
			.append("Email",getEmail())
			.append("StoreName",getStoreName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Admin == false) return false;
		if(this == obj) return true;
		Admin other = (Admin)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

