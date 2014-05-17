package com.feipinjia.model;

import java.util.Date;

public class CareResult {
	private int id;
	private String name;
	private String content;
	private int careId;
	private int commentId;
	private int conditionId;
	private String contentDetial;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCareId() {
		return careId;
	}
	public void setCareId(int careId) {
		this.careId = careId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getConditionId() {
		return conditionId;
	}
	public void setConditionId(int conditionId) {
		this.conditionId = conditionId;
	}
	public String getContentDetial() {
		return contentDetial;
	}
	public void setContentDetial(String contentDetial) {
		this.contentDetial = contentDetial;
	}
	
}
