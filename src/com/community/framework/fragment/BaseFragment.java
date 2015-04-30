package com.community.framework.fragment;

import java.lang.reflect.Field;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.BadTokenException;
import android.widget.ImageView;
import android.widget.TextView;

import com.community.comfortcommunity.R;

public abstract class BaseFragment extends Fragment implements OnClickListener {
	public final String TAG = this.getClass().getSimpleName();
	// protected SharedPrefHelper mSharedPrefHelper;
	public String userid;
	public int currentPage = 1;
	public final static int pageItemSize = 10;
	private ProgressDialog progressDialog;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	/**
	 * 
	 * 页面头部
	 * 
	 * @param isShowBack是否显示返回按钮
	 * @param headTittle头部标题
	 * @param imageResId
	 *            右部图片路径
	 */
	public void initHeader(View view, boolean isShowBack, String headTittle,
			int imageResId) {
		try {
			TextView text_back = (TextView) view.findViewById(R.id.text_back);
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
			TextView tittle = (TextView) view.findViewById(R.id.tittle);
			tittle.setText(headTittle);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			ImageView rightView = (ImageView) view
					.findViewById(R.id.common_header_right);
			if (imageResId != 0) {
				rightView.setVisibility(View.VISIBLE);
				rightView.setBackgroundResource(imageResId);
			} else {
				rightView.setVisibility(View.GONE);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		// mSharedPrefHelper = SharedPrefHelper.getInstance(getActivity());
		// userid = getActivity().getSharedPreferences("userInfo",
		// Context.MODE_PRIVATE).getString(Config.USER_ID_PARAMS, "");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		super.onDetach();
		try {
			Field childFragmentManager = Fragment.class
					.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);

		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onClick(View v) {
		onClickEvent(v);
	}

	public int getScreenWidth() {
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		return screenWidth;
	}

	public abstract void onClickEvent(View v);

	/**
	 * 显示正在加载的进度条
	 * 
	 */
	public void showProgressDialog() {
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
			progressDialog = null;
		}
		progressDialog = new ProgressDialog(getActivity());
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
		progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage(msg);
		try {
			progressDialog.show();
		} catch (BadTokenException exception) {
			exception.printStackTrace();
		}
	}

	public ProgressDialog createProgressDialog(String msg) {
		ProgressDialog progressDialog = new ProgressDialog(getActivity());
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
