package com.baizhi.controller;


import com.baizhi.dto.CarouselDto;
import com.baizhi.entity.Carousel;
import com.baizhi.service.CarouselService;
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
@RequestMapping("carousel")
public class CarouselController {
	
	//DI注入
	@Resource(name="carouselService")
	private CarouselService service;
	
	//添加
	@ResponseBody
	@RequestMapping("addCarousel")
	public void addCarousel(Carousel carousel, MultipartFile file, HttpSession session) throws IllegalStateException, IOException {
		
		String realPath = session.getServletContext().getRealPath("/shangshi");
		
		File serverFile = new File(realPath+"/"+file.getOriginalFilename());
		file.transferTo(serverFile);
		
		carousel.setDate(new Date());
		carousel.setImgPath("shangshi/"+file.getOriginalFilename());
		service.addItem(carousel);
		
		
	}
	
	//删除
	@ResponseBody
	@RequestMapping("removeCarousel")
	public void removeCarousel(int id){
		service.removeItem(id);
	}
	
	//查一个
	@ResponseBody
	@RequestMapping("findOneCarousel")
	public Carousel findOneCarousel(int id){
		Carousel carousel = service.findOneItem(id);
		return carousel;
	}
	
	//修改
	@ResponseBody
	@RequestMapping("modifyCarousel")
	public void modifyCarousel(Carousel carousel){
		service.modifyItem(carousel);
	}

	//查所有
	@ResponseBody
	@RequestMapping("findAllCarousel")
	public List<Carousel> findAllCarousel(){
		List<Carousel> list = service.findItems();
		return list;
	}
	
	//分页
	@RequestMapping("queryBy")
	@ResponseBody
	public CarouselDto queryBy(int page, int rows){
		CarouselDto dto = service.queryByPage(page, rows);
		return dto;
	}
}
