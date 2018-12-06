package com.fc.test.common.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 抽象类BasicService
* @ClassName: BasicService
* @Description: TODO(Service实现这个)
* @author fuce
* @date 2018年6月3日
*
 */
public interface BasicService<T> {
	
    int deleteByPrimaryKey(String id);

    int insertSelective(T record);

    T selectByPrimaryKey(String id);
   
    int updateByPrimaryKeySelective(T record);
    
    int updateByExampleSelective(@Param("record") T record, @Param("example") T example);

    int updateByExample(@Param("record") T record, @Param("example") T example);
    
    List<T> selectByExample(T example);

    long countByExample(T example);

    int deleteByExample(T example);

}
