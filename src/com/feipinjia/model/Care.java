package com.feipinjia.model;

import java.util.Date;

public class Care {
	private int id;
	private String title;
	private String draw;
	private int good;
	private int nornal;
	private int bad;
	private Date createDate;
	private String solustion;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDraw() {
		return draw;
	}
	public void setDraw(String draw) {
		this.draw = draw;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getNornal() {
		return nornal;
	}
	public void setNornal(int nornal) {
		this.nornal = nornal;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getSolustion() {
		return solustion;
	}
	public void setSolustion(String solustion) {
		this.solustion = solustion;
	}
	

}
