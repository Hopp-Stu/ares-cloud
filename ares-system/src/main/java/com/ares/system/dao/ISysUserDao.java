package com.ares.system.dao;


import com.ares.core.dao.IBaseDao;
import com.ares.core.model.system.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: yy 2020/01/22
 **/
public interface ISysUserDao extends IBaseDao<SysUser> {

    SysUser getUserByName(String userName);

    Integer checkAccount(String account);

    int resetPassword(@Param("password") String password, @Param("id") String id);

    List<SysUser> getUserByRole(String roleId);

    List<SysUser> allUser(String roleId);

}
