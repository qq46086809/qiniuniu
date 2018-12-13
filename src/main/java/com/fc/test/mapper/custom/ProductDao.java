package com.fc.test.mapper.custom;

import com.fc.test.model.auto.Product;

import java.util.List;

public interface ProductDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectProductList(String title);

    String ProductcoverByPrimaryKey(String ids);

    String ProductContentByPrimaryKey(String ids);

    int deleteSelective(String ids);

    int updateProductDown(String id);

    int updateProductUp(String id);
}