package com.community.framework.activity;

import android.content.Intent;
import android.view.View;

import com.community.comfortcommunity.R;

/**
 * 做饭
 * 
 * @author mwz123
 * 
 */
public class HouseCleanLongMakeFoodActivity extends BaseActivity {

	@Override
	public void setContentLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_house_clean_long_make_food);

	}

	@Override
	public void dealLogicBeforeInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		initHeader(true, "烧饭", 0);
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

		default:
			break;
		}

	}
}
