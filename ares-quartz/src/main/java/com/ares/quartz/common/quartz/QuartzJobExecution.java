package com.ares.quartz.common.quartz;


import com.ares.core.model.system.SysQuartzJob;
import com.ares.quartz.utils.JobInvokeUtil;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, SysQuartzJob sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }

}
