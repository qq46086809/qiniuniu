package com.fc.test.service;

import com.fc.test.mapper.auto.NewsMapper;
import com.fc.test.mapper.custom.NewsDao;
import com.fc.test.model.auto.*;
import com.fc.test.model.custom.Tablepar;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 * @ClassName: SysUserService
 * @author fuce
 * @date 2018年8月26日
 *
 */
@Service
public class SysNewService {
	//生成的用户dao
	@Autowired
	private NewsMapper tsysNewMapper;
	

	
	//自定义角色dao
	@Autowired
	private NewsDao newsDao;
	

	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<News> list(Tablepar tablepar,String username){
	        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
	        List<News> list= newsDao.selectNewsList(username);
	        PageInfo<News> pageInfo = new PageInfo<News>(list);
	        return  pageInfo;
	 }


	public int insertSelective(News news) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		news.setCreatetime(sdf.format(d));
		return newsDao.insertSelective(news);
	}

	public int deleteByPrimaryKey(String ids) {
		return newsDao.deleteSelective(ids);
	}

	public News selectByPrimaryKey(String id) {
		return newsDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKey(News news) {
		return newsDao.updateByPrimaryKey(news);
	}

	public String NewsDescByPrimaryKey(String ids) {
		return newsDao.NewsDescByPrimaryKey(ids);
	}
}
