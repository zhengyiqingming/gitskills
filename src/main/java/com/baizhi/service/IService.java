package com.baizhi.service;

import java.util.List;

public interface IService <T>{

    void addItem(T t);
    void modifyItem(T t);
    void removeItem(int id);
    T findOneItem(int id);
    List<T> findItems();

}
