package com.fc.test.mapper.custom;

import com.fc.test.model.auto.Service;

import java.util.List;

public interface ServiceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Service record);

    int insertSelective(Service record);

    Service selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKeyWithBLOBs(Service record);

    int updateByPrimaryKey(Service record);

    List<Service> selectServiceList(String title);

    String ServicecoverByPrimaryKey(String ids);

    String ServiceContentByPrimaryKey(String ids);

    int deleteSelective(String ids);

    int updateServiceDown(String id);

    int updateServiceUp(String id);
}