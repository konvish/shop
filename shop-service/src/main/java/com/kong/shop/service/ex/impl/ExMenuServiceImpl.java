package com.kong.shop.service.ex.impl;

import com.kong.shop.dao.IMenuDAO;
import com.kong.shop.dao.ex.IExMenuDAO;
import com.kong.shop.domain.ex.ExMenu;
import com.kong.shop.service.ex.IExMenuService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by kong on 2016/2/19.
 */
@Service("ExMenuServiceImpl")
public class ExMenuServiceImpl implements IExMenuService {

    @Autowired
    IExMenuDAO exMenuDAO;

    @Autowired
    IMenuDAO menuDAO;

    @Override
    public List<Map<String, Object>> queryPageByCondition(Map<String, Object> var1, int var2, int var3, String var4, String var5) {
        return exMenuDAO.queryPageByCondition(var1,var2,var3,var4,var5);
    }

    @Override
    public int addMenus(List menus, Integer parentId) {
        Integer flag = 0;
        List<ExMenu> menuList = null;
        ExMenu menu = null;
        JSONObject jsonObj = null;
        ;
        for(Object obj : menus){
            jsonObj = JSONObject.fromObject(obj);
            menu = (ExMenu) JSONObject.toBean(jsonObj, ExMenu.class);
            menu.setParentId(parentId);
            menu.setStatus(0);
            if(menu.getId()==null || Integer.parseInt(String.valueOf(menu.getId()))==0){
                flag = menuDAO.insert(menu);
            }else{
                flag = menuDAO.update(menu);
            }

            menuList = menu.getMenuList();
            if(menuList!=null){
                int id = Integer.parseInt(menu.getId()+"");
                addMenus(menuList, id);
            }
        }
        return flag;
    }

    @Override
    public List<ExMenu> getExMenu(Map params) {
        if(params==null ){
            return null;
        }
        return exMenuDAO.getExMenu(params);
    }

    @Override
    public int deleteMenuByIds(String ids) {
        return exMenuDAO.deleteMenuByIds(ids);
    }

    @Override
    public List<Map<String, Object>> queryMenuAll(Map<String, Object> var1) {
        return exMenuDAO.queryPageByCondition(var1, null, null, null, null);
    }

    @Override
    public List<ExMenu> queryMenuByAdmin(Map params) {
        return exMenuDAO.queryMenuByAdmin(params);
    }

    @Override
    public void deleteAll(String[] ids) {
        exMenuDAO.deleteAll(ids);
    }
}
