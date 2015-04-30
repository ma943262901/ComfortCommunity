package com.community.framework.network;

import java.util.Map;

import com.community.framework.parser.BaseParser;

public class Request {

	private ServerInterfaceDefinition serverInterfaceDefinition;
	private Map<String, String> paramsMap;
	private BaseParser<?> jsonParser;
	private String mBody;

	public Request(ServerInterfaceDefinition serverInterfaceDefinition,
			Map<String, String> paramsMap, BaseParser<?> jsonParser) {
		super();
		this.serverInterfaceDefinition = serverInterfaceDefinition;
		this.paramsMap = paramsMap;
		this.jsonParser = jsonParser;
	}

	public Request(ServerInterfaceDefinition serverInterfaceDefinition,
			String body, BaseParser<?> jsonParser) {
		super();
		this.serverInterfaceDefinition = serverInterfaceDefinition;
		this.mBody = body;
		this.jsonParser = jsonParser;
	}

	public Request(ServerInterfaceDefinition serverInterfaceDefinition,
			String body, Map<String, String> paramsMap, BaseParser<?> jsonParser) {
		super();
		this.serverInterfaceDefinition = serverInterfaceDefinition;
		this.mBody = body;
		this.paramsMap = paramsMap;
		this.jsonParser = jsonParser;
	}

	public Map<String, String> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(Map<String, String> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public String getBody() {
		return mBody;
	}

	public void setBody(String mBody) {
		this.mBody = mBody;
	}

	public ServerInterfaceDefinition getServerInterfaceDefinition() {
		return serverInterfaceDefinition;
	}

	public void setServerInterfaceDefinition(
			ServerInterfaceDefinition serverInterfaceDefinition) {
		this.serverInterfaceDefinition = serverInterfaceDefinition;
	}

	public BaseParser<?> getJsonParser() {
		return jsonParser;
	}

	public void setJsonParser(BaseParser<?> jsonParser) {
		this.jsonParser = jsonParser;
	}

}
