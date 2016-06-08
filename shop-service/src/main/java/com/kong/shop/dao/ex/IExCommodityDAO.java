package com.kong.shop.dao.ex;

import com.kong.shop.domain.ex.ExCommodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by kong on 2016/3/6 0006.
 */
public interface IExCommodityDAO {
    List<ExCommodity> queryPageByCondition(@Param("condition") Map condition, @Param("offset") Integer offset, @Param("rows") Integer rows, @Param("sortBy") String sortBy, @Param("orderBy") String orderBy);
    ExCommodity fetch(Integer id);
    ExCommodity findOne(Map map);
}
