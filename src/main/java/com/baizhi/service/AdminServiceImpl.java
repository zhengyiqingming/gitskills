package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	//DI注入
	@Resource(name="adminDao")
	private AdminDao dao;
	
	
	//登录
	@Override
	public Admin findOneAdmin(String name, String password) {
		Admin admin = dao.queryOneAdmin(name, password);
		return admin;
	}
	
	//查一
	@Override
	public Admin findOneItem(int id) {
		Admin admin = dao.queryOneItem(id);
		return admin;
	}
	
	//修改
	@Transactional
	@Override
	public void modifyItem(Admin t) {
		dao.updateItem(t);
	}
	
	@Override
	public void addItem(Admin t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeItem(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Admin> findItems() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
