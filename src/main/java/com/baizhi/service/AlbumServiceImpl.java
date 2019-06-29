package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dto.AlbumDto;
import com.baizhi.entity.Album;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
@Service("albumService")
public class AlbumServiceImpl implements AlbumService {
	//注入DI
	@Resource(name="albumDao")
	private AlbumDao dao;
	
	//添加
	@Transactional
	public void addItem(Album t) {
		dao.insertItem(t);

	}

	//查一 

	public Album findOneItem(int id) {
		
		return dao.queryOneItem(id);
	}
	
	//查所有
	@Override
	public List<Album> findItems() {
		
		return dao.queryItems();
	}
	
	@Override
	public void modifyItem(Album t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeItem(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public AlbumDto queryByPage(int curPage, int pageSize) {
		AlbumDto dto = new AlbumDto();
		dto.setTotal(dao.count());
		dto.setRows(dao.queryByPage(curPage, pageSize));
		return dto;
	}
	
	

}
