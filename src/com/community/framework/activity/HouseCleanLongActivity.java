package com.community.framework.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.community.comfortcommunity.R;

/**
 * 长期钟点工
 * 
 * @author mwz123
 * 
 */
public class HouseCleanLongActivity extends BaseActivity {

	@Override
	public void setContentLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_house_clean_long);

	}

	@Override
	public void dealLogicBeforeInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		initHeader(true, "长期钟点工", 0);
		LinearLayout ll_clean_long_clean = (LinearLayout) findViewById(R.id.ll_clean_long_clean);
		ll_clean_long_clean.setOnClickListener(this);
		LinearLayout ll_clean_long_make_food = (LinearLayout) findViewById(R.id.ll_clean_long_make_food);
		ll_clean_long_make_food.setOnClickListener(this);
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
		case R.id.ll_clean_long_clean:
			intent = new Intent(this, HouseCleanLongCleanActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_clean_long_make_food:
			intent = new Intent(this, HouseCleanLongMakeFoodActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}
}
