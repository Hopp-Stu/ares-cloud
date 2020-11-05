package com.ares.system.controller;


import com.ares.core.model.base.BaseResult;
import com.ares.core.model.base.Constants;
import com.ares.core.model.cache.CacheBody;
import com.ares.core.model.system.SysMenu;
import com.ares.core.model.system.SysRole;
import com.ares.core.model.system.SysUser;
import com.ares.core.utils.MD5Util;
import com.ares.core.utils.ServletUtils;
import com.ares.system.client.RedisClient;
import com.ares.system.common.config.BaseConfig;
import com.ares.system.common.jwt.JwtAuthenticationToken;
import com.ares.system.common.security.SecurityUtils;
import com.ares.system.service.SysMenuService;
import com.ares.system.service.SysRoleService;
import com.ares.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @description:
 * @author: yy 2020/05/04
 **/
@Controller
@Api(value = "系统登录API", tags = {"系统登录"})
public class LoginApiController {
    private Logger logger = LoggerFactory.getLogger(LoginApiController.class);

    private String prefix = "";

    @Resource
    SysUserService userService;
    @Resource
    SysRoleService roleService;
    @Resource
    SysMenuService menuService;
    @Resource
    RedisClient redisClient;
    @Resource
    BaseConfig config;
    @Autowired
    private AuthenticationManager authenticationManager;


    @ApiOperation(value = "登录", response = Object.class)
    @PostMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = ServletUtils.getParameter();
        String userName = String.valueOf(map.get("username"));
        String password = String.valueOf(map.get("password"));
        password = MD5Util.encode(password);
        // 系统登录认证
        JwtAuthenticationToken token = SecurityUtils.login(request, userName, password, authenticationManager);

        SysUser user = userService.getUserByName(userName);
        if (password.equals(user.getPassword())) {
            CacheBody cacheBody = new CacheBody();
            cacheBody.setKey(Constants.LOGIN_INFO + userName);
            cacheBody.setValue(token);
            cacheBody.setTime(config.getTimeout());
            redisClient.setCache(cacheBody);
            return BaseResult.success().put("token", token.getToken());
        } else {
            return BaseResult.error(500, "用户名或密码不正确");
        }
    }


    @RequestMapping("unAuth")
    @ResponseBody
    @ApiOperation(value = "未登录", response = Object.class)
    public Object unAuth(HttpServletRequest request, HttpServletResponse response) {
        return BaseResult.unLogin();
    }

    @RequestMapping("unauthorized")
    @ResponseBody
    @ApiOperation(value = "无权限", response = Object.class)
    public Object unauthorized(HttpServletRequest request, HttpServletResponse response) {
        return BaseResult.error(HttpStatus.UNAUTHORIZED.value(), "用户无权限！");
    }

    @RequestMapping("getInfo")
    @ResponseBody
    @ApiOperation(value = "获取登录信息", response = Object.class)
    public Object getInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SysUser user = SecurityUtils.getUser();
        List<SysRole> roleList = roleService.getRoleByUserId(user.getId());
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        for (SysRole role : roleList) {
            List<String> perms = new ArrayList<>();
            if ("gly".equalsIgnoreCase(role.getRoleName())) {
                perms = roleService.getPermsByRoleId(null);
            } else {
                perms = roleService.getPermsByRoleId(role.getId());
            }
            for (String perm : perms) {
                permissions.add(perm);
            }
            roles.add(role.getRoleName());
        }
        return BaseResult.success().put("user", user).put("roles", roles).put("permissions", permissions);
    }

    @RequestMapping("getRouters")
    @ResponseBody
    @ApiOperation(value = "获取路由", response = Object.class)
    public Object getRouters() throws Exception {
        SysUser user = SecurityUtils.getUser();
        List<SysMenu> menus = menuService.getAll(user.getId());
        return BaseResult.successData(HttpStatus.OK.value(), menuService.buildMenus(menus, "0"));
    }
}
