package com.baizhi.dto;


import com.baizhi.entity.Carousel;

import java.util.List;

public class CarouselDto {
	private Integer total;
	private List<Carousel> rows;
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<Carousel> getRows() {
		return rows;
	}
	public void setRows(List<Carousel> rows) {
		this.rows = rows;
	}
	
}
