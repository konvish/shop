package com.kong.shop.controller;

import com.kong.shop.domain.Admin;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基础控制器
 */
public abstract class BaseController {
    @Autowired
    HttpServletRequest request;

    /**
     * 日志对象
     */

    protected Log log = LogFactory.getLog(this.getClass());


    /**
     * 取得静态页面模板的相对于网站根的根路径	 *
     * @return 格式如:/html/info/
     */
    public abstract String getTemplateRoot();

    /**
     * 根据版本号取得静态页面模板的相对于网站根的根路径	 *
     * @return 格式如:/background/html/
     */
    public String getVersionTemplateRoot(String path,HttpServletResponse response){
        String templateRoot = "/";
        String[] ps = path.split("/");
        for(String tempPath : ps){
            if(!"".equals(tempPath)){
                if("html".equals(tempPath)){
                    //templateRoot += tempPath+this.getVersion(request)+"/";
                }else{
                    templateRoot += tempPath+"/";
                }
            }
        }
        return templateRoot;
    }

    public Admin getAdmin(){
        return (Admin)request.getSession().getAttribute("admin");
    }

}
