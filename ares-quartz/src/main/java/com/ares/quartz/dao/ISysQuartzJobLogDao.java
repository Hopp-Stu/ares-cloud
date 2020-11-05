package com.ares.quartz.dao;


import com.ares.core.dao.IBaseDao;
import com.ares.core.model.system.SysQuartzJobLog;

public interface ISysQuartzJobLogDao extends IBaseDao<SysQuartzJobLog> {

    int cleanJobLog();

}