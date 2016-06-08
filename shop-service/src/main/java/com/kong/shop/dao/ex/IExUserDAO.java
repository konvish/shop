package com.kong.shop.dao.ex;

import com.kong.shop.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by kong on 2016/3/8.
 */
public interface IExUserDAO {
    User checkAccountAndPassword(@Param("condition") Map<String, Object> con);
}
