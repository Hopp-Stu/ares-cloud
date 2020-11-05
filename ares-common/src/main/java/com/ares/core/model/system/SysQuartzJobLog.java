package com.ares.core.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "SysQuartzJobLog对象",description = "任务日志")
public class SysQuartzJobLog implements Serializable {
    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("任务名称")
    private String jobName;
    @ApiModelProperty("任务分组")
    private String jobGroup;
    @ApiModelProperty("目标类")
    private String invokeTarget;
    @ApiModelProperty("任务消息")
    private String jobMessage;
    @ApiModelProperty("任务状态")
    private Integer status;
    @ApiModelProperty("异常信息")
    private String exceptionInfo;
    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    @ApiModelProperty("完成时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date finishTime;
    @ApiModelProperty("开始时间")
    private String beginTime;
    @ApiModelProperty("结束时间")
    private String endTime;

}