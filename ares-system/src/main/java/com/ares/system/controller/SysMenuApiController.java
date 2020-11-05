package com.ares.system.controller;


import com.ares.core.model.system.SysMenu;
import com.ares.core.model.base.BaseResult;
import com.ares.core.utils.StringUtils;
import com.ares.system.common.security.SecurityUtils;
import com.ares.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: yy 2020/05/05
 **/
@RestController
@RequestMapping("/system/menu/*")
@Api(value = "系统菜单API", tags = {"系统菜单"})
public class SysMenuApiController {
    @Resource
    SysMenuService menuService;

    @PreAuthorize("hasAnyAuthority('menu:list')")
    @RequestMapping("list")
    @ApiOperation(value = "菜单列表", response = Object.class)
    public Object list(SysMenu menu) throws Exception {
        String userId = SecurityUtils.getUser().getId();
        List<SysMenu> menuList = menuService.selectMenuList(menu, userId);
        return BaseResult.successData(menuList);
    }

    @GetMapping(value = "{menuId}")
    @ApiOperation(value = "根据菜单Id获取菜单", response = Object.class)
    public Object getInfo(@PathVariable String menuId) {
        return BaseResult.successData(menuService.getById(menuId));
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("treeselect")
    @ApiOperation(value = "获取菜单下拉树列表", response = Object.class)
    public Object treeselect(SysMenu menu) throws Exception {
        String userId = SecurityUtils.getUser().getId();
        List<SysMenu> menus = menuService.selectMenuList(menu, userId);
        return BaseResult.successData(menuService.buildMenuTreeSelect(menus));
    }

    @PreAuthorize("hasAnyAuthority('menu:edit')")
    @PostMapping("edit")
    @ApiOperation(value = "新增/修改菜单", response = Object.class)
    public Object edit(@Validated @RequestBody SysMenu menu) throws Exception {
        if (StringUtils.isEmpty(menu.getId())) {
            menu.setCreator(SecurityUtils.getUser().getId());
            menuService.insert(menu);
        } else {
            menu.setModifier(SecurityUtils.getUser().getId());
            menuService.update(menu);
        }
        return BaseResult.success();
    }

    @PreAuthorize("hasAnyAuthority('menu:delete')")
    @DeleteMapping("{menuId}")
    @ApiOperation(value = "删除菜单", response = Object.class)
    public Object remove(@PathVariable String menuId) {
        if (menuService.hasChildByMenuId(menuId)) {
            return BaseResult.error("存在子菜单,不允许删除");
        }
        menuService.remove(menuId);
        return BaseResult.success();
    }

    @GetMapping(value = "roleMenuTreeselect/{roleId}")
    @ApiOperation(value = "根据角色Id获取菜单", response = Object.class)
    public Object roleMenuTreeselect(@PathVariable("roleId") String roleId) throws Exception {
        String userId = SecurityUtils.getUser().getId();
        List<SysMenu> menus = menuService.selectMenuList(new SysMenu(), userId);
        BaseResult result = BaseResult.success();
        result.put("checkedKeys", menuService.selectMenuByRole(roleId));
        result.put("menus", menuService.buildMenuTreeSelect(menus));
        return result;
    }
}
