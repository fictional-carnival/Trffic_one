package com.mad.bean;

import java.lang.reflect.Field;
import java.util.Iterator;

import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;

public class BaseBean {
	public BaseBean fromJson(JSONObject jsonObj) {
		Iterator<String> keys = jsonObj.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			try {
				Field f = this.getClass().getDeclaredField(
						key.replace('.', '_'));
				f.setAccessible(true);
				if (f.getType().equals(int.class)) {
					f.setInt(this, jsonObj.getInt(key));
				} else if (f.getType().equals(long.class)) {
					f.setLong(this, jsonObj.getLong(key));
				} else if (f.getType().equals(String.class)) {
					f.set(this, jsonObj.getString(key));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this;
	}

	public BaseBean fromCursor(Cursor c) {
		for (int i = 0; i < c.getColumnCount(); i++) {
			String key = c.getColumnName(i);
			try {
				Field f = this.getClass().getDeclaredField(key);
				f.setAccessible(true);
				if (f.getType().equals(int.class)) {
					f.setInt(this, c.getInt(i));
				} else if (f.getType().equals(long.class)) {
					f.setLong(this, c.getLong(i));
				} else if (f.getType().equals(String.class)) {
					f.set(this, c.getString(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this;
	}

	public ContentValues toContentValues() {
		ContentValues cvs = new ContentValues();
		for (Field f : this.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if (f.getType().equals(int.class)) {
					cvs.put(f.getName(), f.getInt(this));
				} else if (f.getType().equals(long.class)) {
					cvs.put(f.getName(), f.getLong(this));
				} else if (f.getType().equals(String.class)) {
					cvs.put(f.getName(), (String) f.get(this));
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cvs;
	}
}
