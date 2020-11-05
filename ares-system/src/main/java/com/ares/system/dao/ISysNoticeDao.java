package com.ares.system.dao;


import com.ares.core.dao.IBaseDao;
import com.ares.core.model.system.SysNotice;

import java.util.List;

public interface ISysNoticeDao extends IBaseDao<SysNotice> {

    int noticeNum();

    List<SysNotice> getNotices();

}