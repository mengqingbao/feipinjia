package com.feipinjia.persistence;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.feipinjia.database.DBHelper;

public class BaseDao {
	protected SQLiteDatabase db;
	
	public String getString(Cursor c,String key){
		String result;
		try {
			byte[] temp=c.getBlob(c.getColumnIndex(key));
			if(temp!=null){
			result = new String(c.getBlob(c.getColumnIndex(key)),"gb2312");
			}else{
				return "";
			}
		} catch (UnsupportedEncodingException e) {
			return "error";
		}
		return result;
	}
	
	protected SQLiteDatabase getDb(boolean isRead,Context context) {
		
		DBHelper dbHelper = new DBHelper(context);
		try {
			dbHelper.createDataBase();
		} catch (IOException e) {
		}
		if(isRead){
			return dbHelper.getReadableDatabase();
		}else{
			return dbHelper.getWritableDatabase();
		}
		//return SQLiteDatabase.openOrCreateDatabase("data/data/com.feipinjia.activity/databases/mydata.db", null);
	}
}
