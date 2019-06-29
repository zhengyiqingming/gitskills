package com.baizhi.controller;


import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("menu")
public class MenuController {
	//DI注入
	@Resource(name="menuService")
	private MenuService menuService;
	
	@RequestMapping("findAll")
	@ResponseBody
	//查所有
	public List<Menu> findAll(){
		List<Menu> list = menuService.findItems();
		return list;
	}
	
}
