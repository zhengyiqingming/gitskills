package com.baizhi.dao;

import java.util.List;

public interface IDao <T>{

    void insertItem(T t);
    void updateItem(T t);
    void deleteItem(int id);
    T queryOneItem(int id);
    List<T> queryItems();
}
