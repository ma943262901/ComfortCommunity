package com.community.framework.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.community.comfortcommunity.R;

/**
 * 订单
 * 
 */
public class OrderFragment extends BaseFragment {
	public static OrderFragment f;

	public static OrderFragment getInstance() {
		if (f == null) {

			f = new OrderFragment();
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
		View view = inflater.inflate(R.layout.fragment_order, container, false);
		// initHeader(view, false, "游戏", 0);
		// loadData(view);
		// initView(view);
		return view;
	}

	@Override
	public void onClickEvent(View v) {
		// TODO Auto-generated method stub

	}

}
