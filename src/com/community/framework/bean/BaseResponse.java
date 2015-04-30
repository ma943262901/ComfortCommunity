package com.community.framework.bean;

import java.io.Serializable;

/**
 * Response基类
 */
public abstract class BaseResponse implements Serializable {

	private static final long serialVersionUID = 5375804597574885028L;

	/**
	 * 请求结果
	 * <ul>
	 * <li>0：成功</li>
	 * <li>负值：失败</li>
	 * </ul>
	 */
	public int result = -1;

	/**
	 * 请求类别
	 */
	public String requsetType;

}
