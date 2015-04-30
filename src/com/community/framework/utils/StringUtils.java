package com.community.framework.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextPaint;
import android.text.TextUtils;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

/**
 * 字符串工具类
 * 
 * @author tangjun
 * 
 */
public class StringUtils {

	public static final String EMPTY = "";

	private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	private static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd hh:mm:ss";
	/** 用于生成文件 */
	private static final String DEFAULT_FILE_PATTERN = "yyyy-MM-dd-HH-mm-ss";
	private static final double KB = 1024.0;
	private static final double MB = 1048576.0;
	private static final double GB = 1073741824.0;
	public static final SimpleDateFormat DATE_FORMAT_PART = new SimpleDateFormat(
			"HH:mm");

	public static String currentTimeString() {
		return DATE_FORMAT_PART.format(Calendar.getInstance().getTime());
	}

	public static char chatAt(String pinyin, int index) {
		if (pinyin != null && pinyin.length() > 0)
			return pinyin.charAt(index);
		return ' ';
	}

	/** 获取字符串宽度 */
	public static float GetTextWidth(String Sentence, float Size) {
		if (isEmpty(Sentence))
			return 0;
		TextPaint FontPaint = new TextPaint();
		FontPaint.setTextSize(Size);
		return FontPaint.measureText(Sentence.trim()) + (int) (Size * 0.1); // 留点余地
	}

	/**
	 * 格式化日期字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 格式化日期字符串
	 * 
	 * @param date
	 * @return 例如2011-3-24
	 */
	public static String formatDate(Date date) {
		return formatDate(date, DEFAULT_DATE_PATTERN);
	}

	public static String formatDate(long date) {
		return formatDate(new Date(date), DEFAULT_DATE_PATTERN);
	}

	/**
	 * 获取当前时间 格式为yyyy-MM-dd 例如2011-07-08
	 * 
	 * @return
	 */
	public static String getDate() {
		return formatDate(new Date(), DEFAULT_DATE_PATTERN);
	}

	/** 生成一个文件名，不含后缀 */
	public static String createFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FILE_PATTERN);
		return format.format(date);
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getDateTime() {
		return formatDate(new Date(), DEFAULT_DATETIME_PATTERN);
	}

	/**
	 * 格式化日期时间字符串
	 * 
	 * @param date
	 * @return 例如2011-11-30 16:06:54
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, DEFAULT_DATETIME_PATTERN);
	}

	public static String formatDateTime(long date) {
		return formatDate(new Date(date), DEFAULT_DATETIME_PATTERN);
	}

	/**
	 * 格林威时间转换
	 * 
	 * @param gmt
	 * @return
	 */
	public static String formatGMTDate(String gmt) {
		TimeZone timeZoneLondon = TimeZone.getTimeZone(gmt);
		return formatDate(Calendar.getInstance(timeZoneLondon)
				.getTimeInMillis());
	}

	/**
	 * 拼接数组
	 * 
	 * @param array
	 * @param separator
	 * @return
	 */
	public static String join(final ArrayList<String> array,
			final String separator) {
		StringBuffer result = new StringBuffer();
		if (array != null && array.size() > 0) {
			for (String str : array) {
				result.append(str);
				result.append(separator);
			}
			result.delete(result.length() - 1, result.length());
		}
		return result.toString();
	}

	public static String join(final Iterator<String> iter,
			final String separator) {
		StringBuffer result = new StringBuffer();
		if (iter != null) {
			while (iter.hasNext()) {
				String key = iter.next();
				result.append(key);
				result.append(separator);
			}
			if (result.length() > 0)
				result.delete(result.length() - 1, result.length());
		}
		return result.toString();
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		return str == null ? EMPTY : str.trim();
	}

	/**
	 * 转换时间显示
	 * 
	 * @param time
	 *            毫秒
	 * @return
	 */
	public static String generateTime(long time) {
		int totalSeconds = (int) (time / 1000);
		int seconds = totalSeconds % 60;
		int minutes = (totalSeconds / 60) % 60;
		int hours = totalSeconds / 3600;

		return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes,
				seconds) : String.format("%02d:%02d", minutes, seconds);
	}

	/** 根据秒速获取时间格式 */
	public static String gennerTime(int totalSeconds) {
		int seconds = totalSeconds % 60;
		int minutes = (totalSeconds / 60) % 60;
		return String.format("%02d:%02d", minutes, seconds);
	}

	/**
	 * 转换文件大小
	 * 
	 * @param size
	 * @return
	 */
	public static String generateFileSize(long size) {
		String fileSize;
		if (size < KB)
			fileSize = size + "B";
		else if (size < MB)
			fileSize = String.format("%.1f", size / KB) + "KB";
		else if (size < GB)
			fileSize = String.format("%.1f", size / MB) + "MB";
		else
			fileSize = String.format("%.1f", size / GB) + "GB";

		return fileSize;
	}

	public static String getTimeDiff(long time) {
		// Calendar cal = Calendar.getInstance();
		long diff = 0;
		// Date dnow = cal.getTime();
		String str = "";
		diff = System.currentTimeMillis() - time;

		if (diff > 2592000000L) {// 30 * 24 * 60 * 60 * 1000=2592000000 毫秒
			str = "1个月前";
		} else if (diff > 1814400000) {// 21 * 24 * 60 * 60 * 1000=1814400000 毫秒
			str = "3周前";
		} else if (diff > 1209600000) {// 14 * 24 * 60 * 60 * 1000=1209600000 毫秒
			str = "2周前";
		} else if (diff > 604800000) {// 7 * 24 * 60 * 60 * 1000=604800000 毫秒
			str = "1周前";
		} else if (diff > 86400000) { // 24 * 60 * 60 * 1000=86400000 毫秒
			// System.out.println("X天前");
			str = (int) Math.floor(diff / 86400000f) + "天前";
		} else if (diff > 18000000) {// 5 * 60 * 60 * 1000=18000000 毫秒
			// System.out.println("X小时前");
			str = (int) Math.floor(diff / 18000000f) + "小时前";
		} else if (diff > 60000) {// 1 * 60 * 1000=60000 毫秒
			// System.out.println("X分钟前");
			str = (int) Math.floor(diff / 60000) + "分钟前";
		} else {
			str = (int) Math.floor(diff / 1000) + "秒前";
		}
		return str;
	}

	/**
	 * 截取字符串
	 * 
	 * @param search
	 *            待搜索的字符串
	 * @param start
	 *            起始字符串 例如：<title>
	 * @param end
	 *            结束字符串 例如：</title>
	 * @param defaultValue
	 * @return
	 */
	public static String substring(String search, String start, String end,
			String defaultValue) {
		int start_len = start.length();
		int start_pos = StringUtils.isEmpty(start) ? 0 : search.indexOf(start);
		if (start_pos > -1) {
			int end_pos = StringUtils.isEmpty(end) ? -1 : search.indexOf(end,
					start_pos + start_len);
			if (end_pos > -1)
				return search.substring(start_pos + start.length(), end_pos);
			else
				return search.substring(start_pos + start.length());
		}
		return defaultValue;
	}

	/**
	 * 截取字符串
	 * 
	 * @param search
	 *            待搜索的字符串
	 * @param start
	 *            起始字符串 例如：<title>
	 * @param end
	 *            结束字符串 例如：</title>
	 * @return
	 */
	public static String substring(String search, String start, String end) {
		return substring(search, start, end, "");
	}

	/**
	 * 拼接字符串
	 * 
	 * @param strs
	 * @return
	 */
	public static String concat(String... strs) {
		StringBuffer result = new StringBuffer();
		if (strs != null) {
			for (String str : strs) {
				if (str != null)
					result.append(str);
			}
		}
		return result.toString();
	}

	/** 获取中文字符个数 */
	public static int getChineseCharCount(String str) {
		String tempStr;
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			tempStr = String.valueOf(str.charAt(i));
			if (tempStr.getBytes().length == 3) {
				count++;
			}
		}
		return count;
	}

	/** 获取英文字符个数 */
	public static int getEnglishCount(String str) {
		String tempStr;
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			tempStr = String.valueOf(str.charAt(i));
			if (!(tempStr.getBytes().length == 3)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 拼接url
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String joinStringBuffer(String url, Map<String, String> params) {
		// cid/1/epgdate/2013-07-19/uid/1
		StringBuffer paramsBuf = new StringBuffer();
		paramsBuf.append(url);

		if (params != null) {
			int i = 0;
			for (String key : params.keySet()) {
				if (i > 0) {
					paramsBuf.append("/");
				}
				paramsBuf.append(key).append("/").append(params.get(key));
				i++;
			}
		}

		return paramsBuf.toString();
	}

	/**
	 * 随机产生一个唯一码
	 * 
	 * @return
	 */
	public static String getStrMd5ToServer() {
		Date date = new Date();
		String dateStr = DateUtil.dateToStringFormat(date,
				DateUtil.YYYYMMDD_HHMMSS);

		Random random = new Random(date.getTime());

		StringBuffer result = new StringBuffer();
		result.append(dateStr);
		for (int i = 0; i < 3; i++) {
			result.append(random.nextInt(10));
		}

		return getStrMd5(result.toString());
	}

	/**
	 * MD5加密
	 */
	public static String getStrMd5(String params) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.reset();

			digest.update(params.getBytes("UTF-8"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		byte[] byteArray = digest.digest();
		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}

	/**
	 * 替换某个字符串
	 * 
	 * @param source
	 * @param replaceStr
	 * @return
	 */
	public static String replace(String source, String replaceStr) {
		StringBuilder sb = new StringBuilder();
		String[] temp = source.split(",");
		for (String string : temp) {
			if (!string.contains(replaceStr)) {
				sb.append(string);
			}
		}
		return sb.toString();
	}

	/**
	 * 验证手机格式
	 */
	public static boolean isMobileNO(String mobiles) {
		/*
		 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
		String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (TextUtils.isEmpty(mobiles))
			return false;
		else
			return mobiles.matches(telRegex);
	}

	/** 用于判断密码是否合法 */
	public static boolean matchPwd(String pwd) {
		// if (pwd.length() <8 || pwd.length() >16) {
		// return false;
		// }
		String str = "^(?![^a-zA-Z]+$)(?!\\D+$).{6,16}$";
		// String str = "^[0-9a-zA-Z]{6,32}$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(pwd);
		return m.matches();
	}

	public static void setPasswordCanSee(EditText et, boolean isCansee) {
		if (isCansee) {
			et.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
		} else {
			et.setInputType(InputType.TYPE_CLASS_TEXT
					| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		}
	}

	public static void setToggleBtnListener(
			ToggleButton togglebtn_see_password, final EditText et) {
		togglebtn_see_password
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean arg1) {
						// TODO Auto-generated method stub
						if (arg1) {
							StringUtils.setPasswordCanSee(et, arg1);
						} else {
							StringUtils.setPasswordCanSee(et, arg1);
						}

						// // 下面两行代码实现: 输入框光标一直在输入文本后面
						Editable etable = et.getText();
						Selection.setSelection(etable, etable.length());
					}
				});
	}

}
