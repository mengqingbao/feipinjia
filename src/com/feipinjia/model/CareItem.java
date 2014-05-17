package com.feipinjia.model;

import java.util.Date;

public class CareItem {
	private int id;
	private int carId;
	private int parantId;
	private String title;
	private String draw;
	private String condition0;
	private String condition1;
	private String condition2;
	private String condition3;
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
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public int getParantId() {
		return parantId;
	}
	public void setParantId(int parantId) {
		this.parantId = parantId;
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
	public String getCondition0() {
		return condition0;
	}
	public void setCondition0(String condition0) {
		this.condition0 = condition0;
	}
	public String getCondition1() {
		return condition1;
	}
	public void setCondition1(String condition1) {
		this.condition1 = condition1;
	}
	public String getCondition2() {
		return condition2;
	}
	public void setCondition2(String condition2) {
		this.condition2 = condition2;
	}
	public String getCondition3() {
		return condition3;
	}
	public void setCondition3(String condition3) {
		this.condition3 = condition3;
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
