package com.kong.shop.service.common;

import org.apache.commons.lang.StringUtils;

/**
 * Created by kong on 2016/3/4.
 */
public class ReturnJsonData {
    private boolean success=false;//结果
    private String message;//返回值
    private Object data;//返回其他实体类

    public ReturnJsonData(boolean success,String message,Object data) {
        this.success=success;
        this.message= StringUtils.trimToEmpty(message);
        this.data=data;
    }
    public ReturnJsonData(boolean success,String message) {
        this.success=success;
        this.message=StringUtils.trimToEmpty(message);
    }
    /**
     *
     */
    public ReturnJsonData(boolean success) {
        this.success=success;
    }

    /**
     *
     */
    public ReturnJsonData() {
        super();
        // TODO Auto-generated constructor stub
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
