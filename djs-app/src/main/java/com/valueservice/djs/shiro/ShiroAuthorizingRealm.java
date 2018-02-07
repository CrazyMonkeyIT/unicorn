package com.valueservice.djs.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.valueservice.djs.db.LoginUser;
import com.valueservice.djs.db.entity.Resources;
import com.valueservice.djs.service.LoginUserService;
import com.valueservice.djs.service.ResourceService;
import com.valueservice.djs.shiro.auth.ShiroPermission;
import com.valueservice.djs.util.SHA256;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义shiroRealm
 * @author Myron
 *
 */
public class ShiroAuthorizingRealm extends AuthorizingRealm{

	private static final Logger logger = LoggerFactory.getLogger(ShiroAuthorizingRealm.class);

	@Resource
	private EhCacheManager cacheManager;

	@Autowired
	private LoginUserService loginUserService;

	@Autowired
	private ResourceService resourcesService;
    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc){
        //获取登录时输入的用户名
        //String loginName = (String) pc.fromRealm(getName()).iterator().next();
        //TBLoginUserDO loginUser = loginUserService.selectByLoginName(loginName);
        //SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //用户的角色集合
        //Set<String> roleSet = getRoleList(user.getUserId());
        //info.setRoles(roleSet);
        //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
        //info.addStringPermissions(getResourceList(user.getUserId()));
        //info.addObjectPermissions(getPermissions(loginUser.getUserId()));
        return new SimpleAuthorizationInfo();
    }

    /**
     * 登录认证;
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) auth;
        String tokenUsername = token.getUsername();
        String tokenPassword = new String(token.getPassword());
        //查出是否有此用户
        LoginUser loginUser = loginUserService.selectByLoginName(tokenUsername);
        if(loginUser == null){
        	logger.error("用户名:" + tokenUsername + "  不存在");
			//用户不存在
        	throw new UnknownAccountException();
        }else{
        	if(!SHA256.verify(tokenPassword, loginUser.getLoginPwd())){
        		logger.error("用户名:" + tokenUsername + "  密码不正确");
				//密码错误
        		throw new IncorrectCredentialsException();
        	}else if(loginUser.getUserStatus() == 2){
				//用户已被冻结
        		throw new LockedAccountException();
        	}else if(loginUser.getUserStatus() == 3){
				//账户密码过期
        		throw new AuthenticationException("account_invalid");
        	}
        }

		//若存在，将此用户存放到登录认证info中
		AuthenticationInfo info = new SimpleAuthenticationInfo(tokenUsername, tokenPassword, getName());
		clearCache(info.getPrincipals());
		Cache<Object, Object> cache  = cacheManager.getCache("sessionCache");
		cache.put(tokenUsername, SecurityUtils.getSubject().getSession().getId().toString());
		return info;

    }

//    private List<ShiroPermission> getPermissions(Integer userId){
//    	List<Resources> resources = resourcesService.selectByUserId(userId);
//    	List<ShiroPermission> permissions = new ArrayList<ShiroPermission>();
//    	for (TBResourcesDO res : resources) {
//    		ShiroPermission per = new ShiroPermission();
//    		per.setResourceId(res.getResourceId());
//    		per.setResourceUrl(res.getResourceUrl());
//    		per.setCssCls(res.getCssCls());
//    		per.setParentId(res.getParentId());
//    		per.setOrderNo(res.getOrderNo());
//    		permissions.add(per);
//		}
//    	return permissions;
//    }



}