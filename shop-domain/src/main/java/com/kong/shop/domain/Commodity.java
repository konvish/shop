/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  Commodity.java 2016-03-16 16:43:35 $
 */



package com.kong.shop.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class Commodity extends CreateBaseDomain<Integer>{
    private String name;
    private Integer storeId;
    private Integer typeId;
    private Double price;
    private String brand;
    private String description;
    private String pic;
    private Double weight;
    private Integer number;
    private String production;
    private String color;
    private String datailPic;
    private String remark;
    private String realPicPath;
    private String realDetailPicPath;

	public Commodity(){
	}
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setStoreId(Integer value) {
        this.storeId = value;
    }

    public Integer getStoreId() {
        return this.storeId;
    }
    public void setTypeId(Integer value) {
        this.typeId = value;
    }

    public Integer getTypeId() {
        return this.typeId;
    }
    public void setPrice(Double value) {
        this.price = value;
    }

    public Double getPrice() {
        return this.price;
    }
    public void setBrand(String value) {
        this.brand = value;
    }

    public String getBrand() {
        return this.brand;
    }
    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }
    public void setPic(String value) {
        this.pic = value;
    }

    public String getPic() {
        return this.pic;
    }
    public void setWeight(Double value) {
        this.weight = value;
    }

    public Double getWeight() {
        return this.weight;
    }
    public void setNumber(Integer value) {
        this.number = value;
    }

    public Integer getNumber() {
        return this.number;
    }
    public void setProduction(String value) {
        this.production = value;
    }

    public String getProduction() {
        return this.production;
    }
    public void setColor(String value) {
        this.color = value;
    }

    public String getColor() {
        return this.color;
    }
    public void setDatailPic(String value) {
        this.datailPic = value;
    }

    public String getDatailPic() {
        return this.datailPic;
    }
    public void setRemark(String value) {
        this.remark = value;
    }

    public String getRemark() {
        return this.remark;
    }
    public void setRealPicPath(String value) {
        this.realPicPath = value;
    }

    public String getRealPicPath() {
        return this.realPicPath;
    }
    public void setRealDetailPicPath(String value) {
        this.realDetailPicPath = value;
    }

    public String getRealDetailPicPath() {
        return this.realDetailPicPath;
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
			.append("StoreId",getStoreId())
			.append("TypeId",getTypeId())
			.append("Price",getPrice())
			.append("Brand",getBrand())
			.append("Description",getDescription())
			.append("Pic",getPic())
			.append("Weight",getWeight())
			.append("Number",getNumber())
			.append("Production",getProduction())
			.append("Color",getColor())
			.append("DatailPic",getDatailPic())
			.append("Remark",getRemark())
			.append("RealPicPath",getRealPicPath())
			.append("RealDetailPicPath",getRealDetailPicPath())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Commodity == false) return false;
		if(this == obj) return true;
		Commodity other = (Commodity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

