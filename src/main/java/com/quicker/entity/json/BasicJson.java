package com.quicker.entity.json;

public class BasicJson {
	private boolean status = false;
	private Errmsg errorMsg = new Errmsg();
	private Object jsonString;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Errmsg getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(Errmsg errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Object getJsonString() {
		return jsonString;
	}
	public void setJsonString(Object jsonString) {
		this.jsonString = jsonString;
	}
}
