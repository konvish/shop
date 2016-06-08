package com.kong.shop.domain.ex;

import com.kong.shop.domain.Menu;

import java.util.List;

/**
 * Created by kong on 2016/2/19.
 */
public class ExMenu extends Menu {
    private List<ExMenu> menuList;
    private String parentName;

    public List<ExMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<ExMenu> menuList) {
        this.menuList = menuList;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
