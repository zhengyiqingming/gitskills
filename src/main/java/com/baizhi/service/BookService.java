package com.baizhi.service;

import com.baizhi.entity.Book;

import java.util.List;

public interface BookService {

    public List<Book> find(String keywords);
}
