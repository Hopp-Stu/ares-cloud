package com.ares.system.controller;


import com.ares.core.controller.BaseController;
import com.ares.core.model.system.SysRole;
import com.ares.core.model.system.SysUser;
import com.ares.core.model.base.BaseResult;
import com.ares.core.model.page.TableDataInfo;
import com.ares.core.utils.StringUtils;
import com.ares.system.common.security.SecurityUtils;
import com.ares.system.service.SysRoleService;
import com.ares.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: yy 2020/05/06
 **/
@RestController
@RequestMapping("/system/role/*")
@Api(value = "系统角色API", tags = {"系统角色"})
public class SysRoleApiController extends BaseController {
    @Resource
    SysRoleService roleService;
    @Resource
    SysUserService userService;

    @PreAuthorize("hasAnyAuthority('role:list')")
    @RequestMapping("list")
    @ApiOperation(value = "角色列表", response = TableDataInfo.class)
    public TableDataInfo list(SysRole role) {
        startPage();
        List<SysRole> roleList = roleService.selectRoleList(role);
        return getDataTable(roleList);
    }

    @GetMapping("{roleId}")
    @ApiOperation(value = "根据角色Id获取用户", response = Object.class)
    public Object getInfo(@PathVariable String roleId) {
        return BaseResult.successData(roleService.getById(roleId));
    }

    @PreAuthorize("hasAnyAuthority('role:edit')")
    @PostMapping("edit")
    @ApiOperation(value = "新增/修改角色", response = Object.class)
    public Object edit(@Validated @RequestBody SysRole role) throws Exception {
        String roleId = "";
        if (StringUtils.isEmpty(role.getId())) {
            if (roleService.checkRoleName(role.getRoleName())) {
                return BaseResult.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
            }
            role.setCreator(SecurityUtils.getUser().getId());
            roleId = roleService.insertRole(role);
        } else {
            roleId = role.getId();
            role.setModifier(SecurityUtils.getUser().getId());
            roleService.updateRole(role);
        }

        return BaseResult.success();
    }

    @PreAuthorize("hasAnyAuthority('role:delete')")
    @DeleteMapping("{roleIds}")
    @ApiOperation(value = "删除用户", response = Object.class)
    public Object remove(@PathVariable String[] roleIds) {
        roleService.deleteByIds(Arrays.asList(roleIds));
        return BaseResult.success();
    }

    @PutMapping("dataScope")
    @ApiOperation(value = "角色权限分配", response = Object.class)
    public Object dataScope(@RequestBody SysRole role) {
        roleService.authDataScope(role);
        return BaseResult.success();
    }

    @GetMapping("optionselect")
    @ApiOperation(value = "角色下拉选项", response = Object.class)
    public Object optionselect() {
        return BaseResult.successData(roleService.getAll());
    }

    @GetMapping("roleUserselect/{roleId}")
    @ApiOperation(value = "根据角色Id获取用户", response = Object.class)
    public Object roleUserselect(@PathVariable String roleId) {
        BaseResult result = BaseResult.success();
        result.put("allUser", userService.selectUserList(new SysUser()));
        result.put("checkedKeys", userService.getUserByRole(roleId));
        return result;
    }
}
