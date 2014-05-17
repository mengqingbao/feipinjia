package com.feipinjia.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.feipinjia.model.Care;
import com.feipinjia.model.InMessage;
import com.feipinjia.model.InUser;

public class CareStore extends BaseDao {
	

	private static CareStore objself;
	public CareStore(){
	}
	
	//自举
	public static CareStore getInstance(){
		if(objself==null){
			objself=new CareStore();
		}
		return objself;
	}
	public List<Care> queryPageList(int start ,int limit,Context context) {
		List<Care> result = new ArrayList<Care>();
		db = getDb(true,context);
		Cursor c = db.rawQuery("select * from Care order by id asc limit "+limit+" offset "+start,null);
		int count=c.getCount();
		if (c.moveToFirst()) {
			for (int i = 0; i < count; i++) {
				Care care = new Care();
				care.setId(c.getInt(c.getColumnIndex("id")));
				care.setTitle(getString(c,"title"));
				care.setDraw(getString(c,"draw"));
				care.setSolustion(getString(c,"solustion"));
				care.setCreateDate(new Date(c.getLong(c.getColumnIndex("createDate"))));
				care.setGood(c.getInt(c.getColumnIndex("good")));
				care.setNornal(c.getInt(c.getColumnIndex("nornal")));
				care.setBad(c.getInt(c.getColumnIndex("bad")));
				result.add(care);
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
}
