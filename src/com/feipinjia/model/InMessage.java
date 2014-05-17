package com.feipinjia.model;

import java.util.Date;

public class InMessage {

	private InUser inUser;
	private String content;
	private Date createDate;
	private boolean type;  //type:true message from friends ,false:msg from yourself
	private String friendNick;
	private String friendId;
	private int commentStatus;
	private int action;
	public InUser getInUser() {
		return inUser;
	}
	public void setInUser(InUser inUser) {
		this.inUser = inUser;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public String getFriendNick() {
		return friendNick;
	}
	public void setFriendNick(String friendNick) {
		this.friendNick = friendNick;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public int getCommentStatus() {
		return commentStatus;
	}
	public void setCommentStatus(int commentStatus) {
		this.commentStatus = commentStatus;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	
}
