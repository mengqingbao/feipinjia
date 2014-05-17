package com.feipinjia.persistence;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.feipinjia.model.CareResult;

public class CareResultStroe extends BaseDao {
	
	private static CareResultStroe objself;
	public CareResultStroe(){
	}
	
	//自举
	public static CareResultStroe getInstance(){
		if(objself==null){
			objself=new CareResultStroe();
		}
		return objself;
	}
	
	public List<CareResult> queryPageList(int start ,int limit,Context context,int id) {
		List<CareResult> result = new ArrayList<CareResult>();
		db = getDb(true,context);
		Cursor c = db.rawQuery("select * from care_result where CARE_ID="+id+" limit "+limit+" offset "+start,null);
		int count=c.getCount();
		if (c.moveToFirst()) {
			for (int i = 0; i < count; i++) {
				CareResult careResult = new CareResult();
				careResult.setId(c.getInt(c.getColumnIndex("ID")));
				careResult.setName(getString(c,"NAME_"));
				careResult.setContent(getString(c,"CONTENT_"));
				careResult.setCareId(c.getInt(c.getColumnIndex("CARE_ID")));
				careResult.setConditionId(c.getInt(c.getColumnIndex("CONDITION_ID")));
				careResult.setCommentId(c.getInt(c.getColumnIndex("COMMENT_ID")));
				careResult.setContentDetial(getString(c,"CONTENT_DETAIL"));
				//condition.setParentId(c.getInt(c.getColumnIndex("_order")));
				//condition.setOrder(c.getInt(c.getColumnIndex("ORDER_")));
				result.add(careResult);
				c.moveToNext();
			}
		}

		return result;
	}

	public  void saveOrUpdate(CareResult condition,Context context) {
		db = getDb(false,context);
		ContentValues cv = new ContentValues();
		
		db.insert("InMessage", null, cv);
		db.close();
	}
	
	public  void close(){
		if(db!=null){
			db.close();
		}
	}
}
