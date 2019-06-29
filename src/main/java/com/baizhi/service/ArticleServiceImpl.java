package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {
	@Resource(name="articleDao")
	private ArticleDao articleDao;
	
	@Override
	public void addItem(Article t) {
		// TODO Auto-generated method stub
		articleDao.insertItem(t);
	}

	@Override
	public void modifyItem(Article t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeItem(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Article findOneItem(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> findItems() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
