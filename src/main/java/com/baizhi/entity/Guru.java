package com.baizhi.entity;

import java.io.Serializable;
import java.util.List;

public class Guru implements Serializable {
	private Integer id;
	
	private String title;
	private String headPic;
	private String gender;
	private List<Article> children;
	public Guru() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Guru(Integer id, String title, String headPic, String gender,
			List<Article> children) {
		super();
		this.id = id;
		this.title = title;
		this.headPic = headPic;
		this.gender = gender;
		this.children = children;
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
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<Article> getChildren() {
		return children;
	}
	public void setChildren(List<Article> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "Guru [id=" + id + ", title=" + title + ", headPic=" + headPic
				+ ", gender=" + gender + ", children=" + children + "]";
	}
	
	
}
