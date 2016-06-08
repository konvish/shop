/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  User.java 2016-03-04 08:59:17 $
 */



package com.kong.shop.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class User extends CreateBaseDomain<Integer>{
    private String name;
    private String password;
    private Long phone;
    private String email;
    private Integer level;
    private Integer jfen;
    private Integer sex;
    private String birthday;
    private String address;
    private String areaCode;
    private String nation;
    private String country;
    private String niName;

	public User(){
	}
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setPassword(String value) {
        this.password = value;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPhone(Long value) {
        this.phone = value;
    }

    public Long getPhone() {
        return this.phone;
    }
    public void setEmail(String value) {
        this.email = value;
    }

    public String getEmail() {
        return this.email;
    }
    public void setLevel(Integer value) {
        this.level = value;
    }

    public Integer getLevel() {
        return this.level;
    }
    public void setJfen(Integer value) {
        this.jfen = value;
    }

    public Integer getJfen() {
        return this.jfen;
    }
    public void setSex(Integer value) {
        this.sex = value;
    }

    public Integer getSex() {
        return this.sex;
    }
    public void setBirthday(String value) {
        this.birthday = value;
    }

    public String getBirthday() {
        return this.birthday;
    }
    public void setAddress(String value) {
        this.address = value;
    }

    public String getAddress() {
        return this.address;
    }
    public void setAreaCode(String value) {
        this.areaCode = value;
    }

    public String getAreaCode() {
        return this.areaCode;
    }
    public void setNation(String value) {
        this.nation = value;
    }

    public String getNation() {
        return this.nation;
    }
    public void setCountry(String value) {
        this.country = value;
    }

    public String getCountry() {
        return this.country;
    }

    public String getNiName() {
        return niName;
    }

    public void setNiName(String niName) {
        this.niName = niName;
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
			.append("Password",getPassword())
			.append("Phone",getPhone())
			.append("Email",getEmail())
			.append("Level",getLevel())
			.append("Jfen",getJfen())
			.append("Sex",getSex())
			.append("Birthday",getBirthday())
			.append("Address",getAddress())
			.append("AreaCode",getAreaCode())
			.append("Nation",getNation())
			.append("Country",getCountry())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof User == false) return false;
		if(this == obj) return true;
		User other = (User)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

