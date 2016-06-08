package com.kong.shop.service.ex.impl;

import com.kong.shop.dao.ICategoryDAO;
import com.kong.shop.dao.ex.IExCategoryDAO;
import com.kong.shop.domain.ex.ExCategory;
import com.kong.shop.service.ex.IExCategoryService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/2 0002.
 */
@Service("ExCategoryServiceImpl")
public class ExCategoryServiceImpl implements IExCategoryService {

    @Autowired
    IExCategoryDAO exCategoryDAO;

    @Autowired
    ICategoryDAO categoryDAO;

    @Override
    public int deleteCategoryByIds(String ids) {
        return exCategoryDAO.deleteCategoryByIds(ids);
    }

    @Override
    public int addCategorys(List categorys, Integer parentId) {
        int flag = categorys(categorys,parentId);
        return flag;
    }

    //递归扫描所有的目录及子目录
    private int categorys(List categorys,Integer parentId){
        Integer flag = 0;
        ExCategory category = null;
        JSONObject jsonObj = null;
        for(Object obj : categorys){
            jsonObj = JSONObject.fromObject(obj);
            category = (ExCategory) JSONObject.toBean(jsonObj, ExCategory.class);
            category.setParentId(parentId);
            category.setStatus(0);
            if(category.getId()==null || Integer.parseInt(String.valueOf(category.getId()))==0){
                flag = exCategoryDAO.insert(category);
                if (category.getCategoryList().size()!=0) {
                    String id = String.valueOf(category.getId());
                    categorys(category.getCategoryList(), Integer.valueOf(id));
                }
            }else{
                flag = categoryDAO.update(category);
                if (category.getCategoryList().size()!=0)
                    categorys(category.getCategoryList(),category.getId());
            }

        }
        return flag;
    }

    @Override
    public List<ExCategory> getExCategory(Map params) {
        if(params==null ){
            return null;
        }
        return exCategoryDAO.getExCategory(params);
    }

    @Override
    public void deleteAll(String[] ids) {

    }

    @Override
    public List<ExCategory> queryPageByCondition(Map condition, Integer page, Integer pageSize, String sortBy, String orderBy) {
        return exCategoryDAO.queryPageByCondition(condition,page,pageSize,sortBy,orderBy);
    }
}
