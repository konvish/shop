package com.kong.shop.filter.shiro;

import com.kong.shop.service.IAdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by kong on 2016/2/23.
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    IAdminService adminService;

    public ShiroRealm(){
        setName("ShiroRealm");
    }

    /*
     *
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
        if(token.getUsername()!=null && token.getPassword()!=null) {
            return new SimpleAuthenticationInfo(token.getUsername(),token.getPassword(),getName());
        }
        else throw new AuthenticationException();
    }

    /*
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        return null;
    }

}

