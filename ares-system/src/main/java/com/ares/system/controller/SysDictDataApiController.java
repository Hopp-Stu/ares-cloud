package com.ares.system.controller;

import com.ares.core.controller.BaseController;
import com.ares.core.model.system.SysDictData;
import com.ares.core.model.base.BaseResult;
import com.ares.core.model.page.TableDataInfo;
import com.ares.core.utils.StringUtils;
import com.ares.system.common.security.SecurityUtils;
import com.ares.system.service.SysDictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/system/dict/data/*")
@Api(value = "字典数据API", tags = {"字典数据"})
public class SysDictDataApiController extends BaseController {

    @Resource
    SysDictDataService sysDictDataService;


    @PreAuthorize("hasAnyAuthority('sysDictData:list')")
    @RequestMapping("list")
    @ApiOperation(value = "字典数据列表", response = TableDataInfo.class)
    public TableDataInfo list(SysDictData sysDictData) {
        startPage();
        List<SysDictData> sysDictDataList = sysDictDataService.list(sysDictData);
        return getDataTable(sysDictDataList);
    }

    @GetMapping("{sysDictDataId}")
    @ApiOperation(value = "根据Id获取字典数据", response = Object.class)
    public Object getInfo(@PathVariable String sysDictDataId) {
        return BaseResult.successData(sysDictDataService.getById(sysDictDataId));
    }

    @PreAuthorize("hasAnyAuthority('sysDictData:edit')")
    @PostMapping("edit")
    @ApiOperation(value = "编辑字典数据", response = Object.class)
    public Object edit(@Validated @RequestBody SysDictData sysDictData) throws Exception {
        if (StringUtils.isEmpty(sysDictData.getId())) {
            sysDictData.setCreator(SecurityUtils.getUser().getId());
            sysDictDataService.insert(sysDictData);
        } else {
            sysDictData.setModifier(SecurityUtils.getUser().getId());
            sysDictDataService.update(sysDictData);
        }
        return BaseResult.success();
    }

    @PreAuthorize("hasAnyAuthority('sysDictData:delete')")
    @DeleteMapping("{sysDictDataIds}")
    @ApiOperation(value = "删除字典数据", response = Object.class)
    public Object remove(@PathVariable String[] sysDictDataIds) {
        sysDictDataService.deleteByIds(Arrays.asList(sysDictDataIds));
        return BaseResult.success();
    }

    @GetMapping("dictType/{dictType}")
    @ApiOperation(value = "根据类别获取字典数据", response = Object.class)
    public Object getDicts(@PathVariable String dictType) {
        return BaseResult.successData(sysDictDataService.getDicts(dictType));
    }
}
