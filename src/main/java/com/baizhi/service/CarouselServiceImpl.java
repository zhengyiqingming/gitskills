package com.baizhi.service;


import com.baizhi.annotations.FlushCache;
import com.baizhi.annotations.RedisCache;
import com.baizhi.dao.CarouselDao;
import com.baizhi.dto.CarouselDto;
import com.baizhi.entity.Carousel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.List;

@Service("carouselService")
@Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
public class CarouselServiceImpl implements CarouselService {
	//DI注入
	@Resource(name="carouselDao")
	private CarouselDao dao;

	@Resource
	private Jedis jedis;
	
	//添加
	@Transactional
	@Override
	@FlushCache
	public void addItem(Carousel t) {
		dao.insertItem(t);
	}
	
	@Transactional
	@Override
	@FlushCache
	public void modifyItem(Carousel t) {
		dao.updateItem(t);
	}
	
	@Transactional
	@Override
	@FlushCache
	public void removeItem(int id) {
		dao.deleteItem(id);
	}

	@Override
	public Carousel findOneItem(int id) {
		Carousel carousel = dao.queryOneItem(id);
		return carousel;
	}

	@Override
	public List<Carousel> findItems() {
		List<Carousel> list = dao.queryItems();
		return list;
	}

	@Override
	@RedisCache
	public CarouselDto queryByPage(int curPage, int pageSize) {
		CarouselDto dto = new CarouselDto();
		dto.setTotal(dao.count());
		dto.setRows(dao.queryByPage(curPage, pageSize));
		return dto;
	}

}
