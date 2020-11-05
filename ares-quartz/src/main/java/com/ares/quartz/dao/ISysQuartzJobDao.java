package com.ares.quartz.dao;


import com.ares.core.dao.IBaseDao;
import com.ares.core.model.system.SysQuartzJob;

public interface ISysQuartzJobDao extends IBaseDao<SysQuartzJob> {

    Integer checkUnique(String jobName);

}