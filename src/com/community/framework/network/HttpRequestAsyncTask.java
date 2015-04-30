package com.community.framework.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.community.framework.utils.LogUtil;

public class HttpRequestAsyncTask extends AsyncTask<Request, Void, Object> {
	// private static final int RESPONSE_TIME_OUT = 30000;
	// private static final int REQUEST_TIME_OUT = 30000;

	public HttpRequestAsyncTask(Context context) {
	}

	private String resultString;
	private OnCompleteListener onCompleteListener;

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

	}

	@Override
	protected Object doInBackground(Request... params) {

		Object object = null;
		Request request = params[0];

		String urlString = request.getServerInterfaceDefinition().getOpt();

		try {
			HttpResponse httpResponse;
			HttpGet httpGet = null;
			HttpPost httpPost = null;

			if (request.getServerInterfaceDefinition().getRequestMethod()
					.equals(ServerInterfaceDefinition.RequestMethod.GET)) {
				// 默认Get方式
				StringBuffer sb = new StringBuffer(urlString + "?");
				for (Map.Entry<String, String> entry : request.getParamsMap()
						.entrySet()) {
					sb.append(entry.getKey());
					sb.append('=');
					sb.append(URLEncoder.encode(entry.getValue(), HTTP.UTF_8));
					sb.append('&');
				}
				sb.deleteCharAt(sb.length() - 1);

				Log.d("Debug", "urlString:" + sb.toString());
				httpGet = new HttpGet(sb.toString());
				HttpManager.shortTimeOut();
				httpResponse = HttpManager.execute(httpGet);

			} else {
				// Post方式
				httpPost = new HttpPost(urlString);
				String body = request.getBody();
				httpPost.setEntity(new StringEntity(chinaToUnicode(body)));
				HttpManager.shortTimeOut();
				httpResponse = HttpManager.execute(httpPost);
			}

			if (httpResponse.getStatusLine().getStatusCode() != 200) {
				LogUtil.log("getStatusCode="
						+ httpResponse.getStatusLine().getStatusCode());
				if (null != httpPost) {
					httpPost.abort();
				}
				if (null != httpGet) {
					httpGet.abort();
				}
			} else {
				resultString = EntityUtils.toString(httpResponse.getEntity(),
						"UTF-8");
				LogUtil.log("result=" + resultString);

				if (request.getJsonParser() != null) {
					object = request.getJsonParser().parse(resultString);
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;

	}

	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		if (null != onCompleteListener) {
			if (null == result) {
				onCompleteListener.onComplete(null, null);
			} else {
				onCompleteListener.onComplete(result, resultString);
			}
		}
	}

	private boolean downloadUrlToStream(String urlString,
			OutputStream outputStream) {

		try {

			outputStream.write(urlString.getBytes());
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	private String chinaToUnicode(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int chr1 = (char) str.charAt(i);
			if (chr1 >= 19968 && chr1 <= 171941) {// 汉字范围 \u4e00-\u9fa5 (中文)
				result += "\\u" + Integer.toHexString(chr1);
			} else {
				result += str.charAt(i);
			}
		}
		return result;
	}

	public interface OnCompleteListener<T> {
		public void onComplete(T result, String resultString);
	}

	public OnCompleteListener getOnCompleteListener() {
		return onCompleteListener;
	}

	public void setOnCompleteListener(OnCompleteListener onCompleteListener) {
		this.onCompleteListener = onCompleteListener;
	}
}
