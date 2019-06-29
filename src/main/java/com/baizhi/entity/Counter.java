package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

//用户功课计数器表
public class Counter implements Serializable {
	
	/*`id` int(11) NOT NULL AUTO_INCREMENT,
	  `title` varchar(255) DEFAULT NULL,
	  `count` int(11) DEFAULT NULL,
	  `userId` int(11) DEFAULT NULL,
	  `courseId` int(11) DEFAULT NULL,
	  `date` date DEFAULT NULL,*/
	
	private Integer id;
	private String title;
	private Integer count;
	private Integer userId;
	private Integer courseId;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date date;
	
	//关系属性
	private List<User> users;
	private List<Course> courses;
	
	
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public Counter(Integer id, String title, Integer count, Integer userId,
			Integer courseId, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.count = count;
		this.userId = userId;
		this.courseId = courseId;
		this.date = date;
	}
	@Override
	public String toString() {
		return "Counter [id=" + id + ", title=" + title + ", count=" + count
				+ ", userId=" + userId + ", courseId=" + courseId + ", date="
				+ date + ", users=" + users + ", courses=" + courses + "]";
	}
	public Counter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
