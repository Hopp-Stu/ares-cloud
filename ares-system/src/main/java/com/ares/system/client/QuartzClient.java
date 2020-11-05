package com.ares.system.client;

import com.ares.core.model.page.TableDataInfo;
import com.ares.core.model.system.SysQuartzJob;
import com.ares.core.model.system.SysQuartzJobLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/22
 * @see: com.ares.system.client QuartzClient.java
 **/
@FeignClient(value = "Ares-Quartz")
public interface QuartzClient {

    @RequestMapping(value = "/quartz/monitor/job/list")
    public TableDataInfo list(@RequestBody SysQuartzJob job);

    @RequestMapping(value = "/quartz/monitor/job/{jobId}", method = RequestMethod.GET)
    public Object getJobInfo(@PathVariable String jobId);

    @RequestMapping(value = "/quartz/monitor/job/edit", method = RequestMethod.POST)
    public Object edit(@Validated @RequestBody SysQuartzJob job);

    @RequestMapping(value = "/quartz/monitor/job/{jobIds}", method = RequestMethod.DELETE)
    public Object removeJob(@PathVariable String[] jobIds);

    @RequestMapping(value = "/quartz/monitor/job/changeStatus", method = RequestMethod.PUT)
    public Object changeStatus(@RequestBody SysQuartzJob job);

    @RequestMapping(value = "/quartz/monitor/job/run", method = RequestMethod.PUT)
    public Object run(@RequestBody SysQuartzJob job);

    @RequestMapping(value = "/quartz/monitor/jobLog/list", method = RequestMethod.GET)
    public TableDataInfo list(SysQuartzJobLog jobLog);

    @RequestMapping(value = "/quartz/monitor/jobLog/{jobLogId}", method = RequestMethod.GET)
    public Object getInfo(@PathVariable String jobLogId);

    @RequestMapping(value = "/quartz/monitor/jobLog/{jobLogIds}", method = RequestMethod.DELETE)
    public Object remove(@PathVariable String[] jobLogIds);

    @RequestMapping(value = "/quartz/monitor/jobLog/clean", method = RequestMethod.DELETE)
    public Object clean();

}
