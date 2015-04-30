package com.community.framework.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.community.comfortcommunity.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class ImageLoaderUtil {

	static DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.img_default) // 设置图片在下载期间显示的图片
			.showImageForEmptyUri(R.drawable.img_default)// 设置图片Uri为空或是错误的时候显示的图片
			.showImageOnFail(R.drawable.img_default) // 设置图片加载/解码过程中错误时候显示的图片
			.cacheInMemory(false)// 设置下载的图片是否缓存在内存中
			.cacheOnDisk(false)// 设置下载的图片是否缓存在SD卡中
			.imageScaleType(ImageScaleType.IN_SAMPLE_INT)// 设置图片以如何的编码方式显示
			.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型
			.build();// 构建完成

	static DisplayImageOptions cacheOptions = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.img_default) // 设置图片在下载期间显示的图片
			.showImageForEmptyUri(R.drawable.img_default)// 设置图片Uri为空或是错误的时候显示的图片
			.showImageOnFail(R.drawable.img_default) // 设置图片加载/解码过程中错误时候显示的图片
			.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
			.cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
			.imageScaleType(ImageScaleType.IN_SAMPLE_INT)// 设置图片以如何的编码方式显示
			.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型
			.build();// 构建完成

	/**
	 * 加载图片（不缓存）
	 * 
	 * @param url
	 * @param container
	 */
	public static void displayImg(String url, ImageView container) {
		ImageLoader.getInstance().displayImage(url, container, options);
	}

	/**
	 * 加载图片（缓存）
	 * 
	 * @param url
	 * @param container
	 * @param isCache
	 */
	public static void displayImg(String url, ImageView container,
			boolean isCache) {
		ImageLoader.getInstance().displayImage(url, container, cacheOptions);
	}

}
