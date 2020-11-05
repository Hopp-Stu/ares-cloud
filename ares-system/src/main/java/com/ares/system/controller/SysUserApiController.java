package com.ares.system.controller;


import com.ares.core.controller.BaseController;
import com.ares.core.model.system.SysUser;
import com.ares.core.model.base.BaseResult;
import com.ares.core.model.page.TableDataInfo;
import com.ares.core.utils.StringUtils;
import com.ares.system.common.security.SecurityUtils;
import com.ares.system.service.SysPostService;
import com.ares.system.service.SysRoleService;
import com.ares.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: yy 2020/05/05
 **/
@RestController
@RequestMapping("/system/user/*")
@Api(value = "系统用户API", tags = {"系统用户"})
public class SysUserApiController extends BaseController {

    @Resource
    SysUserService userService;
    @Resource
    SysRoleService roleService;
    @Resource
    SysPostService postService;

    @PreAuthorize("hasAnyAuthority('user:list')")
    @RequestMapping("list")
    @ApiOperation(value = "用户列表", response = TableDataInfo.class)
    public TableDataInfo list(SysUser user) {
        startPage();
        List<SysUser> userList = userService.selectUserList(user);
        return getDataTable(userList);
    }

    @GetMapping(value = {"", "{userId}"})
    @ApiOperation(value = "根据用户Id获取用户", response = Object.class)
    public Object getInfo(@PathVariable(value = "userId", required = false) String userId) {
        BaseResult result = new BaseResult();
        result.put("code", HttpStatus.OK.value());
        result.put("data", userService.getById(userId));
        result.put("roles", roleService.getAll());
        result.put("posts", postService.getAll());
        if (StringUtils.isNotEmpty(userId)) {
            result.put("roleIds", roleService.getRoleIdsByUser(userId));
        }
        return result;
    }

    @PreAuthorize("hasAnyAuthority('user:edit')")
    @PostMapping("edit")
    @ApiOperation(value = "新增/修改用户", response = Object.class)
    public Object edit(@Validated @RequestBody SysUser user) throws Exception {
        String userId = "";
        if (StringUtils.isEmpty(user.getId())) {
            if (userService.checkAccount(user.getAccount()) != 0) {
                return BaseResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
            }
            user.setCreator(SecurityUtils.getUser().getId());
            userId = userService.insertUser(user);
        } else {
            userId = user.getId();
            user.setModifier(SecurityUtils.getUser().getId());
            userService.update(user);
        }
        if (StringUtils.isNotEmpty(user.getRoleIds())) {
            roleService.saveRoleUser(user.getRoleIds(), userId);
        }
        return BaseResult.success();
    }

    @PreAuthorize("hasAnyAuthority('user:delete')")
    @DeleteMapping("{userIds}")
    @ApiOperation(value = "删除用户", response = Object.class)
    public Object remove(@PathVariable String[] userIds) {
        userService.deleteByIds(Arrays.asList(userIds));
        return BaseResult.success();
    }

    /**
     * 重置密码
     */
    @PutMapping("resetPwd")
    @ApiOperation(value = "重置密码", response = Object.class)
    public Object resetPwd(@RequestBody SysUser user) {
        userService.resetPassword(user.getId());
        return BaseResult.success();
    }
}
