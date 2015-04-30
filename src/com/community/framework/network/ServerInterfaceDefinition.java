package com.community.framework.network;

public class ServerInterfaceDefinition {

	private String opt;
	private RequestMethod requestMethod = RequestMethod.GET;
	private int retryNumber = 1;

	public ServerInterfaceDefinition(String opt) {
		this.opt = opt;
	}

	@SuppressWarnings("unused")
	private ServerInterfaceDefinition(String opt, RequestMethod requestMethod) {
		this.opt = opt;
		this.requestMethod = requestMethod;
	}

	@SuppressWarnings("unused")
	private ServerInterfaceDefinition(String opt, RequestMethod requestMethod,
			int retryNumber) {
		this.opt = opt;
		this.requestMethod = requestMethod;
		this.retryNumber = retryNumber;
	}

	public String getOpt() {
		return opt;
	}

	public RequestMethod getRequestMethod() {
		return requestMethod;
	}

	public int getRetryNumber() {
		return retryNumber;
	}

	public enum RequestMethod {
		POST("POST"), GET("GET");
		private String requestMethodName;

		RequestMethod(String requestMethodName) {
			this.requestMethodName = requestMethodName;
		}

		public String getRequestMethodName() {
			return requestMethodName;
		}
	}
}
