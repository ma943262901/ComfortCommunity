package com.community.framework.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences 工具类
 * 
 * @author smile
 * 
 */
public class SharedPrefHelper {
	/**
	 * SharedPreferences的名字
	 */
	private static final String SP_FILE_NAME = "APPLICATION_SP";

	private static SharedPrefHelper sharedPrefHelper = null;
	private static SharedPreferences sharedPreferences;

	public static synchronized SharedPrefHelper getInstance(Context c) {
		if (null == sharedPrefHelper) {
			sharedPrefHelper = new SharedPrefHelper(c);
		}
		return sharedPrefHelper;
	}

	private SharedPrefHelper(Context c) {
		sharedPreferences = c.getSharedPreferences(SP_FILE_NAME,
				Context.MODE_PRIVATE);
	}

	public static SharedPreferences sp(Context c) {
		return c.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
	}

	/**
	 * 应用第一次登陆标识
	 * 
	 */
	public void setFirstLogin(boolean isFirst) {
		sharedPreferences.edit().putBoolean("firstLogin", isFirst).commit();
	}

	public boolean getFirstLogin() {
		return sharedPreferences.getBoolean("firstLogin", true);
	}

	/**
	 * 服务器IP
	 */
	public void setServerAddress(String serverAddress) {
		sharedPreferences.edit().putString("serverAddress", serverAddress);
	}

	public String getServerAddress() {
		return sharedPreferences.getString("serverAddress", "");
	}

	/**
	 * 服务器加密密钥
	 */
	public void setServerRandomKey(String serverRandomKey) {
		sharedPreferences.edit().putString("serverRandomKey", serverRandomKey);
	}

	public String getServerRandomKey() {
		return sharedPreferences.getString("serverRandomKey", "");
	}

	/**
	 * 服务器密钥有效期
	 */
	public void setServerRandomKeyUpdate(String serverRandomKeyUpdate) {
		sharedPreferences.edit().putString("serverRandomKeyUpdate",
				serverRandomKeyUpdate);
	}

	public String getServerRandomKeyUpdate() {
		return sharedPreferences.getString("serverRandomKeyUpdate", "");
	}

	/**
	 * 客户端随机加密密钥
	 */
	public void setRandomKey(String randomKey) {
		sharedPreferences.edit().putString("randomKey", randomKey);
	}

	public String getRandomKey() {
		return sharedPreferences.getString("randomKey", "");
	}
}
