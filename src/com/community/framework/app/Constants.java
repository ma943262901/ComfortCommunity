package com.community.framework.app;


public class Constants {

	/**
	 * 应用服务IP分发服务器地址
	 */
	public static final String DISTRIBUTE_SERVER_URL = "http://vas.smiletv.com.cn/smilemoble/ChangeServer";

	public static final String SHARE_HTML="";
	/**
	 * 新浪微博分享
	 */
	public static final String SINAWEIBO_USER_APPKEY = "1695264410";// 新浪appkey
	public static final String SINAWEIBO_USER_REDIRECTURL = "http://www.sina.com";// 新浪回调地址
	public static final String SINAWEIBO_USER_SCOPE = "email,direct_messages_read,direct_messages_write,"
			+ "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
			+ "follow_app_official_microblog," + "invitation_write";

	/**
	 * 微信分享
	 */
	// APP_ID 替换为你的应用从官方网站申请到的合法appId
	public static final String WX_APP_ID = "wxce331f7c9a61ad1e";

	/**
	 * 分享标题
	 */
	public static final String SHARE_TITLE = "欢迎使用沃豆";

	/**
	 * 分享内容
	 */
	public static final String SHARE_DESCRIPTION = "下载沃豆。。。。。。";

	/**
	 * 分享内容（带网页）
	 */
	public static final String SHARE_CONTENT = SHARE_DESCRIPTION;
}
