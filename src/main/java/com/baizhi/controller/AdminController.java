package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class AdminController {
	//DI注入
	@Resource(name="adminService")
	private AdminService adminService;
	
	//登录
	@RequestMapping("login")
	public String login(String name,String password,HttpSession session,String enCode){
		
		String code = (String)session.getAttribute("code");
		if(enCode.equals(code)){
			Admin admin = adminService.findOneAdmin(name, password);
			if(admin!=null){
				session.setAttribute("login", admin);
				return "redirect:/main/main.jsp";
			}else{
				return "redirect:/login.jsp";
			}
		}
			return "redirect:/login.jsp";
	}
	
/*	//生成动态验证码图片
	@RequestMapping("createImg")
	public void createImg(HttpServletResponse response,HttpSession session) throws Exception{
		//调用img工具类方法  生成随机验证码
		String code = SecurityCode.getSecurityCode();
		//获取值栈 把数据存放在session中
		session.setAttribute("code", code);
		//调用img工具类方法 根据验证码  生成缓存图片
		BufferedImage bi = SecurityImage.createImage(code);
		//获取响应response对象
		ServletOutputStream out = response.getOutputStream();
		//使用工具类方法     把生成的缓冲图片 响应到 client
		ImageIO.write(bi, "jpeg", out);
	}
	*/
	//安全退出
	@RequestMapping("out")
	public String out(HttpSession session){
		session.removeAttribute("login");
		return "redirect:/login.jsp";
	}
	
	//查一
	@ResponseBody
	@RequestMapping("findOne")
	public Admin findOne(Integer id){
		Admin admin = adminService.findOneItem(id);
		return admin;
	}
	
	//修改密码
	@RequestMapping("modifyPassword")
	@ResponseBody
	public void modifyPassword(Admin admin){
		adminService.modifyItem(admin);
	}
	
}
