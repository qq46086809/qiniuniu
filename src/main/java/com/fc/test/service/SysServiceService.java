package com.fc.test.service;

import com.fc.test.mapper.auto.ServiceMapper;
import com.fc.test.mapper.custom.ServiceDao;
import com.fc.test.model.auto.Service;
import com.fc.test.model.custom.Tablepar;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 * @ClassName: SysServiceService
 * @author fuce
 * @date 2018年8月26日
 *
 */
@org.springframework.stereotype.Service
public class SysServiceService {
	//生成的用户dao
	@Autowired
	private ServiceMapper serviceMapper;
	

	
	//自定义角色dao
	@Autowired
	private ServiceDao serviceDao;
	

	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<Service> list(Tablepar tablepar,String title){
	        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
	        List<Service> list= serviceDao.selectServiceList(title);
	        PageInfo<Service> pageInfo = new PageInfo<Service>(list);
	        return  pageInfo;
	 }


	public int insertSelective(Service Service) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Service.setOther1(sdf.format(d));
		return serviceDao.insertSelective(Service);
	}

	public int deleteByPrimaryKey(String ids) {
		return serviceDao.deleteSelective(ids);
	}

	public Service selectByPrimaryKey(String id) {
		return serviceDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKey(Service Service) {
		return serviceDao.updateByPrimaryKey(Service);
	}


	public String ServicecoverByPrimaryKey(String ids) {
		return serviceDao.ServicecoverByPrimaryKey(ids);
	}

	public String ServiceContentByPrimaryKey(String ids) {
		return serviceDao.ServiceContentByPrimaryKey(ids);
	}
}
