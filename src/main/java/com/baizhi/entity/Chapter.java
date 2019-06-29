package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Chapter implements Serializable {
	
	//章节
	private Integer id;
	private String title; //标题
	private String size; //章节大小
	private String duration; //章节时长
	private String downloadPath;//下载路径
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date uploadDate; //下载日期
	private Integer albumId; //专辑id
	
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
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDownloadPath() {
		return downloadPath;
	}
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public Integer getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}
	public Chapter(Integer id, String title, String size, String duration,
			String downloadPath, Date uploadDate, Integer albumId) {
		super();
		this.id = id;
		this.title = title;
		this.size = size;
		this.duration = duration;
		this.downloadPath = downloadPath;
		this.uploadDate = uploadDate;
		this.albumId = albumId;
	}
	public Chapter() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Chapter [id=" + id + ", title=" + title + ", size=" + size
				+ ", duration=" + duration + ", downloadPath=" + downloadPath
				+ ", uploadDate=" + uploadDate + ", albumId=" + albumId + "]";
	}
	
	
}
