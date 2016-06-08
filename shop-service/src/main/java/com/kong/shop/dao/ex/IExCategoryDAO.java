package com.kong.shop.dao.ex;

import com.kong.shop.domain.Category;
import com.kong.shop.domain.ex.ExCategory;
import com.kong.shop.domain.ex.ExMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/2 0002.
 */
public interface IExCategoryDAO {
    int deleteCategoryByIds(@Param("ids")String ids);
    List<ExCategory> getExCategory(Map params);
    void deleteAll(String ids[]);
    List<ExCategory> queryPageByCondition(@Param("condition") Map condition,@Param("offset") Integer offset,@Param("rows") Integer rows,@Param("sortBy") String sortBy,@Param("orderBy") String orderBy);
    int insert(Category category);
}
