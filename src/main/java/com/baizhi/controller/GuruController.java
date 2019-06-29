package com.baizhi.controller;


import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/guru")
public class GuruController {
	@Resource(name="guruService")
	private GuruService guruService;
	
	@RequestMapping("/queryOneGuru")
	@ResponseBody
	public Guru queryOneGuru(int id){
		return guruService.findOneItem(id);
	}
	@RequestMapping("/queryAllGuru")
	@ResponseBody
	public List<Guru> queryAllGuru(){
		return guruService.findItems();
	}
	@RequestMapping("/addGuru")
	@ResponseBody
	public void addGuru(Guru guru, MultipartFile file, HttpSession session) throws IllegalStateException, IOException {
		String realPath = session.getServletContext().getRealPath("/shangshi");
		File serverFile = new File(realPath+"/"+file.getOriginalFilename());
		file.transferTo(serverFile);
		
		guru.setHeadPic("/shangshi/"+file.getOriginalFilename());
		System.out.println(guru);
		guruService.addItem(guru);
	}
	
	
	
	

}
