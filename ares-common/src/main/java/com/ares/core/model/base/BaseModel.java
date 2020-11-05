package com.ares.core.model.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author: yy 2020/01/22
 **/
@Data
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 2513447910190608371L;
    @ApiModelProperty("主键")
    private String Id;
    @ApiModelProperty("创建人")
    private String creator;
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty("修改人")
    private String modifier;
    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    /** =============================查询参数================================= */
    /**
     * 开始时间
     */
    @JsonIgnore
    private String beginTime;
    /**
     * 结束时间
     */
    @JsonIgnore
    private String endTime;
    @JsonIgnore
    private String sortColumn;
    @JsonIgnore
    private String sortAsc;
    @JsonIgnore
    private String sort;
    /**
     * 请求参数
     */
    private Map<String, Object> params;

}
