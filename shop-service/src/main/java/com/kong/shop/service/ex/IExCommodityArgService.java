package com.kong.shop.service.ex;

import com.kong.shop.domain.ex.ExCommodityArg;

import java.util.List;
import java.util.Map;

/**
 * Created by kong on 2016/3/5 0005.
 */
public interface IExCommodityArgService {
    int deleteCommodityArgByIds(String ids);
    int addCommodityArgs(List commodityArg,Integer parentId);
    List<ExCommodityArg> getExCommodityArg(Map params);
    void deleteAll(String ids[]);
    List<ExCommodityArg> queryPageByCondition(Map condition,Integer page,Integer pageSize,String sortBy,String orderBy);

}
