package com.community.framework.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.community.comfortcommunity.R;

/**
 * 深度清理
 * 
 * @author mwz123
 * 
 */
public class HouseCleanDeepActivity extends BaseActivity {

	@Override
	public void setContentLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_house_clean_deep);

	}

	@Override
	public void dealLogicBeforeInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		initHeader(true, "日常保洁", 0);
		LinearLayout ll_check_room = (LinearLayout) findViewById(R.id.ll_check_room);
		ll_check_room.setOnClickListener(this);
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
		case R.id.ll_check_room:
			intent = new Intent(this, HouseCleanDeepCheckActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}
}
