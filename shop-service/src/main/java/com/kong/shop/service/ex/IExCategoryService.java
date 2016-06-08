package com.kong.shop.service.ex;

import com.kong.shop.domain.ex.ExCategory;
import com.kong.shop.domain.ex.ExMenu;

import java.util.List;
import java.util.Map;

/**
 * Created by AdministratorCategory on 2016/3/2 0002.
 */
public interface IExCategoryService {
    int deleteCategoryByIds(String ids);
    int addCategorys(List category,Integer parentId);
    List<ExCategory> getExCategory(Map params);
    void deleteAll(String ids[]);
    List<ExCategory> queryPageByCondition(Map condition,Integer page,Integer pageSize,String sortBy,String orderBy);
}
