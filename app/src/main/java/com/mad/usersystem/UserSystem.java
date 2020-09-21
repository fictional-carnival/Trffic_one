package com.mad.usersystem;

import com.mad.bean.UserBean;

public class UserSystem {
	private static UserSystem instance;
	
	public static UserSystem getInstance() {
		if (UserSystem.instance == null) {
			synchronized (UserSystem.class) {
				if (UserSystem.instance == null) {
					UserSystem.instance = new UserSystem();
				}
			}
		}
		return UserSystem.instance;
	}
	private UserBean user;
	
	public UserBean getUser() {
		return this.user;
	}
	
	public void setUser(UserBean user) {
		this.user = user;
	}
	
	public static boolean checkUserName(String username) {
		for (char c : username.toLowerCase().toCharArray()) {
			if (!(('a' <= c && c <='z') || ('0' <= c && c <= '9'))) return false;
		}
		return 4 <= username.length() && username.length() <= 10;
	}
	
	public static boolean checkPassword(String password) {
		for (char c : password.toLowerCase().toCharArray()) {
			if (!(('a' <= c && c <='z') || ('0' <= c && c <= '9'))) return false;
		}
		return 4 <= password.length() && password.length() <= 10;
	}
}
