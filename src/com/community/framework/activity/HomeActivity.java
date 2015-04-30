package com.community.framework.activity;

import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.community.framework.fragment.HomeFragment;
import com.community.framework.fragment.MemberFragment;
import com.community.framework.fragment.OrderFragment;
import com.community.framework.fragment.UserInfoFragment;
import com.community.comfortcommunity.R;

/**
 * 首页
 * 
 * @author smile
 * 
 */
public class HomeActivity extends BaseActivity implements
		OnCheckedChangeListener {

	private RadioGroup mGroup;
	private HomeFragment mHomeFragment;
	private OrderFragment mOrderFragment;
	private MemberFragment mMemberFragment;
	private UserInfoFragment mUserInfoFragment;
	private String mFormerTag;

	private final static String HOME_TAG = "HomeFragment";
	private final static String ORDER_TAG = "OrderFragment";
	private final static String MEMBER_TAG = "MemberFragment";
	private final static String USERINFO_TAG = "UserInfoFragment";

	/**
	 * 最后按退回键的时间
	 */
	private long exitTime;
	private final static long TIME_DIFF = 2 * 1000;

	@Override
	public void setContentLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_home);
	}

	@Override
	public void dealLogicBeforeInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

		mGroup = (RadioGroup) findViewById(R.id.main_radio);
		mHomeFragment = HomeFragment.getInstance();
		mMemberFragment = MemberFragment.getInstance();
		mOrderFragment = OrderFragment.getInstance();
		mUserInfoFragment = UserInfoFragment.getInstance();
		mFormerTag = HOME_TAG;
		getSupportFragmentManager().beginTransaction()
				.add(R.id.main_content, mHomeFragment, HOME_TAG).commit();
	}

	@Override
	public void dealLogicAfterInitView() {
		// TODO Auto-generated method stub
		mGroup.setOnCheckedChangeListener(this);
	}

	@Override
	public void onClickEvent(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {

		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			if ((System.currentTimeMillis() - exitTime) > TIME_DIFF) {
				Toast.makeText(HomeActivity.this, "再按一次退出", Toast.LENGTH_SHORT)
						.show();
				exitTime = System.currentTimeMillis();
			} else {
				System.exit(0);
			}
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 首页导航切换
	 */
	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId) {
		// TODO Auto-generated method stub
		FragmentTransaction mTransaction = getSupportFragmentManager()
				.beginTransaction();
		mTransaction.hide(getSupportFragmentManager().findFragmentByTag(
				mFormerTag));

		switch (checkedId) {

		// 首页
		case R.id.radiobutton_home:
			mFormerTag = HOME_TAG;
			if (mHomeFragment.isAdded()) {
				mTransaction.show(mHomeFragment).commit();
			} else {
				mTransaction.add(R.id.main_content, mHomeFragment, mFormerTag)
						.commit();
			}
			break;

		// 订单
		case R.id.radiobutton_order:
			mFormerTag = ORDER_TAG;
			if (mOrderFragment.isAdded()) {
				mTransaction.show(mOrderFragment).commit();
			} else {
				mTransaction.add(R.id.main_content, mOrderFragment, mFormerTag)
						.commit();
			}

			break;

		// 会员
		case R.id.radiobutton_member:
			mFormerTag = MEMBER_TAG;
			if (mMemberFragment.isAdded()) {
				mTransaction.show(mMemberFragment).commit();
			} else {
				mTransaction
						.add(R.id.main_content, mMemberFragment, mFormerTag)
						.commit();
			}
			break;

		// 个人
		case R.id.radiobutton_my:
			mFormerTag = USERINFO_TAG;
			if (mUserInfoFragment.isAdded()) {
				mTransaction.show(mUserInfoFragment).commit();
			} else {
				mTransaction.add(R.id.main_content, mUserInfoFragment,
						mFormerTag).commit();
			}
			break;
		}
	}

}
