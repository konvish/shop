package com.kong.shop.service.ex;

import com.kong.shop.domain.Store;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/5 0005.
 */
public interface IExStoreService {
    List<Store> queryPageByCondition(Map condition,Integer page,Integer pageSize,String sortBy,String orderBy);
}
