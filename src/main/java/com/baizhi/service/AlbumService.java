package com.baizhi.service;


import com.baizhi.dto.AlbumDto;
import com.baizhi.entity.Album;

public interface AlbumService extends IService<Album> {
	//分页查
	AlbumDto queryByPage(int curPage, int pageSize);
}
