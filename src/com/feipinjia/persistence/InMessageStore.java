package com.feipinjia.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.feipinjia.model.InMessage;
import com.feipinjia.model.InUser;

public class InMessageStore extends BaseDao {

	private static InMessageStore ims;
	public InMessageStore(){
	}
	
	//自举
	public static InMessageStore getInstance(){
		if(ims==null){
			ims=new InMessageStore();
		}
		return ims;
	}
	
	public List<InMessage> getMessages(String userId, String friendId,int start ,int limit,Context context) {
		List<InMessage> result = new ArrayList<InMessage>();
		db = getDb(true,context);
		Cursor c = db.rawQuery(
				"select * from InMessage where userId=? and friendId=? order by id asc limit "+limit+" offset "+start,
				new String[] { userId, friendId });
		int count=c.getCount();
		if (c.moveToFirst()) {
			for (int i = 0; i < count; i++) {
				InMessage inMessage = new InMessage();
				inMessage.setContent(getString(c,"content"));
				inMessage.setType(c.getInt(c.getColumnIndex("type"))==1);
				inMessage.setCreateDate(new Date(c.getLong(c.getColumnIndex("createDate"))));
				inMessage.setCommentStatus(c.getInt(c.getColumnIndex("commentStatus")));
				inMessage.setAction(c.getInt(c.getColumnIndex("action")));
				InUser user=new InUser();
				user.setNick(getString(c,"nick"));
				user.setUserId(c.getString(c.getColumnIndex("userId")));
				inMessage.setFriendNick(getString(c,"friendNick"));
				inMessage.setFriendId(c.getString(c.getColumnIndex("friendId")));
				result.add(inMessage);
				c.moveToNext();
			}
		}

		return result;
	}

	public  void saveOrUpdate(String userId, String friendId,String friendNick, String content,boolean type,String nick,Context context) {
		db = getDb(false,context);
		ContentValues cv = new ContentValues();
		cv.put("content", content);
		cv.put("userId", userId);
		cv.put("friendId", friendId);
		if(type){
			cv.put("type", 1);
		}else{
			cv.put("type", 0);
		}
		cv.put("createDate",new Date().getTime());
		cv.put("nick", nick);
		cv.put("friendNick", friendNick);
		db.insert("InMessage", null, cv);
		db.close();
	}
	
	public  void close(){
		if(db!=null){
			db.close();
		}
	}

	public  List<InMessage> getUserMessage(Context context) {
		List<InMessage> result = new ArrayList<InMessage>();
		db = getDb(true,context);
		Cursor c = db.rawQuery(
				"select * from InMessage group by friendId order by createDate asc", null);
		int count=c.getCount();
		if (c.moveToFirst()) {
			for (int i = 0; i < count; i++) {
				InMessage inMessage = new InMessage();
				inMessage.setContent(getString(c,"content"));
				inMessage.setType(c.getInt(c.getColumnIndex("type"))==1);
				inMessage.setCreateDate(new Date(c.getLong(c.getColumnIndex("createDate"))));
				inMessage.setCommentStatus(c.getInt(c.getColumnIndex("commentStatus")));
				inMessage.setAction(c.getInt(c.getColumnIndex("action")));
				InUser user=new InUser();
				user.setNick(getString(c,"nick"));
				user.setUserId(c.getString(c.getColumnIndex("userId")));
				inMessage.setFriendNick(getString(c,"friendNick"));
				inMessage.setFriendId(c.getString(c.getColumnIndex("friendId")));
				result.add(inMessage);
				c.moveToNext();
			}
		}

		return result;
	}
}
