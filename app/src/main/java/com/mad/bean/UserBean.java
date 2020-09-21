package com.mad.bean;

import java.util.LinkedList;

import android.content.Context;
import android.database.Cursor;

import com.mad.db.DB;

public class UserBean extends BaseBean {
	private String user;
	private String pass;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public static LinkedList<UserBean> getAll(Context context) {
		LinkedList<UserBean> list = new LinkedList<UserBean>();
		Cursor c = DB.getInstance(context).query(DB.DB_USER, "", new String[]{});
		while (c.moveToNext()) {
			list.add((UserBean) new UserBean().fromCursor(c));
		}
		return list;
	}
}
