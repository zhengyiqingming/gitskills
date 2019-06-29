package com.baizhi.service;


import com.baizhi.dto.CarouselDto;
import com.baizhi.entity.Carousel;

public interface CarouselService extends IService<Carousel> {
	//分页查
	CarouselDto queryByPage(int curPage, int pageSize);
}
