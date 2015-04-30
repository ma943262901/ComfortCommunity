package com.community.framework.activity;

import com.community.comfortcommunity.R;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

public class HouseWorkingActivity extends BaseActivity {

	@Override
	public void setContentLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_house_working);

	}

	@Override
	public void dealLogicBeforeInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		initHeader(true, "家政", 0);
		LinearLayout ll_clean_normal = (LinearLayout) findViewById(R.id.ll_clean_normal);
		LinearLayout ll_clean_deep = (LinearLayout) findViewById(R.id.ll_clean_deep);
		LinearLayout ll_clean_long = (LinearLayout) findViewById(R.id.ll_clean_long);
		LinearLayout ll_baby_sitter = (LinearLayout) findViewById(R.id.ll_baby_sitter);
		LinearLayout ll_moon_baby_sitter = (LinearLayout) findViewById(R.id.ll_moon_baby_sitter);
		LinearLayout ll_take_care_old = (LinearLayout) findViewById(R.id.ll_take_care_old);
		ll_clean_normal.setOnClickListener(this);
		ll_clean_deep.setOnClickListener(this);
		ll_clean_long.setOnClickListener(this);
		ll_baby_sitter.setOnClickListener(this);
		ll_moon_baby_sitter.setOnClickListener(this);
		ll_take_care_old.setOnClickListener(this);
	}

	@Override
	public void dealLogicAfterInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClickEvent(View view) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (view.getId()) {
		case R.id.text_back:
			finish();
			break;
		case R.id.ll_clean_normal:
			intent = new Intent(this, HouseCleanNormalActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_clean_deep:
			intent = new Intent(this, HouseCleanDeepActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_clean_long:
			intent = new Intent(this, HouseCleanLongActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_baby_sitter:
			intent = new Intent(this, HouseCleanLongActivity.class);
			startActivity(intent);
			break;
		// 月嫂
		case R.id.ll_moon_baby_sitter:
			intent = new Intent(this, HouseMoonBabySitterActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_take_care_old:
			intent = new Intent(this, HouseWorkingActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}

}
