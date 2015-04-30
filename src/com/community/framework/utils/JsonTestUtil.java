package com.community.framework.utils;

import android.content.Context;

/**
 * 接口数据测试
 * 
 * @author smile
 * 
 */
public enum JsonTestUtil {

	GetHomePageList("GetHomePageList"),

	GetVideoPageList("GetVideoPageList"),

	SearchVideo("SearchVideo"),

	GetGamePageList("GetGamePageList"),

	SearchGame("SearchGame");

	private String requestType;

	private JsonTestUtil(String requestType) {
		// TODO Auto-generated constructor stub
		this.requestType = requestType;
	}

	public String getJson(Context context) {
		return FileUtils.getFileFromAssets(context, "json" + "/" + requestType+".json");
	}
}
