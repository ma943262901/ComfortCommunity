package com.community.framework.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.community.comfortcommunity.R;

/**
 * 月嫂
 * 
 * @author mwz123
 * 
 */
public class HouseMoonBabySitterActivity extends BaseActivity {

	@Override
	public void setContentLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_moon_baby_sitter);

	}

	@Override
	public void dealLogicBeforeInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		initHeader(true, "月嫂", 0);
		LinearLayout ll_moon_baby_sitter = (LinearLayout) findViewById(R.id.ll_moon_baby_sitter);
		ll_moon_baby_sitter.setOnClickListener(this);
		LinearLayout ll_takecare_baby_sitter = (LinearLayout) findViewById(R.id.ll_takecare_baby_sitter);
		ll_takecare_baby_sitter.setOnClickListener(this);
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
		// 月嫂
		case R.id.ll_moon_baby_sitter:
			intent = new Intent(this, HouseMoonBabySitterDetailActivity.class);
			startActivity(intent);
			break;
		// 育儿嫂
		case R.id.ll_takecare_baby_sitter:
			intent = new Intent(this, HouseTakecareBabySitterActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}
}
