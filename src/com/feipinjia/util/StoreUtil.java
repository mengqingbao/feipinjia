package com.feipinjia.util;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.feipinjia.database.DBHelper;
import com.feipinjia.database.InSQLiteOpenHelper;
import com.feipinjia.model.InMessage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class StoreUtil {

	private static SQLiteDatabase getDb(Context context) {
		//
		DBHelper isqloh = new DBHelper(context);
	    return isqloh.getReadableDatabase();
	}

	public static List<InMessage> getMessages(String userId, String friendId,
			Context context) {
		List<InMessage> result = new ArrayList<InMessage>();
		SQLiteDatabase db = getDb(context);
		// Cursor c = db.query("InMessage",null,null,null,null,null,null);
		Cursor c = db.rawQuery(
				"select content from InMessage where userId=? and friendId=?",
				new String[] { userId, friendId });
		// String[] columns = new String[]{"content"};
		// Cursor c = db.query("InMessage", columns, "userId='"+userId+"'",null,
		// null, null, "Id");
		if (c.moveToFirst()) {// �ж��α��Ƿ�Ϊ��
			for (int i = 0; i < c.getCount(); i++) {
				InMessage inMessage = new InMessage();
				c.move(i);// �ƶ���ָ����¼
				inMessage.setContent(c.getString(c.getColumnIndex("content")));
				result.add(inMessage);
			}
		}

		return result;
	}

	public static void add(String tname, Map<String, String> map,
			Context context) {
		SQLiteDatabase db = getDb(context);
		ContentValues cv = new ContentValues();
		Set<String> key = map.keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			cv.put(s, map.get(s));
		}
		db.insert(tname, null, cv);
	}


}
