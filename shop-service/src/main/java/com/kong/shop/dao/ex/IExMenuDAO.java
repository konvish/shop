package com.kong.shop.dao.ex;

import com.kong.shop.domain.ex.ExMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by kong on 2016/2/19.
 */
public interface IExMenuDAO {
    List<Map<String,Object>> queryPageByCondition(@Param("condition") Map<String, Object> condition, @Param("offset") Integer offset, @Param("rows") Integer rows, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy);
    List<ExMenu> getExMenu(Map params);
    int deleteMenuByIds(@Param("ids")String ids);
    List<ExMenu> queryMenuByAdmin(Map params);
    void deleteAll(String ids[]);
}
