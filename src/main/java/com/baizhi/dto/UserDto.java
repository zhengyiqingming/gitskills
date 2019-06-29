package com.baizhi.dto;


import com.baizhi.entity.User;

import java.util.List;

public class UserDto {
	private Integer total;
	private List<User> rows;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<User> getRows() {
		return rows;
	}
	public void setRows(List<User> rows) {
		this.rows = rows;
	}
	
	
	
	
}
