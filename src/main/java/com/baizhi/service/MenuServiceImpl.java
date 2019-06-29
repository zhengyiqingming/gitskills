package com.baizhi.service;


import com.baizhi.dao.MenuDao;
import com.baizhi.entity.Menu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
@Service("menuService")
public class MenuServiceImpl implements MenuService {
	
	//DI
	@Resource(name="menuDao")
	private MenuDao dao;
	
	//查询所有
	@Override
	public List<Menu> findItems() {
		List<Menu> list = dao.queryItems();
		return list;
	}
	
	
	
	@Override
	public void addItem(Menu t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyItem(Menu t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeItem(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Menu findOneItem(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
