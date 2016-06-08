package com.kong.shop.service.ex;

import com.kong.shop.domain.ex.ExCommodity;

import java.util.List;
import java.util.Map;

/**
 * Created by kong on 2016/3/6 0006.
 */
public interface IExCommodityService {
    List<ExCommodity> queryPageByCondition(Map condition, Integer page, Integer pageSize, String sortBy, String orderBy);
    ExCommodity fetch(Integer id);
}
