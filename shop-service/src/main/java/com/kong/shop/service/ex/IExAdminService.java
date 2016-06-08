package com.kong.shop.service.ex;

import com.kong.shop.domain.Admin;

import java.util.List;
import java.util.Map;

/**
 * Created by kong on 2016/2/19.
 */
public interface IExAdminService{
    Admin checkAccountAndPassword(Map<String,Object> con);
    List<Map<String,Object>> queryAdminPageByCondition(Map<String,Object> con,int page,int pageSize,String orderBy,String sortBy);
    String getAdminMenuByAdminId(Admin admin);
    void deleteAll(String[] ids);
}
