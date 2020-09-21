package com.mad.bean;

import java.util.LinkedList;

import android.content.Context;
import android.database.Cursor;

import com.mad.db.DB;

public class RechargeLogBean extends BaseBean {
	private int carId;
	private int money;
	private String user;
	private long time;
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}


	public static LinkedList<RechargeLogBean> getAll(Context context) {
		LinkedList<RechargeLogBean> beans = new LinkedList<RechargeLogBean>();
		Cursor c = DB.getInstance(context).query(DB.DB_RECHARGE,"","");
		while (c.moveToNext()) {
			beans.add((RechargeLogBean) new RechargeLogBean().fromCursor(c));
		}
		return beans;
	}

}
