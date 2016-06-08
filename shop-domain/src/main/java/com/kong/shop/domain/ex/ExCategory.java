package com.kong.shop.domain.ex;

import com.kong.shop.domain.Category;

import java.util.List;

/**
 * Created by Administrator on 2016/3/2 0002.
 */
public class ExCategory extends Category {
    private List<ExCategory> categoryList;
    private String parentName;

    public List<ExCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<ExCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
