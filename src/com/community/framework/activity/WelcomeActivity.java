package com.community.framework.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.ImageView;

import com.community.framework.spfs.SharedPrefHelper;
import com.community.framework.utils.JsonTestUtil;
import com.community.comfortcommunity.R;

/**
 * 欢迎界面
 * 
 * @author smile
 * 
 */
public class WelcomeActivity extends Activity {

	private SharedPrefHelper mSharedPrefHelper;
	private ImageView imv_welcome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		mSharedPrefHelper = SharedPrefHelper.getInstance(this);
		SharedPreferences sharePref = getSharedPreferences("userInfo",
				Context.MODE_PRIVATE);

		// 设置欢迎界面背景
		imv_welcome = (ImageView) findViewById(R.id.imv_welcome);
		// imv_welcome.setImageResource(R.drawable.app_loading);

		// 初始化服务器信息
		initServer();

		// App登录首次注册
		registerApp();

		// 延时跳转其他页面
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				setNewIntent();
			}
		}, 2000);
	}

	/**
	 * 初始化服务器信息
	 * 
	 */
	private void initServer() {
		// String result = JsonTestUtil.ChangeServer.getJson(this);
		// ChangeServerParser parser = new ChangeServerParser();
		// ChangeServerResponse response = parser.parse(result);

		// saveServerInfo(response);
	}

	/**
	 * 保存服务器信息
	 * 
	 * @param response
	 */
	// private void saveServerInfo(ChangeServerResponse response) {
	// ChangeServerBean changeServerBean = response.changeServerBean;
	// mSharedPrefHelper.setServerAddress(changeServerBean.getAddress());
	// mSharedPrefHelper.setServerRandomKey(changeServerBean
	// .getServerRandomKey());
	// mSharedPrefHelper.setServerRandomKeyUpdate(changeServerBean
	// .getServerRandomKeyUpdate());
	// }

	/**
	 * App首次登录注册
	 */
	private void registerApp() {

		// // 判断是否首次注册
		// if (TextUtils.isEmpty(mSharedPrefHelper.getAuthKey())) {
		// String result = JsonTestUtil.ClientReg.getJson(this);
		// ClientRegParser parser = new ClientRegParser();
		// ClientRegResponse response = parser.parse(result);
		//
		// saveClientInfo(response);
		// }

	}

	/**
	 * 保存注册信息
	 * 
	 * @param response
	 */
	// private void saveClientInfo(ClientRegResponse response) {
	// // TODO Auto-generated method stub
	// ClientRegBean clientRegBean = response.clientRegBean;
	// mSharedPrefHelper.setAuthKey(clientRegBean.getAuthKey());
	// }

	/**
	 * 跳转主页面
	 */
	private void setNewIntent() {
		Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
		startActivity(intent);
		finish();
	}

}
