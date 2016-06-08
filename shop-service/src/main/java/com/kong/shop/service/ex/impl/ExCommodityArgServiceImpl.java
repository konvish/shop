package com.kong.shop.service.ex.impl;

import com.kong.shop.dao.ICommodityArgDAO;
import com.kong.shop.dao.ex.IExCommodityArgDAO;
import com.kong.shop.domain.ex.ExCommodityArg;
import com.kong.shop.service.ex.IExCommodityArgService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by kong on 2016/3/5 0005.
 */
@Service("ExCommodityArgServiceImpl")
public class ExCommodityArgServiceImpl implements IExCommodityArgService {

    @Autowired
    ICommodityArgDAO commodityArgDAO;

    @Autowired
    IExCommodityArgDAO exCommodityArgDAO;

    @Override
    public int deleteCommodityArgByIds(String ids) {
        return exCommodityArgDAO.deleteCommodityArgByIds(ids);
    }

    @Override
    public int addCommodityArgs(List commodityArgs, Integer parentId) {
        Integer flag = 0;
        List<ExCommodityArg> commodityArgList = null;
        ExCommodityArg commodityArg = null;
        JSONObject jsonObj = null;
        for(Object obj : commodityArgs){
            jsonObj = JSONObject.fromObject(obj);
            commodityArg = (ExCommodityArg) JSONObject.toBean(jsonObj, ExCommodityArg.class);
            commodityArg.setParentId(parentId);
            commodityArg.setStatus(0);
            if(commodityArg.getId()==null || Integer.parseInt(String.valueOf(commodityArg.getId()))==0){
                flag = commodityArgDAO.insert(commodityArg);
            }else{
                flag = commodityArgDAO.update(commodityArg);
            }

            commodityArgList = commodityArg.getCommodityArgList();
            if(commodityArgList!=null && commodityArgList.size()>0){
                int id = Integer.parseInt(commodityArg.getId()+"");
                addCommodityArgs(commodityArgList, id);
            }
        }
        return flag;
    }

    @Override
    public List<ExCommodityArg> getExCommodityArg(Map params) {
        if(params==null ){
            return null;
        }
        return exCommodityArgDAO.getExCommodityArg(params);
    }

    @Override
    public void deleteAll(String[] ids) {
        exCommodityArgDAO.deleteAll(ids);
    }

    @Override
    public List<ExCommodityArg> queryPageByCondition(Map condition, Integer page, Integer pageSize, String sortBy, String orderBy) {
        return exCommodityArgDAO.queryPageByCondition(condition,page,pageSize,sortBy,orderBy);
    }
}
