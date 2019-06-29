package com.baizhi.dao;


import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao extends IDao<User>{
	User queryOneUser(@Param("username") String username, @Param("password") String password);
	//统计总行数
  	int count();
  	//查询每页显示几条 第几页
  	List<User> queryByPage(@Param("curPage") Integer curPage, @Param("pageSize") Integer pageSize);
}
