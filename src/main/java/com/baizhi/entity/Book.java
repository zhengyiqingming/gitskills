package com.baizhi.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Book {
    private String id;
    private String name;
    private Double price;
    private String author;
    private String content;

}
