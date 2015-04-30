package com.community.framework.activity;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.BadTokenException;
import android.widget.ImageView;
import android.widget.TextView;

import com.community.comfortcommunity.R;

/**
 * Activity基类
 * 
 * @author smile
 * 
 */
public abstract class BaseActivity extends FragmentActivity implements
		OnClickListener {
	private ProgressDialog progressDialog;
	protected Resources resources;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		resources = getResources();
		setContentLayout();
		dealLogicBeforeInitView();
		initView();
		dealLogicAfterInitView();
	}

	/**
	 * 设置布局文件
	 */
	public abstract void setContentLayout();

	/**
	 * 在实例化布局之前处理的逻辑
	 */
	public abstract void dealLogicBeforeInitView();

	/**
	 * 实例化布局文件/组件
	 */
	public abstract void initView();

	/**
	 * @param isShowBack是否显示返回按钮
	 * @param headTittle头部标题
	 * @param isShowRight是否显示右部图片
	 * @param imageRes
	 *            右部图片路径
	 */
	public void initHeader(boolean isShowBack, String headTittle,
			int imageRightRes) {
		try {
			TextView text_back = (TextView) findViewById(R.id.text_back);
			if (isShowBack) {
				text_back.setVisibility(View.VISIBLE);
				text_back.setOnClickListener(this);
			} else {
				text_back.setVisibility(View.GONE);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			TextView tittle = (TextView) findViewById(R.id.tittle);
			tittle.setText(headTittle);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			ImageView rightView = (ImageView) findViewById(R.id.common_header_right);
			if (imageRightRes != 0) {
				rightView.setVisibility(View.VISIBLE);
				rightView.setBackgroundResource(imageRightRes);
			} else {
				rightView.setVisibility(View.GONE);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	};

	/**
	 * 在实例化布局之后处理的逻辑
	 */
	public abstract void dealLogicAfterInitView();

	/**
	 * 得到屏幕宽度
	 * 
	 * @return 宽度
	 */
	public int getScreenWidth() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		return screenWidth;
	}

	/**
	 * 得到屏幕高度
	 * 
	 * @return 高度
	 */
	public int getScreenHeight() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenHeight = dm.heightPixels;
		return screenHeight;
	}

	/**
	 * 是否全屏和显示标题，true为全屏和无标题，false为无标题，请在setContentView()方法前调用
	 * 
	 * @param fullScreen
	 */
	public void setFullScreen(boolean fullScreen) {
		if (fullScreen) {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		} else {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
		}

	}

	/**
	 * onClick方法的封装，在此方法中处理点击
	 * 
	 * @param view
	 *            被点击的View对象
	 */
	abstract public void onClickEvent(View view);

	@Override
	public void onClick(View v) {
		onClickEvent(v);
	}

	/**
	 * 显示正在加载的进度条
	 * 
	 */
	public void showProgressDialog() {
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
			progressDialog = null;
		}
		progressDialog = new ProgressDialog(BaseActivity.this);
		progressDialog.setMessage("加载中...");
		try {
			progressDialog.show();
		} catch (BadTokenException exception) {
			exception.printStackTrace();
		}
	}

	public void showProgressDialog(String msg) {
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
			progressDialog = null;
		}
		progressDialog = new ProgressDialog(BaseActivity.this);
		progressDialog.setMessage(msg);
		try {
			progressDialog.show();
		} catch (BadTokenException exception) {
			exception.printStackTrace();
		}
	}

	public ProgressDialog createProgressDialog(String msg) {
		ProgressDialog progressDialog = new ProgressDialog(BaseActivity.this);
		progressDialog.setMessage(msg);
		return progressDialog;
	}

	/**
	 * 隐藏正在加载的进度条
	 * 
	 */
	public void dismissProgressDialog() {
		if (null != progressDialog && progressDialog.isShowing() == true) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}

}
