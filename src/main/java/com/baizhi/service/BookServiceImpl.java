package com.baizhi.service;

import com.baizhi.entity.Book;
import com.baizhi.lucene.dao.BookLuceneDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

    @Resource
    private BookLuceneDAO bookLuceneDAO;

    @Override
    public List<Book> find(String keywords) {
        return bookLuceneDAO.find(keywords) ;
    }
}
