package com.baizhi.service;


import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("chapterService")
public class ChapterServiceImpl implements ChapterService {
	//DI注入
	@Resource(name="chapterDao")
	private ChapterDao dao;
	
	//添加
	@Override
	public void addItem(Chapter t) {
		dao.insertItem(t);

	}

	@Override
	public void modifyItem(Chapter t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeItem(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Chapter findOneItem(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Chapter> findItems() {
		// TODO Auto-generated method stub
		return null;
	}

}
