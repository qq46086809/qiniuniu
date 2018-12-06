package com.fc.test.mapper.custom;

import com.fc.test.model.auto.News;

import java.util.List;

/**
 * @ClassName: NewsDao
 * @author fuce
 * @date 2018年8月25日
 *
 */
public interface NewsDao {

	List<News> selectNewsList(String title);

	int insertSelective(News news);

	int deleteSelective(String ids);

	News selectByPrimaryKey(String id);

	int updateByPrimaryKey(News news);
}
