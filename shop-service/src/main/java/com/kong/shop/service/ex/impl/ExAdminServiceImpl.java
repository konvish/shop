package com.kong.shop.service.ex.impl;

import com.kong.shop.dao.ex.IExAdminDAO;
import com.kong.shop.domain.Admin;
import com.kong.shop.service.ex.IExAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by kong on 2016/2/19.
 */
@Service("ExAdminServiceImpl")
public class ExAdminServiceImpl implements IExAdminService {

    @Autowired
    IExAdminDAO exAdminDAO;

    @Override
    public Admin checkAccountAndPassword(Map<String, Object> con) {
        return exAdminDAO.checkAccountAndPassword(con);
    }

    @Override
    public List<Map<String, Object>> queryAdminPageByCondition(Map<String, Object> con, int page, int pageSize, String orderBy, String sortBy) {
        return exAdminDAO.queryAdminPageByCondition(con,page,pageSize,orderBy,sortBy);
    }

    @Override
    public String getAdminMenuByAdminId(Admin admin) {
        return exAdminDAO.getAdminMenuByAdminId(admin);
    }

    @Override
    public void deleteAll(String[] ids) {
        exAdminDAO.deleteAll(ids);
    }
}
