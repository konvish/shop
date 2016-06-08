package com.kong.shop.dao.ex;

import com.kong.shop.domain.ex.ExCommodityArg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by kong on 2016/3/5 0005.
 */
public interface IExCommodityArgDAO {
    int deleteCommodityArgByIds(@Param("ids")String ids);
    int addCommodityArgs(List commodityArg,Integer parentId);
    List<ExCommodityArg> getExCommodityArg(Map params);
    void deleteAll(String ids[]);
    List<ExCommodityArg> queryPageByCondition(@Param("condition")Map condition,@Param("offset")Integer offset,@Param("rows")Integer rows,@Param("sortBy")String sortBy,@Param("orderBy")String orderBy);

}
