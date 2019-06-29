package com.baizhi.lucene.dao;

import com.baizhi.entity.Book;

import java.util.List;

public interface BookLuceneDAO {

    public List<Book> find(String keywords);
}
