package com.kong.shop.dao.ex;

import com.kong.shop.domain.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by kong on 2016/2/19.
 */
public interface IExAdminDAO {
    Admin checkAccountAndPassword(@Param("condition") Map<String, Object> con);
    List<Map<String, Object>> queryAdminPageByCondition(@Param("condition") Map<String, Object> con,@Param("offset") int offset,@Param("rows") int rows,@Param("orderBy") String orderBy,@Param("sortBy") String sortBy);
    String getAdminMenuByAdminId(Admin admin);
    void deleteAll(String[] ids);
}
