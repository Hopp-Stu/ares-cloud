package com.ares.system.service;


import com.ares.core.model.system.SysNotice;
import com.ares.core.service.BaseService;
import com.ares.core.utils.SnowflakeIdWorker;
import com.ares.system.dao.ISysNoticeDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysNoticeService implements BaseService<SysNotice> {

    @Resource
    ISysNoticeDao sysNoticeDao;

    public PageInfo<SysNotice> list(int pageNo, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(pageNo, pageSize);
        List<SysNotice> lists = sysNoticeDao.list(map);
        PageInfo<SysNotice> pageInfo = new PageInfo<>(lists);
        return pageInfo;
    }

    @Override
    public void insert(SysNotice obj) {
        obj.setId(SnowflakeIdWorker.getUUID());
        obj.setCreateTime(new Date());
        sysNoticeDao.insert(obj);
    }

    @Override
    public void update(SysNotice obj) {
        obj.setModifyTime(new Date());
        sysNoticeDao.update(obj);
    }

    @Override
    public void deleteByIds(List<String> ids) {
        sysNoticeDao.deleteByIds(ids);
    }

    @Override
    public SysNotice getById(String id) {
        return sysNoticeDao.getById(id);
    }

    public List<SysNotice> list(SysNotice obj) {
        List<SysNotice> lists = sysNoticeDao.selectList(obj);
        return lists;
    }

    public int noticeNum(){
        return sysNoticeDao.noticeNum();
    }

    public List<SysNotice> getNotices(){
        return sysNoticeDao.getNotices();
    }

}