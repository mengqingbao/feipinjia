package com.feipinjia.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.feipinjia.model.Doctor;
import com.feipinjia.model.InMessage;
import com.feipinjia.model.InUser;

public class DoctorStore extends BaseDao  {
	private static DoctorStore objself;
	private Context context;
	public DoctorStore(){
	}
	
	//自举
	public static DoctorStore getInstance(Context context){
		if(objself==null){
			objself=new DoctorStore();
		}
		context=context;
		return objself;
	}
	
	public List<Doctor> queryPage(String type,int start ,int limit,Context context) {
		List<Doctor> result = new ArrayList<Doctor>();
		db = getDb(true,context);
		Cursor c = db.rawQuery(
				"select * from doctor where type='"+type+"' order by id asc limit "+limit+" offset "+start,
				new String[] {});
		int count=c.getCount();
		if (c.moveToFirst()) {
			for (int i = 0; i < count; i++) {
				Doctor obj = new Doctor();
				obj.setUserId(c.getString(c.getColumnIndex("userId")));
				obj.setNick(getString(c,"nick"));
				obj.setDesc(getString(c,"draw"));
				obj.setGood(c.getInt(c.getColumnIndex("good")));
				obj.setNormal(c.getInt(c.getColumnIndex("normal")));
				obj.setBad(c.getInt(c.getColumnIndex("bad")));
				obj.setZgood(c.getInt(c.getColumnIndex("z_good")));
				obj.setZnormal(c.getInt(c.getColumnIndex("z_normal")));
				obj.setZbad(c.getInt(c.getColumnIndex("z_bad")));
				obj.setLevel(c.getInt(c.getColumnIndex("level")));
				System.out.println("+++++");
				switch (c.getInt(c.getColumnIndex("level"))) {
				case 1:
					obj.setLevelDes("民");
					break;
				case 2:
					obj.setLevelDes("证");
					break;
				case 3:
					obj.setLevelDes("民  证");
					break;
				default:
					break;
				}
				obj.setPosition(getString(c,"position"));
				obj.setAvatar(getString(c,"avatar"));
				obj.setPosition(getString(c,"info"));
				obj.setAvatar(getString(c,"tec_from"));
				result.add(obj);
				c.moveToNext();
			}
		}

		return result;
	}
	
	public Doctor getById(String userId,Context context){
		db = getDb(true,context);
		Cursor c = db.rawQuery("select * from doctor where userId=?",new String[] {userId});
		int count=c.getCount();
		if (c.moveToFirst()) {
			if (count>0) {
				Doctor obj = new Doctor();
				obj.setUserId(c.getString(c.getColumnIndex("userId")));
				obj.setNick(getString(c,"nick"));
				obj.setDesc(getString(c,"draw"));
				obj.setProcince(getString(c,"province"));
				obj.setCity(getString(c,"city"));
				obj.setGood(c.getInt(c.getColumnIndex("good")));
				obj.setNormal(c.getInt(c.getColumnIndex("normal")));
				obj.setBad(c.getInt(c.getColumnIndex("bad")));
				obj.setZgood(c.getInt(c.getColumnIndex("z_good")));
				obj.setZnormal(c.getInt(c.getColumnIndex("z_normal")));
				obj.setZbad(c.getInt(c.getColumnIndex("z_bad")));
				obj.setLevel(c.getInt(c.getColumnIndex("level")));
				switch (c.getInt(c.getColumnIndex("level"))) {
				case 1:
					obj.setLevelDes("民");
					break;
				case 2:
					obj.setLevelDes("证");
					break;
				case 3:
					obj.setLevelDes("民  证");
					break;
				default:
					break;
				}
				obj.setPosition(getString(c,"position"));
				obj.setAvatar(getString(c,"avatar"));
				obj.setInfo(getString(c,"info"));
				obj.setTecFrom(getString(c,"tec_from"));
				return obj;
			}
	}
		return null;
}
}