package com.baizhi.controller;

import com.baizhi.dto.AlbumDto;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("album")
public class AlbumController {
	//DI注入
	@Resource(name="albumService")
	private AlbumService service;

	//查一个
	@ResponseBody
	@RequestMapping("findOneAlbum")
	public Album findOneAlbum(Integer id){
		return service.findOneItem(id);
	}
	
	//添加
	@ResponseBody
	@RequestMapping("addAlbum")
	public void addAlbum(Album album,MultipartFile file,HttpSession session) throws IllegalStateException, IOException{
		String realPath = session.getServletContext().getRealPath("/shangshi");
		File serverFile = new File(realPath+"/"+file.getOriginalFilename());
		file.transferTo(serverFile);
		album.setPublishDate(new Date());
		album.setCoverImg("../shangshi/"+file.getOriginalFilename());
		service.addItem(album);
	}

	
	//查所有
	@ResponseBody
	@RequestMapping("findAll")
	public List<Album> findAll(){
		return service.findItems();
	}
	
	//分页
	@RequestMapping("queryBy")
	@ResponseBody
	public AlbumDto queryBy(int page, int rows){
		AlbumDto dto = service.queryByPage(page, rows);
		return dto;
	}
}
