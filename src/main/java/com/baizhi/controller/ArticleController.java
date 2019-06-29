package com.baizhi.controller;


import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FileUtils;
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

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Resource(name="articleService")
	private ArticleService articleService;
	
	@RequestMapping("/addArticle")
	@ResponseBody
	public void addArticle(Article article, MultipartFile file, HttpSession session) throws IllegalStateException, IOException {
		//找到shangshi文件夹的真实位置
		String realPath = session.getServletContext().getRealPath("/shangshi");
		//按文件名创建新文件
		File serverFile = new File(realPath+"/"+file.getOriginalFilename());
		//复制文件过去
		file.transferTo(serverFile);
		//路径入库
		article.setContent("/shangshi/"+file.getOriginalFilename());
		article.setTitle(file.getOriginalFilename());
		article.setPublishDate(new Date());
		//把文件转成字符串 
		//String readFileToString = FileUtils.readFileToString(serverFile, "utf-8");
		//存入内容字段中 便于页面显示
		//article.setContent(readFileToString);
		
		articleService.addItem(article);
	}
	
	@RequestMapping("/downloadArticle")
	@ResponseBody
	public void downloadArticle(String title, HttpSession session, HttpServletResponse response) throws IOException{
		//获取要下载的文件
		String realPath = session.getServletContext().getRealPath("/shangshi");
		byte[] bs = FileUtils.readFileToByteArray(new File(realPath+"/"+title));
		//响应在页面播放
		response.setContentType("audio/mpeg");
		//响应头信息 文件在浏览器下载 和文件名中文乱码的编码格式
		response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(title, "utf-8"));
		//获取输出流输出内容
		ServletOutputStream out = response.getOutputStream();
		out.write(bs);
		if(out!=null) out.flush();
		if(out!=null) out.close();
			
		}
		
}
