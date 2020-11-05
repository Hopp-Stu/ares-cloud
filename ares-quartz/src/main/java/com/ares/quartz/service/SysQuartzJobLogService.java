package com.ares.quartz.service;

import com.ares.core.service.BaseService;
import com.ares.core.utils.SnowflakeIdWorker;
import com.ares.quartz.dao.ISysQuartzJobLogDao;
import com.ares.core.model.system.SysQuartzJobLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: yy 2020/01/29
 **/
@Service
public class SysQuartzJobLogService implements BaseService<SysQuartzJobLog> {

    @Resource
    ISysQuartzJobLogDao sysQuartzJobLogDao;

    @Override
    public void insert(SysQuartzJobLog obj) {
        obj.setId(SnowflakeIdWorker.getUUID());
        sysQuartzJobLogDao.insert(obj);
    }

    @Override
    public void update(SysQuartzJobLog obj) {

    }

    @Override
    public void deleteByIds(List<String> ids) {
        sysQuartzJobLogDao.deleteByIds(ids);
    }

    @Override
    public SysQuartzJobLog getById(String id) {
        return sysQuartzJobLogDao.getById(id);
    }

    public PageInfo<SysQuartzJobLog> list(int pageNo, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(pageNo, pageSize);
        List<SysQuartzJobLog> sysQuartzJobLogList = sysQuartzJobLogDao.list(map);
        PageInfo<SysQuartzJobLog> jobPageInfo = new PageInfo<>(sysQuartzJobLogList);
        return jobPageInfo;
    }

    public List<SysQuartzJobLog> selectJobLogList(SysQuartzJobLog jobLog){
        return sysQuartzJobLogDao.selectList(jobLog);
    }

    public void cleanJobLog(){
        sysQuartzJobLogDao.cleanJobLog();
    }
}
