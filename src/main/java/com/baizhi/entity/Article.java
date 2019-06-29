package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
	private Integer id;
	private String title;
	private String content;
	private String insertimgPath;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date publishDate;
	private String guruId;
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Article(Integer id, String title, String content,
			String insertimgPath, Date publishDate, String guruId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.insertimgPath = insertimgPath;
		this.publishDate = publishDate;
		this.guruId = guruId;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getInsertimgPath() {
		return insertimgPath;
	}
	public void setInsertimgPath(String insertimgPath) {
		this.insertimgPath = insertimgPath;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getGuruId() {
		return guruId;
	}
	public void setGuruId(String guruId) {
		this.guruId = guruId;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content="
				+ content + ", insertimgPath=" + insertimgPath
				+ ", publishDate=" + publishDate + ", guruId=" + guruId + "]";
	}
	
	
	
}
