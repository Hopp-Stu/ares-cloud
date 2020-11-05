package com.ares.system.controller;


import com.ares.core.controller.BaseController;
import com.ares.core.model.base.BaseResult;
import com.ares.core.model.page.TableDataInfo;
import com.ares.core.model.system.SysQuartzJob;
import com.ares.system.client.QuartzClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description:
 * @author: yy 2020/05/06
 **/
@RestController
@RequestMapping("/monitor/job/*")
@Api(value = "系统任务API", tags = {"系统任务"})
public class SysJobApiController extends BaseController {

    @Resource
    private QuartzClient quartzClient;

    @PreAuthorize("hasAnyAuthority('quartz:list')")
    @RequestMapping("list")
    @ApiOperation(value = "任务列表", response = TableDataInfo.class)
    public TableDataInfo list(SysQuartzJob job) {
        return quartzClient.list(job);
    }

    @GetMapping("{jobId}")
    @ApiOperation(value = "根据任务Id获取任务", response = Object.class)
    public Object getInfo(@PathVariable String jobId) {
        return BaseResult.successData(quartzClient.getJobInfo(jobId));
    }

    @PreAuthorize("hasAnyAuthority('quartz:edit')")
    @PostMapping("edit")
    @ApiOperation(value = "新增/修改任务", response = Object.class)
    public Object edit(@Validated @RequestBody SysQuartzJob job) {
        return BaseResult.successData(quartzClient.edit(job));
    }

    @PreAuthorize("hasAnyAuthority('quartz:delete')")
    @DeleteMapping("{jobIds}")
    @ApiOperation(value = "删除任务", response = Object.class)
    public Object remove(@PathVariable String[] jobIds) {
        return BaseResult.successData(quartzClient.removeJob(jobIds));
    }

    @PutMapping("changeStatus")
    @ApiOperation(value = "启停任务", response = Object.class)
    public Object changeStatus(@RequestBody SysQuartzJob job) {
        return BaseResult.successData(quartzClient.changeStatus(job));
    }

    /**
     * 定时任务立即执行一次
     */
    @PutMapping("run")
    @ApiOperation(value = "执行任务", response = Object.class)
    public Object run(@RequestBody SysQuartzJob job) {
        return BaseResult.successData(quartzClient.run(job));
    }
}
