package com.kong.shop.service.ex;

import com.kong.shop.domain.ex.ExMenu;

import java.util.List;
import java.util.Map;

/**
 * Created by kong on 2016/2/19.
 */
public interface IExMenuService {
    List<Map<String,Object>> queryPageByCondition(Map<String, Object> var1, int var2, int var3, String var4, String var5);
    int addMenus(List menus,Integer parentId);
    List<ExMenu> getExMenu(Map params);
    int deleteMenuByIds(String ids);
    List<Map<String, Object>> queryMenuAll(Map<String, Object> var1);
    List<ExMenu> queryMenuByAdmin(Map params);
    void deleteAll(String ids[]);
}
