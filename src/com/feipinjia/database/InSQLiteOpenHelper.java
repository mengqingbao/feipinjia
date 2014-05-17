package com.feipinjia.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InSQLiteOpenHelper extends SQLiteOpenHelper{

	private static final String DB_NAME = "mydata.db";
	private static final int version = 1; 

	public InSQLiteOpenHelper(Context context){
		super(context, DB_NAME, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE [InMessage] ([userId] NVARCHAR(120)  NULL,[content] NVARCHAR(240)  NULL,[Id] INTEGER  PRIMARY KEY NOT NULL,[friendId] NVARCHAR(140)  NULL,[createDate] TIMESTAMP DEFAULT CURRENT_TIME NULL,[type] INTEGER  NULL,[nick] VARCHAR(120)  NULL,[friendNick] VARCHAR(120)  NULL);CREATE TABLE android_metadata (locale TEXT);CREATE TABLE [care] ([id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,[title] vARCHAR(40)  NULL,[draw] vARCHAR(120)  NULL,[good] iNTEGER  NULL,[nornal] iNTEGER  NULL,[bad] iNTEGER  NULL,[createDate] timesTAMP  NULL,[solustion] vARCHAR(220)  NULL);CREATE TABLE [care_comment] ([id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,[careId] iNTEGER  NULL,[userId] vARCHAR(60)  NULL,[nick] vARCHAR(60)  NULL,[comments] vARCHAR(120)  NULL,[grader] INTEGER  NOT NULL,[createDate] timesTAMP  NULL);CREATE TABLE [cure_record] ([id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,[class] vARCHAR(120)  NULL,[sub_class] vARCHAR(120)  NULL,[title] vARCHAR(300)  NULL,[grader] iNTEGER  NULL,[comment] vARCHAR(400)  NULL,[createDate] TIMESTAMP  NULL);CREATE TABLE [doctor] ([id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,[userId] vARCHAR(120)  NULL,[nick] vARCHAR(120)  NULL,[draw] vARCHAR(512)  NULL,[province] vARCHAR(120)  NULL,[city] vARCHAR(120)  NULL,[good] iNTEGER  NULL,[normal] iNTEGER  NULL,[bad] iNTEGER  NULL,[type] inTEGER  NULL,[status] iNTEGER  NULL,[createDate] TIMESTAMP  NULL);CREATE INDEX [IDX_INMESSAGE_] ON [InMessage]([content]  ASC);";
		
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}

}
