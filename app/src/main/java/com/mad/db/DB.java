package com.mad.db;

import com.mad.bean.BaseBean;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
	
	public static final String DB_USER = "user";
	public static final String DB_RECHARGE = "recharge";
	
	private static DB instance;
	private SQLiteDatabase conn;

	private DB(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	public static DB getInstance(Context context) {
		if (DB.instance == null) {
			synchronized (DB.class) {
				if (DB.instance == null) {
					DB.instance = new DB(context, "db.db", null, 1);
				}
			}
		}
		return DB.instance;
	}
	
	public long insert(String tableName, BaseBean bean){
		if (this.conn == null) this.conn = this.getWritableDatabase();
		return this.conn.insert(tableName, null, bean.toContentValues());
	}
	
	public Cursor query(String tableName, String selection, String ...selectionArgs) {
		if (this.conn == null) this.conn = this.getWritableDatabase();
		return this.conn.query(tableName, null, selection, selectionArgs, null, null, null);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		this.conn = arg0;
		this.conn.execSQL("CREATE TABLE user (_id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, pass TEXT)");
		this.conn.execSQL("CREATE TABLE recharge (_id INTEGER PRIMARY KEY AUTOINCREMENT, carId INTEGER, money INTEGER, user TEXT, time INTEGER)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
}
