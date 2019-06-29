package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;


@Controller
@RequestMapping("chapter")
public class ChapterController {
	//DI注入
	@Resource(name="chapterService")
	private ChapterService service;
	
	//添加章节
	//添加
	@ResponseBody
	@RequestMapping("addChapter")
	public void addChapter(Chapter chapter, MultipartFile file, HttpSession session) throws IllegalStateException, IOException{
		String realPath = session.getServletContext().getRealPath("/shangshi");
		File serverFile = new File(realPath+"/"+file.getOriginalFilename());
		file.transferTo(serverFile);
		chapter.setUploadDate(new Date());
		chapter.setDownloadPath("../shangshi/"+file.getOriginalFilename());
		service.addItem(chapter);
	}

	//下载
	@ResponseBody
	@RequestMapping("download")
	public void download(String filename, HttpSession session,HttpServletResponse response) throws IOException{

		/*byte[] bs = FileUtils.readFileToByteArray(new File(realPath+"/"+filename));
		// 设置响应头信息
		response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(filename, "utf-8"));	
		ServletOutputStream out = response.getOutputStream();
		out.write(bs);
		out.close();*/
	}
}
