package com.community.framework.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.community.comfortcommunity.R;

/**
 * 育儿嫂
 * 
 * @author mwz123
 * 
 */
public class HouseTakecareBabySitterActivity extends BaseActivity {

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
		initHeader(true, "育儿嫂", 0);
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
			intent = new Intent(this, HouseCleanDeepCheckActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}
}
