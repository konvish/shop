/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  Advertisement.java 2016-03-04 08:59:12 $
 */



package com.kong.shop.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class Advertisement extends CreateBaseDomain<Integer>{
    private Integer categoryId;
    private String name;
    private String adPic;
    private String picLink;

	public Advertisement(){
	}
    public void setCategoryId(Integer value) {
        this.categoryId = value;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setAdPic(String value) {
        this.adPic = value;
    }

    public String getAdPic() {
        return this.adPic;
    }
    public void setPicLink(String value) {
        this.picLink = value;
    }

    public String getPicLink() {
        return this.picLink;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Creator",getCreator())
			.append("CreateDate",getCreateDate())
			.append("LastModifier",getLastModifier())
			.append("LastModDate",getLastModDate())
			.append("Status",getStatus())
			.append("CategoryId",getCategoryId())
			.append("Name",getName())
			.append("AdPic",getAdPic())
			.append("PicLink",getPicLink())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Advertisement == false) return false;
		if(this == obj) return true;
		Advertisement other = (Advertisement)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

