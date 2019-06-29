package com.baizhi.dto;


import com.baizhi.entity.Album;

import java.util.List;

public class AlbumDto {
	private Integer total;
	private List<Album> rows;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<Album> getRows() {
		return rows;
	}
	public void setRows(List<Album> rows) {
		this.rows = rows;
	}
	
	
	
}
