package com.baizhi.service;


import com.baizhi.entity.Admin;

public interface AdminService extends IService<Admin>{
	//登录
	Admin findOneAdmin(String name, String password);
}
