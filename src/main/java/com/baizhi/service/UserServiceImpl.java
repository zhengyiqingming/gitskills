package com.baizhi.service;


import com.baizhi.annotations.RedisCache;
import com.baizhi.dao.UserDao;
import com.baizhi.dto.UserDto;
import com.baizhi.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
@Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
public class UserServiceImpl implements UserService {
	
	//DI注入
	@Resource(name="userDao")
	private UserDao dao;
	
	//添加
	@Transactional
	public void addItem(User t) {
		dao.insertItem(t);
	}
	
	//修改
	@Transactional
	@Override
	public void modifyItem(User t) {
		dao.updateItem(t);
	}
	
	//删除
	@Transactional
	@Override
	public void removeItem(int id) {
		dao.deleteItem(id);
	}

	//查询
	@Override
	public User findOneItem(int id) {
		return dao.queryOneItem(id);
	}
	
	//查所有
	@Override
	public List<User> findItems() {
		return dao.queryItems();
	}

	//登录
	@Override
	public User findOneUser(String username, String password) {
		return dao.queryOneUser(username, password);
	}

	@Override
	@RedisCache
	public UserDto queryByPage(int curPage, int pageSize) {
		UserDto dto = new UserDto();
		dto.setTotal(dao.count());
		dto.setRows(dao.queryByPage(curPage, pageSize));
		return dto;
	}

}
