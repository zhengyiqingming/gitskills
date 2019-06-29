package com.baizhi.service;


import com.baizhi.dto.UserDto;
import com.baizhi.entity.User;

public interface UserService extends IService<User> {
	//登录
	User findOneUser(String username, String password);
	//分页查
	UserDto queryByPage(int curPage, int pageSize);
}
