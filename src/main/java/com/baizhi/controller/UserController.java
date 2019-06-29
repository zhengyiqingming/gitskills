package com.baizhi.controller;

import com.baizhi.dto.UserDto;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.PoiUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("user")
public class UserController {
	//DI注入
	@Resource(name="userService")
	private UserService service;
	
	//注册
	//上传
	@ResponseBody
	@RequestMapping("addUser")
	public void addUser(User user,MultipartFile file,HttpSession session) throws IllegalStateException, IOException{
		String realPath = session.getServletContext().getRealPath("/shangshi");
		File serverFile = new File(realPath+"/"+file.getOriginalFilename());
		file.transferTo(serverFile);
		user.setDate(new Date());
		user.setHeadPic("shangshi/"+file.getOriginalFilename());
		service.addItem(user);
	}
	
	//登陆
	@ResponseBody
	@RequestMapping("login")
	public User login(String username,String password){
		User user = service.findOneUser(username, password);
		return user;
	}
	
	//查询一个
	@ResponseBody
	@RequestMapping("findOneUser")
	public User findOneUser(Integer id){
		User user = service.findOneItem(id);
		return user;
	}
	
	//修改
	@ResponseBody
	@RequestMapping("modifyUser")
	public void modifyUser(User user){
		service.modifyItem(user);
	}
	
	//删除
	@ResponseBody
	@RequestMapping("removeUser")
	public void removeUser(Integer id){
		service.removeItem(id);
	}
	
	//查所有
	@ResponseBody
	@RequestMapping("findAllUser")
	public List<User> findAllUser(){
		List<User> list = service.findItems();
		return list;
	}
	
	//分页
	@RequestMapping("queryBy")
	@ResponseBody
	public UserDto queryBy(int page, int rows){
		UserDto dto = service.queryByPage(page, rows);
		return dto;
	}


	@RequestMapping("/download")
	public void downloadUserInfo(HttpServletResponse response) throws IOException {
		List<User> items = service.findItems();

		//下载之前一定设置响应头
		response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode("用户信息汇总.xls","UTF-8"));

		ServletOutputStream os = response.getOutputStream();

		//生成excel
		PoiUtil.exportExcel("App 注册用户信息","用户信息汇总",items,os);


	}
}
