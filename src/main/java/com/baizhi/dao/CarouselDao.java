package com.baizhi.dao;


import com.baizhi.entity.Carousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarouselDao extends IDao<Carousel> {
	//统计总行数
  	int count();
  	//查询每页显示几条 第几页
  	List<Carousel> queryByPage(@Param("curPage") Integer curPage, @Param("pageSize") Integer pageSize);
}
