package com.community.framework.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.community.framework.bean.BaseResponse;

/**
 * Parser基类
 * 
 * @author smile
 * 
 * @param <T>
 */
public abstract class BaseParser<T extends BaseResponse> {
	public static final String ERROR_CODE = "code";
	public static final String MSG = "msg";

	public abstract T parse(String paramString);

	public void parseCommon(JSONObject jo, BaseResponse br) {
		br.result = getInt("Result", jo);
		br.requsetType = getString("RequsetType", jo);
	}

	/**
	 * @param key
	 * @param jsonObject
	 * @return
	 */
	public static String getString(String key, JSONObject jsonObject) {
		String res = "";
		if (jsonObject != null && jsonObject.containsKey(key)) {
			if (key == null) {
				return "";
			}

			try {
				res = jsonObject.getString(key);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return res;
	}

	/**
	 * @param key
	 * @param jsonObject
	 * @return
	 */
	public static int getInt(String key, JSONObject jsonObject) {
		int res = -1;
		if (jsonObject != null && jsonObject.containsKey(key)) {
			try {
				res = jsonObject.getIntValue(key);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return res;
	}

	/**
	 * @param key
	 * @param jsonObject
	 * @return
	 */
	public static double getDouble(String key, JSONObject jsonObject) {
		double res = 0l;
		if (jsonObject != null && jsonObject.containsKey(key)) {
			try {
				res = jsonObject.getDouble(key);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return res;
	}

	/**
	 * @param key
	 * @param jsonObject
	 * @return
	 */
	public static JSONArray getJsonArray(String key, JSONObject jsonObject) {
		JSONArray res = null;
		if (jsonObject != null && jsonObject.containsKey(key)) {
			try {
				res = jsonObject.getJSONArray(key);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return res;
	}
}
