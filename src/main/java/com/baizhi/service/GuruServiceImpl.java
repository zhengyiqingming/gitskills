package com.baizhi.service;


import com.baizhi.dao.GuruDao;
import com.baizhi.entity.Guru;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("guruService")
@Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
public class GuruServiceImpl implements GuruService{
	@Resource(name="guruDao")
	private GuruDao guruDao;
	

	@Transactional
	public void addItem(Guru t) {
		guruDao.insertItem(t);
		
	}


	public void modifyItem(Guru t) {
		
		
	}

	@Override
	public void removeItem(int id) {
		
		
	}
	
	//查一
	@Override
	public Guru findOneItem(int id) {
		return guruDao.queryOneItem(id);
	}
	
	//查所有
	@Override
	public List<Guru> findItems() {
		
		return guruDao.queryItems();
	}

	
	
}
