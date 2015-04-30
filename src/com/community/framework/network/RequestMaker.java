package com.community.framework.network;


public class RequestMaker {
	// private String SERVER_URL = "http://IP/smilemoble/appentry.jsp";
//	private static final String INFO_STRING = "info";
//	private static final String AUTH_STRING = "auth";

	/**
	 * 获取单例
	 * 
	 * @return
	 */
	public static RequestMaker getInstance() {
		if (requestMaker == null) {
			requestMaker = new RequestMaker();
			return requestMaker;
		} else {
			return requestMaker;
		}
	}

	private static RequestMaker requestMaker = null;

}
