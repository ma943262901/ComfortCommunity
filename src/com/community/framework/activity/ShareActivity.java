package com.community.framework.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.community.comfortcommunity.R;
import com.community.framework.app.Constants;
import com.community.framework.utils.Util;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.utils.Utility;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXTextObject;
import com.tencent.mm.sdk.openapi.WXWebpageObject;

public class ShareActivity extends BaseActivity implements
		IWeiboHandler.Response {

	@Override
	public void setContentLayout() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dealLogicBeforeInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dealLogicAfterInitView() {
		// TODO Auto-generated method stub

	}

	private IWXAPI api;
	/** 支持发送到朋友圈最小版本号 */
	private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;
	/** 微博微博分享接口实例 */
	private IWeiboShareAPI mWeiboShareAPI = null;

	private static final String SHARE_TO_WEIXINQUAN = "1";
	private static final String SHARE_TO_WEIXIN = "2";
	private static final String SHARE_TO_WEIBO = "3";
	private static final String SHARE_TO_MESSAGE = "4";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	//
	// View view = inflater.inflate(R.layout.fragment_share, container, false);
	// initView(view);
	// return view;
	// }

	// private void initView(View view) {
	// // TODO Auto-generated method stub
	//
	// TextView share_to_weixinquan = (TextView) view
	// .findViewById(R.id.share_to_weixinquan);
	// TextView share_to_weixin = (TextView) view
	// .findViewById(R.id.share_to_weixin);
	// TextView share_to_sina = (TextView) view
	// .findViewById(R.id.share_to_sina);
	// TextView share_to_message = (TextView) view
	// .findViewById(R.id.share_to_message);
	//
	// share_to_weixinquan.setOnClickListener(this);
	// share_to_weixin.setOnClickListener(this);
	// share_to_sina.setOnClickListener(this);
	// share_to_message.setOnClickListener(this);
	//
	// }

	// @Override
	// public void onClickEvent(View v) {
	// // TODO Auto-generated method stub
	//
	// switch (v.getId()) {
	// // 分享到微信
	// case R.id.share_to_weixin:
	// onWeixinShare();
	// break;
	// // 分享到微信朋友圈
	// case R.id.share_to_weixinquan:
	// onWeixinFriendShare();
	// break;
	// // 分享到微博
	// case R.id.share_to_sina:
	// shareToWeiBo();
	// break;
	// // 分享到短信朋友
	// case R.id.share_to_message:
	// shareToMessage();
	// break;
	//
	// default:
	// break;
	// }
	// }

	/**
	 * 初始化微信分享
	 */
	private void initWeiXinShare() {
		// 创建微信分享接口实例
		api = WXAPIFactory.createWXAPI(ShareActivity.this, Constants.WX_APP_ID,
				false);
		api.registerApp(Constants.WX_APP_ID);
	}

	/**
	 * 微信分享
	 */
	private void onWeixinShare() {
		initWeiXinShare();

		if (api.isWXAppInstalled()) {
			shareWebToWeixin(false);
		} else {
			Toast.makeText(ShareActivity.this, "您的手机没有下载微信，请您下载最新版本！", 2000)
					.show();
			// ToastUtil.toastMessage(ShareActivity.this,
			// "您的手机没有下载微信，请您下载最新版本！");
		}
	}

	/**
	 * 朋友圈分享
	 */
	private void onWeixinFriendShare() {
		initWeiXinShare();

		int wxSdkVersion = api.getWXAppSupportAPI();
		if (wxSdkVersion >= TIMELINE_SUPPORTED_VERSION) {
			shareWebToWeixin(true);
		} else {
			Toast.makeText(ShareActivity.this, "您的微信版本不支持分享到朋友圈，请您下载最新版本！",
					2000).show();
			// ToastUtils.toastMessage(ShareActivity.this,
			// "您的微信版本不支持分享到朋友圈，请您下载最新版本！");
		}
	}

	private String mShareMsg = "";

	/**
	 * 微信分享之朋友圈或是微博
	 * 
	 * @paramn isTimeline 若为ture则分享至朋友圈，否则直接分享给微信好友
	 */
	private void shareWeixinTo(boolean isTimeline) {
		// 初始化一个WXTextObject对象
		mShareMsg = Constants.SHARE_CONTENT;
		WXTextObject textObj = new WXTextObject();
		textObj.text = mShareMsg;

		// 用WXTextObject对象初始化一个WXMediaMessage对象
		WXMediaMessage msg = new WXMediaMessage();
		msg.mediaObject = textObj;
		// 发送文本类型的消息时，title字段不起作用
		msg.description = mShareMsg;

		// 构造一个Req
		SendMessageToWX.Req req = new SendMessageToWX.Req();

		req.transaction = buildTransaction("text"); // transaction字段用于唯一标识一个请求
		req.message = msg;
		req.scene = isTimeline ? SendMessageToWX.Req.WXSceneTimeline
				: SendMessageToWX.Req.WXSceneSession;

		// 调用api接口发送数据到微信
		api.sendReq(req);
	}

	/**
	 * 分享网页到微信/朋友圈
	 * 
	 * @param isTimeline
	 */
	private void shareWebToWeixin(boolean isTimeline) {
		WXWebpageObject webpage = new WXWebpageObject();
		if (isTimeline) {

			webpage.webpageUrl = getShareHtml(SHARE_TO_WEIXINQUAN);
		} else {
			webpage.webpageUrl = getShareHtml(SHARE_TO_WEIXIN);
		}
		WXMediaMessage msg = new WXMediaMessage(webpage);
		msg.title = Constants.SHARE_TITLE;
		msg.description = Constants.SHARE_DESCRIPTION;
		Bitmap thumb = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		msg.thumbData = Util.bmpToByteArray(thumb, true);

		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransaction("webpage");
		req.message = msg;
		req.scene = isTimeline ? SendMessageToWX.Req.WXSceneTimeline
				: SendMessageToWX.Req.WXSceneSession;
		api.sendReq(req);
	}

	private String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis())
				: type + System.currentTimeMillis();
	}

	/**
	 * 分享到短信
	 */
	private void shareToMessage() {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.putExtra("sms_body", Constants.SHARE_CONTENT
				+ getShareHtml(SHARE_TO_MESSAGE));
		intent.setType("vnd.android-dir/mms-sms");
		ShareActivity.this.startActivity(intent);
	}

	/**
	 * 分享到微博
	 */
	private void shareToWeiBo() {
		// 创建微博分享接口实例
		mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(ShareActivity.this,
				Constants.SINAWEIBO_USER_APPKEY);

		// 注册第三方应用到微博客户端中，注册成功后该应用将显示在微博的应用列表中。
		// 但该附件栏集成分享权限需要合作申请，详情请查看 Demo 提示
		// NOTE：请务必提前注册，即界面初始化的时候或是应用程序初始化时，进行注册
		mWeiboShareAPI.registerApp();

		sendSingleMessage(true, false, false, false, false);
	}

	/**
	 * 第三方应用发送请求消息到微博，唤起微博分享界面。 当{@link IWeiboShareAPI#getWeiboAppSupportAPI()}
	 * < 10351 时，只支持分享单条消息，即 文本、图片、网页、音乐、视频中的一种，不支持Voice消息。
	 * 
	 * @param hasText
	 *            分享的内容是否有文本
	 * @param hasImage
	 *            分享的内容是否有图片
	 * @param hasWebpage
	 *            分享的内容是否有网页
	 * @param hasMusic
	 *            分享的内容是否有音乐
	 * @param hasVideo
	 *            分享的内容是否有视频
	 */
	private void sendSingleMessage(boolean hasText, boolean hasImage,
			boolean hasWebpage, boolean hasMusic, boolean hasVideo) {

		// 1. 初始化微博的分享消息
		// 用户可以分享文本、图片、网页、音乐、视频中的一种
		WeiboMessage weiboMessage = new WeiboMessage();
		if (hasText) {
			weiboMessage.mediaObject = getTextObj();
		}
		if (hasImage) {
			// weiboMessage.mediaObject = getImageObj();
		}
		if (hasWebpage) {
			// weiboMessage.mediaObject = getWebpageObj();
		}
		if (hasMusic) {
			// weiboMessage.mediaObject = getMusicObj();
		}
		if (hasVideo) {
			// weiboMessage.mediaObject = getVideoObj();
		}
		/*
		 * if (hasVoice) { weiboMessage.mediaObject = getVoiceObj(); }
		 */

		// 2. 初始化从第三方到微博的消息请求
		SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();
		// 用transaction唯一标识一个请求
		request.transaction = String.valueOf(System.currentTimeMillis());
		request.message = weiboMessage;

		// 3. 发送请求消息到微博，唤起微博分享界面
		mWeiboShareAPI.sendRequest(request);
	}

	/**
	 * 接收微客户端博请求的数据。 当微博客户端唤起当前应用并进行分享时，该方法被调用。
	 * 
	 * @param baseRequest
	 *            微博请求数据对象
	 * @see {@link IWeiboShareAPI#handleWeiboRequest}
	 */
	@Override
	public void onResponse(BaseResponse baseResp) {
		switch (baseResp.errCode) {
		case WBConstants.ErrorCode.ERR_OK:
			Toast.makeText(ShareActivity.this, "share_success",
					Toast.LENGTH_LONG).show();
			break;
		case WBConstants.ErrorCode.ERR_CANCEL:
			Toast.makeText(ShareActivity.this, "share_canceled",
					Toast.LENGTH_LONG).show();
			break;
		case WBConstants.ErrorCode.ERR_FAIL:
			Toast.makeText(ShareActivity.this,
					"share_failed)" + "Error Message: " + baseResp.errMsg,
					Toast.LENGTH_LONG).show();
			break;
		}
	}

	/**
	 * 创建文本消息对象。
	 * 
	 * @return 文本消息对象。
	 */
	private TextObject getTextObj() {
		TextObject textObject = new TextObject();
		textObject.text = Constants.SHARE_CONTENT;
		return textObject;
	}

	/**
	 * 创建多媒体（网页）消息对象。
	 * 
	 * @return 多媒体（网页）消息对象。
	 */
	private WebpageObject getWebpageObj() {
		WebpageObject mediaObject = new WebpageObject();
		mediaObject.identify = Utility.generateGUID();
		mediaObject.title = Constants.SHARE_TITLE;
		mediaObject.description = Constants.SHARE_DESCRIPTION;

		// 设置 Bitmap 类型的图片到视频对象里
		Bitmap thumb = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		mediaObject.setThumbImage(thumb);
		mediaObject.actionUrl = getShareHtml(SHARE_TO_WEIBO);
		mediaObject.defaultText = "爱家TV用过都说好！";
		return mediaObject;
	}

	/**
	 * type和userId为需要传的参数，视情况改变
	 * 
	 * @param type
	 * @param userId
	 * @return
	 */
	public String getShareHtml(String type) {
		return Constants.SHARE_HTML + "?invite_type=" + type;
	}

	@Override
	public void onClickEvent(View view) {
		// TODO Auto-generated method stub

	}

}
