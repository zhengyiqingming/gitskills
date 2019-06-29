package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	/* `id` int(11) NOT NULL AUTO_INCREMENT,
	  `phoneNum` varchar(255) DEFAULT NULL,
	  `username` varchar(255) DEFAULT NULL,
	  `password` varchar(255) DEFAULT NULL,
	  `salt` varchar(255) DEFAULT NULL,
	  `dharmName` varchar(255) DEFAULT NULL,
	  `gender` varchar(255) DEFAULT NULL,
	  `headPic` varchar(255) DEFAULT NULL,
	  `province` varchar(255) DEFAULT NULL,
	  `city` varchar(255) DEFAULT NULL,
	  `sign` varchar(255) DEFAULT NULL,
	  `status` varchar(255) DEFAULT NULL,
	  `date` date DEFAULT NULL,*/

	private Integer id;
	private String phoneNum;
	private String username;
	private String password;
	private String salt;
	private String dharmName;
	private String gender;
	private String headPic;
	private String province;
	private String city;
	private String sign;
	private String status;
	@DateTimeFormat(pattern="yyyy-MM-dd")//用来修改接收的日期格式
	@JSONField(format="yyyy-MM-dd")//用来修改响应json时该属性在json数据中日期格式
	private Date date;




	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getDharmName() {
		return dharmName;
	}
	public void setDharmName(String dharmName) {
		this.dharmName = dharmName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
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
	@Override
	public String toString() {
		return "User [id=" + id + ", phoneNum=" + phoneNum + ", username="
				+ username + ", password=" + password + ", salt=" + salt
				+ ", dharmName=" + dharmName + ", gender=" + gender
				+ ", headPic=" + headPic + ", province=" + province + ", city="
				+ city + ", sign=" + sign + ", status=" + status + ", date="
				+ date + "]";
	}
	public User(Integer id, String phoneNum, String username, String password,
			String salt, String dharmName, String gender, String headPic,
			String province, String city, String sign, String status, Date date) {
		super();
		this.id = id;
		this.phoneNum = phoneNum;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.dharmName = dharmName;
		this.gender = gender;
		this.headPic = headPic;
		this.province = province;
		this.city = city;
		this.sign = sign;
		this.status = status;
		this.date = date;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
