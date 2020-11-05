package com.ares.system.dao;


import com.ares.core.dao.IBaseDao;
import com.ares.core.model.system.SysDictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISysDictDataDao extends IBaseDao<SysDictData> {
    List<SysDictData> getDicts(@Param("dictType") String dictType);
}