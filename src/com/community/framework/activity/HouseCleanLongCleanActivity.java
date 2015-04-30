package com.community.framework.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ToggleButton;

import com.community.comfortcommunity.R;

/**
 * 保洁
 * 
 * @author mwz123
 * 
 */
public class HouseCleanLongCleanActivity extends BaseActivity {

	@Override
	public void setContentLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_house_clean_long_clean);

	}

	@Override
	public void dealLogicBeforeInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		initHeader(true, "保洁", 0);
		initToggleButton();
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
		// case R.id.ll_clean_long_clean:
		// intent = new Intent(this, HouseCleanDeepCheckActivity.class);
		// startActivity(intent);
		// break;

		default:
			break;
		}

	}

	private void initToggleButton() {
		// TODO Auto-generated method stub
		ToggleButton week1 = (ToggleButton) findViewById(R.id.week1);
		ToggleButton week2 = (ToggleButton) findViewById(R.id.week2);
		ToggleButton week3 = (ToggleButton) findViewById(R.id.week3);
		ToggleButton week4 = (ToggleButton) findViewById(R.id.week4);
		ToggleButton week5 = (ToggleButton) findViewById(R.id.week5);
		ToggleButton week6 = (ToggleButton) findViewById(R.id.week6);
		ToggleButton week7 = (ToggleButton) findViewById(R.id.week7);
		ToggleButton big_dog = (ToggleButton) findViewById(R.id.big_dog);
		ToggleButton small_dog = (ToggleButton) findViewById(R.id.small_dog);
		ToggleButton cat = (ToggleButton) findViewById(R.id.cat);
		setText(week1, "周一");
		setText(week2, "周二");
		setText(week3, "周三");
		setText(week4, "周四");
		setText(week5, "周五");
		setText(week6, "周六");
		setText(week7, "周日");
		setText(big_dog, "大型犬");
		setText(small_dog, "小型犬");
		setText(cat, "猫");
	}

	private void setText(ToggleButton toggleButton, String text) {
		// TODO Auto-generated method stub
		toggleButton.setText(text);
		toggleButton.setTextOn(text);
		toggleButton.setTextOff(text);
	}
}
