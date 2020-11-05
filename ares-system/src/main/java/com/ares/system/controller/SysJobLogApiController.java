package com.ares.system.controller;


import com.ares.core.controller.BaseController;
import com.ares.core.model.base.BaseResult;
import com.ares.core.model.page.TableDataInfo;
import com.ares.core.model.system.SysQuartzJobLog;
import com.ares.system.client.QuartzClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description:
 * @author: yy 2020/05/07
 **/
@RestController
@RequestMapping("/monitor/jobLog/*")
@Api(value = "系统任务日志API", tags = {"系统任务日志"})
public class SysJobLogApiController extends BaseController {

    @Resource
    private QuartzClient quartzClient;

    @PreAuthorize("hasAnyAuthority('quartz:logList')")
    @GetMapping("list")
    @ApiOperation(value = "任务日志列表", response = TableDataInfo.class)
    public TableDataInfo list(SysQuartzJobLog jobLog) {
        return quartzClient.list(jobLog);
    }

    /**
     * 根据调度编号获取详细信息
     */
    @GetMapping(value = "{jobLogId}")
    @ApiOperation(value = "根据调度编号获取详细信息", response = Object.class)
    public Object getInfo(@PathVariable String jobLogId) {
        return BaseResult.successData(quartzClient.getInfo(jobLogId));
    }

    /**
     * 删除定时任务调度日志
     */
    @PreAuthorize("hasAnyAuthority('quartz:logDelete')")
    @DeleteMapping("{jobLogIds}")
    @ApiOperation(value = "删除定时任务调度日志", response = Object.class)
    public Object remove(@PathVariable String[] jobLogIds) {
        return BaseResult.successData(quartzClient.remove(jobLogIds));
    }

    @PreAuthorize("hasAnyAuthority('quartz:logDelete')")
    @DeleteMapping("clean")
    @ApiOperation(value = "清空定时任务调度日志", response = Object.class)
    public Object clean() {
        return BaseResult.successData(quartzClient.clean());
    }
}
