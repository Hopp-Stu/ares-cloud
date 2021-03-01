package com.ares.quartz.controller;


import com.ares.core.controller.BaseController;
import com.ares.core.model.base.AjaxResult;
import com.ares.core.model.page.TableDataInfo;
import com.ares.core.utils.StringUtils;
import com.ares.core.model.system.SysQuartzJob;
import com.ares.quartz.service.SysQuartzJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.SchedulerException;
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
@RequestMapping("/monitor/job/*")
@Api(value = "系统任务API", tags = {"系统任务"})
public class SysJobController extends BaseController {

    @Resource
    SysQuartzJobService jobService;

    @RequestMapping("list")
    @ApiOperation(value = "任务列表", response = TableDataInfo.class)
    public TableDataInfo list(SysQuartzJob job) {
        startPage();
        List<SysQuartzJob> jobList = jobService.selectJobList(job);
        return getDataTable(jobList);
    }

    @GetMapping("{jobId}")
    @ApiOperation(value = "根据任务Id获取任务", response = Object.class)
    public Object getInfo(@PathVariable String jobId) {
        return AjaxResult.successData(jobService.getById(jobId));
    }

    @PostMapping("edit")
    @ApiOperation(value = "新增/修改任务", response = Object.class)
    public Object edit(@Validated @RequestBody SysQuartzJob job) throws Exception {
        if (StringUtils.isEmpty(job.getId())) {
            if (jobService.checkUnique(job.getJobName()) != 0) {
                return AjaxResult.error("新增任务'" + job.getJobName() + "'失败，任务名称已存在");
            }
            job.setCreator(job.getUserId());
            jobService.insert(job);
        } else {
            job.setModifier(job.getUserId());
            jobService.update(job);
        }
        return AjaxResult.success();
    }

    @DeleteMapping("{jobIds}")
    @ApiOperation(value = "删除任务", response = Object.class)
    public Object remove(@PathVariable String[] jobIds) {
        jobService.deleteByIds(Arrays.asList(jobIds));
        return AjaxResult.success();
    }

    @PutMapping("changeStatus")
    @ApiOperation(value = "启停任务", response = Object.class)
    public Object changeStatus(@RequestBody SysQuartzJob job) throws Exception {
        SysQuartzJob newJob = jobService.getById(job.getId());
        newJob.setStatus(job.getStatus());
        newJob.setModifier(job.getUserId());
        jobService.changeStatus(newJob);
        return AjaxResult.success();
    }

    /**
     * 定时任务立即执行一次
     */
    @PutMapping("run")
    @ApiOperation(value = "执行任务", response = Object.class)
    public Object run(@RequestBody SysQuartzJob job) throws SchedulerException {
        SysQuartzJob newJob = jobService.getById(job.getId());
        jobService.run(newJob);
        return AjaxResult.success();
    }
}
