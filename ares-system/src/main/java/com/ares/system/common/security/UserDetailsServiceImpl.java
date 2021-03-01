package com.ares.system.common.security;


import com.ares.core.model.system.SysRole;
import com.ares.core.model.system.SysUser;
import com.ares.system.common.jwt.JwtUserDetails;
import com.ares.system.dao.ISysRoleDao;
import com.ares.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/19
 * @see: com.ares.system.service UserDetailsService.java
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService userService;
    @Resource
    private ISysRoleDao sysRoleDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser user = userService.getUserByName(userName);
        if (null == user) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        List<SysRole> roleList = sysRoleDao.getRoleByUserId(user.getId());
        List<String> perms = new ArrayList<>();
        for (SysRole role : roleList) {
            if ("gly".equalsIgnoreCase(role.getRoleName())) {
                perms = sysRoleDao.getPermsByRoleId(null);
            } else {
                perms = sysRoleDao.getPermsByRoleId(role.getId());
            }
        }
        List<GrantedAuthority> grantedAuthorities = perms.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        return new JwtUserDetails(userName, user.getPassword(), grantedAuthorities);    }
}
