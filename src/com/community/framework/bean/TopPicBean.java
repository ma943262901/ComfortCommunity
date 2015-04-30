package com.community.framework.bean;


public class TopPicBean  extends EntityBase  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8881061377047076705L;
	private String advName;
	private String advPic;// localhost/aijiamigu/uploadfile/2014/11/16/546859ca59fa9.jpg",
	private String advType;
	private String advId;

	public String getAdvName() {
		return advName;
	}

	public void setAdvName(String advName) {
		this.advName = advName;
	}

	public String getAdvPic() {
		return advPic;
	}

	public void setAdvPic(String advPic) {
		this.advPic = advPic;
	}

	public String getAdvType() {
		return advType;
	}

	public void setAdvType(String advType) {
		this.advType = advType;
	}

	public String getAdvId() {
		return advId;
	}

	public void setAdvId(String advId) {
		this.advId = advId;
	}

}
