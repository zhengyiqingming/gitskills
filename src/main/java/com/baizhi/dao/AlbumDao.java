package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;


//专辑
public interface AlbumDao extends IDao<Album>{
	//统计总行数
  	int count();
  	//查询每页显示几条 第几页
  	List<Album> queryByPage(@Param("curPage") Integer curPage, @Param("pageSize") Integer pageSize);
}
