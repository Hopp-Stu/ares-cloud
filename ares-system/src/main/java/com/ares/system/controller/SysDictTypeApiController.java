package com.ares.system.controller;

import com.ares.core.controller.BaseController;
import com.ares.core.model.system.SysDictType;
import com.ares.core.model.base.AjaxResult;
import com.ares.core.model.page.TableDataInfo;
import com.ares.core.utils.StringUtils;
import com.ares.system.common.security.SecurityUtils;
import com.ares.system.service.SysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/system/dict/type/*")
@Api(value = "字典类别API", tags = {"字典类别"})
public class SysDictTypeApiController extends BaseController {

    @Resource
    SysDictTypeService sysDictTypeService;


    @PreAuthorize("hasAnyAuthority('sysDictType:list')")
    @RequestMapping("list")
    @ApiOperation(value = "字典类别列表", response = TableDataInfo.class)
    public TableDataInfo list(SysDictType sysDictType) {
        startPage();
        List<SysDictType> sysDictTypeList = sysDictTypeService.list(sysDictType);
        return getDataTable(sysDictTypeList);
    }

    @GetMapping("{sysDictTypeId}")
    @ApiOperation(value = "根据Id获取字典类别", response = Object.class)
    public Object getInfo(@PathVariable String sysDictTypeId) {
        return AjaxResult.successData(sysDictTypeService.getById(sysDictTypeId));
    }

    @PreAuthorize("hasAnyAuthority('sysDictType:edit')")
    @PostMapping("edit")
    @ApiOperation(value = "编辑字典类别", response = Object.class)
    public Object edit(@Validated @RequestBody SysDictType sysDictType) throws Exception {
        if (StringUtils.isEmpty(sysDictType.getId())) {
            sysDictType.setCreator(SecurityUtils.getUser().getId());
            sysDictTypeService.insert(sysDictType);
        } else {
            sysDictType.setModifier(SecurityUtils.getUser().getId());
            sysDictTypeService.update(sysDictType);
        }
        return AjaxResult.success();
    }

    @PreAuthorize("hasAnyAuthority('sysDictType:delete')")
    @DeleteMapping("{sysDictTypeIds}")
    @ApiOperation(value = "删除字典类别", response = Object.class)
    public Object remove(@PathVariable String[] sysDictTypeIds) {
        sysDictTypeService.deleteByIds(Arrays.asList(sysDictTypeIds));
        return AjaxResult.success();
    }

}
