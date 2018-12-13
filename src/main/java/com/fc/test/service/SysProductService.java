package com.fc.test.service;

import com.fc.test.mapper.auto.ProductMapper;
import com.fc.test.mapper.custom.ProductDao;
import com.fc.test.model.auto.Product;
import com.fc.test.model.custom.Tablepar;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 * @ClassName: SysProductProduct
 * @author fuce
 * @date 2018年8月26日
 *
 */
@org.springframework.stereotype.Service
public class SysProductService {
	//生成的用户dao
	@Autowired
	private ProductMapper ProductMapper;
	

	
	//自定义角色dao
	@Autowired
	private ProductDao ProductDao;
	

	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<Product> list(Tablepar tablepar,String title){
	        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
	        List<Product> list= ProductDao.selectProductList(title);
	        PageInfo<Product> pageInfo = new PageInfo<Product>(list);
	        return  pageInfo;
	 }


	public int insertSelective(Product Product) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Product.setOther1(sdf.format(d));
		return ProductDao.insertSelective(Product);
	}

	public int deleteByPrimaryKey(String ids) {
		return ProductDao.deleteSelective(ids);
	}

	public Product selectByPrimaryKey(String id) {
		return ProductDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKey(Product Product) {
		return ProductDao.updateByPrimaryKey(Product);
	}


	public String ProductcoverByPrimaryKey(String ids) {
		return ProductDao.ProductcoverByPrimaryKey(ids);
	}

	public String ProductContentByPrimaryKey(String ids) {
		return ProductDao.ProductContentByPrimaryKey(ids);
	}

	public int updateProductDown(String id) {
		return ProductDao.updateProductDown(id);
	}

	public int updateProductUp(String id) {
		return ProductDao.updateProductUp(id);
	}
}
