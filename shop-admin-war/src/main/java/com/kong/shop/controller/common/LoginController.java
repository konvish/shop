package com.kong.shop.controller.common;

import com.kong.shop.controller.BaseController;
import com.kong.shop.domain.Admin;
import com.kong.shop.service.IAdminService;
import com.kong.shop.service.common.MD5Util;
import com.kong.shop.service.ex.IExAdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kong on 2016/2/19.
 */
@Controller
@RequestMapping(value = "/")
public class LoginController extends BaseController {

    @Autowired
    IAdminService adminService;

    @Autowired
    IExAdminService exAdminService;

    @Override
    public String getTemplateRoot() {
        return "/html";
    }

    @RequestMapping(value = "index.do")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        return new ModelAndView(this.getTemplateRoot() + "/index", map);
    }

    @ResponseBody
    @RequestMapping(value = "/bgLogin.do")
    public Map bgLogin(HttpServletRequest request, HttpServletResponse response, Admin admin,String code) {
        Subject subject = SecurityUtils.getSubject();
        HttpSession session = request.getSession();
        if (!subject.isAuthenticated()) {
            return login(session, subject, admin,code);
        }
        subject.logout();
        return login(session, subject, admin,code);
    }

    private Map login(HttpSession session, Subject subject, Admin admin,String code) {
        Map map = new HashMap();
        String account = admin.getAccount();
        String password = admin.getPassword();
        if ("".equals(account) || account == null) {
            map.put("message", "请输入用户名");
            return map;
        }
        if ("".equals(account) || account == null) {
            map.put("message", "请输入密码");
            return map;
        }
        if (session.getAttribute("randCode")!=null){
            if (!(code.equalsIgnoreCase(session.getAttribute("randCode").toString()))) {
                map.put("message", "验证码不正确");
                return map;
            }
        }else if (session.getAttribute("randCode")==null){
            map.put("message", "验证码过期");
            return map;
        }


        Map<String, Object> con = new HashMap();
        con.put("account", account);
        con.put("password", MD5Util.md5CK(password));
        try {
            Admin ad = exAdminService.checkAccountAndPassword(con);

            if (ad == null) {
                map.put("message", "用户名或密码错误");
            } else if (account.equals(ad.getAccount()) && MD5Util.md5CK(password).equals(ad.getPassword())) {
                session.setAttribute("admin", ad);
                UsernamePasswordToken token = new UsernamePasswordToken(ad.getAccount(), ad.getPassword());
                try {
                    subject.login(token);
                } catch (Exception e) {
                    map.put("message", "未知错误导致认证失败");
                    return map;
                }
                map.put("adminList", ad);
                map.put("success", "登录成功");
                map.put("url", "/index.do");
            } else {
                map.put("message", "未知错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/bgLogout.do")
    public  ModelAndView bgLogin(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> map=new HashMap<String, Object>();
        request.getSession().removeAttribute("admin");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ModelAndView("/login",map);
    }
}
