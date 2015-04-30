package com.community.framework.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.community.framework.activity.HouseWorkingActivity;
import com.community.framework.adapter.ViewPagerAdapter;
import com.community.framework.bean.TopPicBean;
import com.community.framework.widget.MyViewPager;
import com.community.framework.widget.MyViewPager.OnSingleTouchListener;
import com.community.framework.widget.PageControl;
import com.community.comfortcommunity.R;

/**
 * 推荐页（新）
 * 
 * @version 使用列表形式上下滑动
 * 
 * @author smile
 * 
 */
public class HomeFragment extends BaseFragment {
	private MyViewPager pager;
	private PageControl mPageControl;
	private int imageFlag = 0;

	public static HomeFragment f;

	public static HomeFragment getInstance() {
		if (f == null) {

			f = new HomeFragment();
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

		View view = inflater.inflate(R.layout.fragment_home, container, false);
		getViews(view);

		return view;
	}

	private void getViews(View v) {

		pager = (MyViewPager) v.findViewById(R.id.recomend_pager);
		mPageControl = (PageControl) v.findViewById(R.id.sy_swip_pagecontrol);
		initViewPager();
		LinearLayout ll_homeworking = (LinearLayout) v
				.findViewById(R.id.ll_homeworking);
		ll_homeworking.setOnClickListener(this);
	}

	private void initViewPager() {

		topPics = new ArrayList<TopPicBean>();
		TopPicBean picBean1 = new TopPicBean();
		picBean1.setAdvPic("http://218.200.229.165:8080/vod_pic/2015/04/24/5539f0134e11b.jpg");
		topPics.add(picBean1);
		TopPicBean picBean2 = new TopPicBean();
		picBean2.setAdvPic("http://218.200.229.165:8080/vod_pic/2015/04/23/5538ac7993bbf.png");
		topPics.add(picBean2);

		if (topPics != null && topPics.size() > 0) {
			mPageControl.setPageCount(topPics.size());
			mPageControl.setInactiveDrawable(getResources().getDrawable(
					R.drawable.page_indicator_unfocused));
			mPageControl.setActiveDrawable(getResources().getDrawable(
					R.drawable.page_indicator_focused));

			ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(
					getActivity(), topPics);
			pager.setAdapter(mViewPagerAdapter);
			pager.setOnPageChangeListener(mOnPageChangeListener);

			pager.setOnSingleTouchListener(new OnSingleTouchListener() {

				@Override
				public void onSingleTouch() {

				}
			});

			mViewPagerAdapter.notifyDataSetChanged();
			handler.postDelayed(runnable, 2000);
		} else {
			pager.setVisibility(View.GONE);
			mPageControl.setVisibility(View.GONE);
		}

	}

	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			// 要做的事情
			handler.postDelayed(this, 5000);
			imageFlag++;
			if (imageFlag > topPics.size() - 1) {
				imageFlag = 0;
			}
			pager.setCurrentItem(imageFlag);
		}
	};

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			// case MSG_END_FRAMEANIMATION:
			// // aniDraw.stop();
			// break;

			default:
				break;
			}
		};
	};

	private OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			mPageControl.setCurrentPage(arg0);
			imageFlag = arg0;
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}
	};
	private List<TopPicBean> topPics;

	@Override
	public void onClickEvent(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
		// 家政
		case R.id.ll_homeworking:
			intent = new Intent(getActivity(), HouseWorkingActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}
}
