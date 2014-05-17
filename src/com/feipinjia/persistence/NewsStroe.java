package com.feipinjia.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.feipinjia.model.News;

public class NewsStroe extends BaseDao {
	
	private static NewsStroe objself;
	public NewsStroe(){
	}
	
	//自举
	public static NewsStroe getInstance(){
		if(objself==null){
			objself=new NewsStroe();
		}
		return objself;
	}
	
	public List<News> queryPageList(int start ,int limit,Context context,int type) {
		List<News> result = new ArrayList<News>();
		db = getDb(true,context);
		Cursor c = db.rawQuery("select * from news where _type="+type+" order by _order desc limit "+limit+" offset "+start,null);
		int count=c.getCount();
		if (c.moveToFirst()) {
			for (int i = 0; i < count; i++) {
				News config = new News();
				config.setId(c.getInt(c.getColumnIndex("id")));
				config.setTitle(getString(c,"title"));
				config.setDesc(getString(c,"desc"));
				config.setCommits(c.getInt(c.getColumnIndex("commits")));
				config.setType(c.getInt(c.getColumnIndex("_type")));
				config.setOrder(c.getInt(c.getColumnIndex("_order")));
				result.add(config);
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
