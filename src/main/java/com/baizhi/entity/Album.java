package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Album implements Serializable {
	//专辑表
	/*`id` int(11) NOT NULL AUTO_INCREMENT,
	  `title` varchar(255) DEFAULT NULL,
	  `coverImg` varchar(255) DEFAULT NULL,
	  `count` int(11) DEFAULT NULL,
	  `score` int(11) DEFAULT NULL,
	  `author` varchar(255) DEFAULT NULL,
	  `broadCast` varchar(255) DEFAULT NULL,
	  `brief` varchar(255) DEFAULT NULL,
	  `publishDate` date DEFAULT NULL,*/
	
	private Integer id;
	private String title;  //标题
	private String coverImg; //封面图片
	private Integer count; //集数
	private Integer score;//评分
	private String author;//作者
	private String broadCast;//播音员
	private String brief;//时长
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date publishDate;//发布日期
	private List<Chapter> children;  //页面做树状图  必须写的属性
	
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
	public String getCoverImg() {
		return coverImg;
	}
	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBroadCast() {
		return broadCast;
	}
	public void setBroadCast(String broadCast) {
		this.broadCast = broadCast;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public List<Chapter> getChildren() {
		return children;
	}
	public void setChildren(List<Chapter> children) {
		this.children = children;
	}
	public Album() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Album [id=" + id + ", title=" + title + ", coverImg="
				+ coverImg + ", count=" + count + ", score=" + score
				+ ", author=" + author + ", broadCast=" + broadCast
				+ ", brief=" + brief + ", publishDate=" + publishDate
				+ ", children=" + children + "]";
	}
	public Album(Integer id, String title, String coverImg, Integer count,
			Integer score, String author, String broadCast, String brief,
			Date publishDate) {
		super();
		this.id = id;
		this.title = title;
		this.coverImg = coverImg;
		this.count = count;
		this.score = score;
		this.author = author;
		this.broadCast = broadCast;
		this.brief = brief;
		this.publishDate = publishDate;
	}
	
	
}
