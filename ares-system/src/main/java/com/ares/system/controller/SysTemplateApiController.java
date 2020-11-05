package com.ares.system.controller;


import com.ares.core.controller.BaseController;
import com.ares.core.model.system.SysTemplate;
import com.ares.core.model.base.BaseResult;
import com.ares.core.model.page.TableDataInfo;
import com.ares.core.utils.StringUtils;
import com.ares.system.common.security.SecurityUtils;
import com.ares.system.service.SysTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/sysTemplate/*")
@Api(value = "系统模版API",tags = {"系统模版"})
public class SysTemplateApiController extends BaseController {

    @Resource
    SysTemplateService sysTemplateService;

    @PreAuthorize("hasAnyAuthority('sysTemplate:list')")
    @RequestMapping("list")
    @ApiOperation(value = "模版列表",response = TableDataInfo.class)
    public TableDataInfo list(SysTemplate sysTemplate) {
        startPage();
        List<SysTemplate> sysTemplateList = sysTemplateService.list(sysTemplate);
        return getDataTable(sysTemplateList);
    }

    @GetMapping("{sysTemplateId}")
    @ApiOperation(value = "根据模版Id获取模版",response = Object.class)
    public Object getInfo(@PathVariable String sysTemplateId) {
        return BaseResult.successData(sysTemplateService.getById(sysTemplateId));
    }

    @PreAuthorize("hasAnyAuthority('sysTemplate:edit')")
    @PostMapping("edit")
    @ApiOperation(value = "新增/修改模版",response = Object.class)
    public Object edit(@Validated @RequestBody SysTemplate sysTemplate) throws Exception {
        if (StringUtils.isEmpty(sysTemplate.getId())) {
            sysTemplate.setCreator(SecurityUtils.getUser().getId());
            sysTemplateService.insert(sysTemplate);
        } else {
            sysTemplate.setModifier(SecurityUtils.getUser().getId());
            sysTemplateService.update(sysTemplate);
        }
        return BaseResult.success();
    }

    @PreAuthorize("hasAnyAuthority('sysTemplate:delete')")
    @DeleteMapping("{sysTemplateIds}")
    @ApiOperation(value = "删除模版",response = Object.class)
    public Object remove(@PathVariable String[] sysTemplateIds) {
        sysTemplateService.deleteByIds(Arrays.asList(sysTemplateIds));
        return BaseResult.success();
    }
}
