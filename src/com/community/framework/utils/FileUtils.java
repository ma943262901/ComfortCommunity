package com.community.framework.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.webkit.MimeTypeMap;

@SuppressLint("NewApi")
public class FileUtils {

	private static final double KB = 1024.0;
	private static final double MB = KB * KB;
	private static final double GB = KB * KB * KB;

	/** 获取文件后缀 */
	public static String getFileExtension(File f) {
		if (f != null) {
			String filename = f.getName();
			int i = filename.lastIndexOf('.');
			if (i > 0 && i < filename.length() - 1) {
				return filename.substring(i + 1).toLowerCase();
			}
		}
		return null;
	}

	public static String getUrlFileName(String url) {
		int slashIndex = url.lastIndexOf('/');
		int dotIndex = url.lastIndexOf('.');
		String filenameWithoutExtension;
		if (dotIndex == -1) {
			filenameWithoutExtension = url.substring(slashIndex + 1);
		} else {
			filenameWithoutExtension = url.substring(slashIndex + 1, dotIndex);
		}
		return filenameWithoutExtension;
	}

	public static String getUrlExtension(String url) {
		if (!StringUtils.isEmpty(url)) {
			int i = url.lastIndexOf('.');
			if (i > 0 && i < url.length() - 1) {
				return url.substring(i + 1).toLowerCase();
			}
		}
		return "";
	}

	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}

	public static String showFileSize(long size) {
		String fileSize;
		if (size < KB)
			fileSize = size + "B";
		else if (size < MB)
			fileSize = String.format("%.1f", size / KB) + " KB";
		else if (size < GB)
			fileSize = String.format("%.1f", size / MB) + " MB";
		else
			fileSize = String.format("%.1f", size / GB) + " GB";

		return fileSize;
	}

	/** 显示SD卡剩余空间 */
	public static String showFileAvailable() {
		String result = "";
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			StatFs sf = new StatFs(Environment.getExternalStorageDirectory()
					.getPath());
			long blockSize = sf.getBlockSize();
			long blockCount = sf.getBlockCount();
			long availCount = sf.getAvailableBlocks();
			return showFileSize(availCount * blockSize) + " / "
					+ showFileSize(blockSize * blockCount);
		}
		return result;
	}

	/** 如果不存在就创建 */
	public static boolean createIfNoExists(String path) {
		File file = new File(path);
		boolean mk = false;
		if (!file.exists()) {
			mk = file.mkdirs();
		}
		return mk;
	}

	public static String sdCard(Context c) {
		// ToastUtils.showToast(paths.toString());
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
			return sdDir.toString();
		}

		return "";
	}

	public static String sdExtCard(Context c) {
		StorageManager sm = (StorageManager) c.getApplicationContext()
				.getSystemService(Context.STORAGE_SERVICE);
		try {
			String[] paths = (String[]) sm.getClass()
					.getMethod("getVolumePaths", null).invoke(sm, null);
			// ToastUtils.showToast(paths.toString());
			String innerPath = "";
			File sdDir = null;
			boolean sdCardExist = Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
			if (sdCardExist) {
				sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
			}
			String sdCardPath = sdDir.toString();
			for (String string : paths) {
				if (!string.equals(sdCardPath)) {
					innerPath = string /* + "/DCIM" */;
					return innerPath;
				}
			}

		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (InvocationTargetException e) {

			e.printStackTrace();
		} catch (NoSuchMethodException e) {

			e.printStackTrace();
		}
		return "";
	}

	public static boolean sdAvailable() {
		return Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment
				.getExternalStorageState())
				|| Environment.MEDIA_MOUNTED.equals(Environment
						.getExternalStorageState());
	}

	public static String makeDocFile() {

		String sdStateString = android.os.Environment.getExternalStorageState();

		if (sdStateString.equals(android.os.Environment.MEDIA_MOUNTED)) {
			try {
				File sdFile = android.os.Environment
						.getExternalStorageDirectory();

				String path = sdFile.getAbsolutePath() + File.separator
						+ "PPKJ" + File.separator + "doc";

				File dirFile = new File(path);
				if (!dirFile.exists()) {
					dirFile.mkdir();
				}

				File myFile = new File(path + File.separator + "doc.html");

				if (!myFile.exists()) {
					myFile.createNewFile();
				}

				return myFile.getAbsolutePath();
			} catch (Exception e) {

			}
		}
		return null;
	}

	public static String makeExcelFile() {

		String sdStateString = android.os.Environment.getExternalStorageState();

		if (sdStateString.equals(android.os.Environment.MEDIA_MOUNTED)) {
			try {
				File sdFile = android.os.Environment
						.getExternalStorageDirectory();

				String path = sdFile.getAbsolutePath() + File.separator
						+ "loveReader" + File.separator + "excel";

				// String temp = path + File.separator + "excel.html";

				File dirFile = new File(path);
				if (!dirFile.exists()) {
					dirFile.mkdir();
				}

				File myFile = new File(path + File.separator + "excel.html");

				if (!myFile.exists()) {
					myFile.createNewFile();
				}

				return myFile.getAbsolutePath();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String getFileMimeType(File file) {
		String type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
				getFileExtension(file));
		if (type == null)
			return "*/*";
		return type;
	}

	/**
	 * 获取下载存储地址
	 * 
	 * @param context
	 * @return
	 */
	public static String getDownloadSavePath(Context context) {
		return context.getExternalFilesDir(null).getAbsolutePath();
	}

	/**
	 * 获取Sd卡图片资源
	 * 
	 * @param path
	 * @return
	 */
	public static Bitmap getSdcardImage(String path) {
		File mFile = new File(path);
		// 若该文件存在
		if (mFile.exists()) {
			Bitmap bitmap = BitmapFactory.decodeFile(path);
			return bitmap;
		}

		return null;
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param filePath
	 * @param size
	 * @return
	 */
	public static boolean isFileExists(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			return true;
		}
		return false;
	}

	public static String getFileFromAssets(Context context, String fileName) {
		try {
			InputStream in = context.getResources().getAssets().open(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					in, "UTF-8"));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
