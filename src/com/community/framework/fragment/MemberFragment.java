package com.community.framework.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.community.comfortcommunity.R;

/**
 * 影音
 * 
 */
public class MemberFragment extends BaseFragment {
	public static MemberFragment f;

	public static MemberFragment getInstance() {
		if (f == null) {

			f = new MemberFragment();
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
		View view = inflater
				.inflate(R.layout.fragment_member, container, false);
		return view;
	}

	@Override
	public void onClickEvent(View v) {
		// TODO Auto-generated method stub

	}

}
