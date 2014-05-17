package com.feipinjia.model;

import java.util.Date;

public class Doctor {

	private int id;
	private String userId;
	private String avatar;
	private String nick;
	private String desc;
	private String procince;
	private String city;
	private Date lastLogin;
	private int good;
	private int normal;
	private int bad;
	private int zgood;
	private int znormal;
	private int zbad;
	private int level;
	private int type;
	private String position;
	private String levelDes;
	private String info;
	private String tecFrom;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getNormal() {
		return normal;
	}
	public void setNormal(int normal) {
		this.normal = normal;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	public int getZgood() {
		return zgood;
	}
	public void setZgood(int zgood) {
		this.zgood = zgood;
	}
	public int getZnormal() {
		return znormal;
	}
	public void setZnormal(int znormal) {
		this.znormal = znormal;
	}
	public int getZbad() {
		return zbad;
	}
	public void setZbad(int zbad) {
		this.zbad = zbad;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLevelDes() {
		return levelDes;
	}
	public void setLevelDes(String levelDes) {
		this.levelDes = levelDes;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getTecFrom() {
		return tecFrom;
	}
	public void setTecFrom(String tecFrom) {
		this.tecFrom = tecFrom;
	}
	public String getProcince() {
		return procince;
	}
	public void setProcince(String procince) {
		this.procince = procince;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
