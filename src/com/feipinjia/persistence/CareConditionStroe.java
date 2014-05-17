package com.feipinjia.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.feipinjia.model.CareCondition;
import com.feipinjia.model.Config;

public class CareConditionStroe extends BaseDao {
	
	private static CareConditionStroe objself;
	public CareConditionStroe(){
	}
	
	//自举
	public static CareConditionStroe getInstance(){
		if(objself==null){
			objself=new CareConditionStroe();
		}
		return objself;
	}
	
	public List<CareCondition> queryPageList(int start ,int limit,Context context,int id) {
		List<CareCondition> result = new ArrayList<CareCondition>();
		db = getDb(true,context);
		Cursor c = db.rawQuery("select * from care_condition where CARE_ID="+id+" limit "+limit+" offset "+start,null);
		int count=c.getCount();
		if (c.moveToFirst()) {
			for (int i = 0; i < count; i++) {
				CareCondition condition = new CareCondition();
				condition.setId(c.getInt(c.getColumnIndex("ID")));
				condition.setName(getString(c,"NAME_"));
				System.out.println(getString(c,"NAME_"));
				condition.setValue(getString(c,"VALUE_"));
				condition.setCareId(c.getInt(c.getColumnIndex("CARE_ID")));
				//condition.setParentId(c.getInt(c.getColumnIndex("_order")));
				condition.setCreateDate(new Date(c.getLong(c.getColumnIndex("CREATE_TIME"))));
				//condition.setOrder(c.getInt(c.getColumnIndex("ORDER_")));
				result.add(condition);
				c.moveToNext();
			}
		}

		return result;
	}

	public  void saveOrUpdate(CareCondition condition,Context context) {
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
