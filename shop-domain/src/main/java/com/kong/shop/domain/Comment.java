/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  Comment.java 2016-03-04 08:59:14 $
 */



package com.kong.shop.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class Comment extends CreateBaseDomain<Integer>{
    private String comment;
    private Integer userId;
    private Integer commodityId;
    private Integer score;

	public Comment(){
	}
    public void setComment(String value) {
        this.comment = value;
    }

    public String getComment() {
        return this.comment;
    }
    public void setUserId(Integer value) {
        this.userId = value;
    }

    public Integer getUserId() {
        return this.userId;
    }
    public void setCommodityId(Integer value) {
        this.commodityId = value;
    }

    public Integer getCommodityId() {
        return this.commodityId;
    }
    public void setScore(Integer value) {
        this.score = value;
    }

    public Integer getScore() {
        return this.score;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Creator",getCreator())
			.append("CreateDate",getCreateDate())
			.append("LastModifier",getLastModifier())
			.append("LastModDate",getLastModDate())
			.append("Status",getStatus())
			.append("Comment",getComment())
			.append("UserId",getUserId())
			.append("CommodityId",getCommodityId())
			.append("Score",getScore())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Comment == false) return false;
		if(this == obj) return true;
		Comment other = (Comment)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

