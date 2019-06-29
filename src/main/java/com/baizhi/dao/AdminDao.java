package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;


public interface AdminDao extends IDao<Admin>{
	Admin queryOneAdmin(@Param("name") String name, @Param("password") String password);
}
