package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Carousel implements Serializable {
	
	 //轮播图
	
	private Integer id;
	private String title;  //标题
	private String imgPath; //图片路径
	private String intro; //描述
	private String status; //状态
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date date; //图片创建日期
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Carousel(Integer id, String title, String imgPath, String intro,
			String status, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.imgPath = imgPath;
		this.intro = intro;
		this.status = status;
		this.date = date;
	}
	public Carousel() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Carousel [id=" + id + ", title=" + title + ", imgPath="
				+ imgPath + ", intro=" + intro + ", status=" + status
				+ ", date=" + date + "]";
	}
	
	
}
