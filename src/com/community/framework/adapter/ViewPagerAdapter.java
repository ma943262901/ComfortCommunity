package com.community.framework.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.community.framework.bean.TopPicBean;
import com.community.comfortcommunity.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;

public class ViewPagerAdapter extends PagerAdapter {

	private Context mContext;
	private List<TopPicBean> mTopPics;

	// private DisplayImageOptions options;
	// private OnClickListener mOnClickListener;
	private BitmapUtils mBitmapUtils;
	private BitmapDisplayConfig bigPicDisplayConfig;

	public ViewPagerAdapter(Context context, List<TopPicBean> topPics) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.mTopPics = topPics;
		mBitmapUtils = new BitmapUtils(context);
		bigPicDisplayConfig = new BitmapDisplayConfig();
		mBitmapUtils.configDiskCacheEnabled(true);
		mBitmapUtils.configMemoryCacheEnabled(true);
		mBitmapUtils.configThreadPoolSize(5);
		mBitmapUtils.configDefaultLoadingImage(R.drawable.img_default);// 默认背景图片
		mBitmapUtils.configDefaultLoadFailedImage(R.drawable.img_default);// 加载失败图片
		mBitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);// 设置图片压缩类型
	}

	@Override
	public int getCount() {
		if (mTopPics != null && mTopPics.size() > 0) {
			return mTopPics.size();
		}
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		if (mTopPics != null && mTopPics.size() > 0) {
			((ViewPager) container).removeView((View) object);
		}
	}

	/**
	 * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
	 */
	@Override
	public Object instantiateItem(View container, int position) {
		Holder holder = new Holder();
		holder.iv = new ImageView(mContext);
		int index = position % mTopPics.size();
		holder.iv.setScaleType(ScaleType.FIT_XY);
		mBitmapUtils.display(holder.iv, mTopPics.get(index).getAdvPic());
		((ViewPager) container).addView(holder.iv);
		holder.iv.setTag(position);
		// holder.iv.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		// Log.d("Debug", "onClick:" + arg0.getTag());
		// }
		// });
		return holder.iv;
	}

	private static class Holder {
		ImageView iv;
	}
}
