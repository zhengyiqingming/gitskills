package com.baizhi.entity;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {
	
	//菜单管理
	private Integer id;
	private String title;
	private String iconCls;
	private Integer parentId;
	private String href;
	private List<Menu> myMenu;
	
	public Menu(Integer id, String title, String iconCls, Integer parentId,
			String href, List<Menu> myMenu) {
		super();
		this.id = id;
		this.title = title;
		this.iconCls = iconCls;
		this.parentId = parentId;
		this.href = href;
		this.myMenu = myMenu;
	}
	public List<Menu> getMyMenu() {
		return myMenu;
	}
	public void setMyMenu(List<Menu> myMenu) {
		this.myMenu = myMenu;
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
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public Menu(Integer id, String title, String iconCls, Integer parentId,
			String href) {
		super();
		this.id = id;
		this.title = title;
		this.iconCls = iconCls;
		this.parentId = parentId;
		this.href = href;
	}
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", title=" + title + ", iconCls=" + iconCls
				+ ", parentId=" + parentId + ", href=" + href + "]";
	}
	
	
}
