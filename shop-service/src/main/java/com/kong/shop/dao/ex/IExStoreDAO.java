package com.kong.shop.dao.ex;

import com.kong.shop.domain.Store;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/5 0005.
 */
public interface IExStoreDAO {
    List<Store> queryPageByCondition(@Param("condition")Map condition,@Param("offset") Integer offset,@Param("rows")Integer rows,@Param("sortBy")String sortBy,@Param("orderBy")String orderBy);
}
