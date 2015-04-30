package com.community.framework.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.community.comfortcommunity.R;

/**
 * 个人中心
 * 
 */
public class UserInfoFragment extends BaseFragment {
	public static UserInfoFragment f;

	public static UserInfoFragment getInstance() {
		if (f == null) {

			f = new UserInfoFragment();
		}
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_userinfo, container,
				false);
		return view;
	}

	@Override
	public void onClickEvent(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
		// case R.id.btnApiTest:
		// intent = new Intent(getActivity(), TestApiActivity.class);
		// startActivity(intent);
		// break;
		//
		// case R.id.user_setting:
		// intent = new Intent(getActivity(), SettingActivity.class);
		// startActivity(intent);
		// break;
		// case R.id.user_recharge: //优惠券
		// intent = new Intent(getActivity(), MyPrizeActivity.class);
		// startActivity(intent);
		// break;
		// case R.id.user_collect: // 我的收藏
		// intent = new Intent(getActivity(), PurchaseVipActivity.class);
		// startActivity(intent);
		// break;
		//
		// case R.id.wofun_account_info: //账户信息
		// intent = new Intent(getActivity(), AccountInfoActivity.class);
		// startActivity(intent);
		// break;
		default:
			break;
		}
	}
}
