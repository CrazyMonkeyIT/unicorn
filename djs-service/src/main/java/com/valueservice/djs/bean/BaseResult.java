package com.valueservice.djs.bean;

public class BaseResult {
	
	private boolean success;
	
	private String message;

	public BaseResult(){
		this.success = false;
		this.message = "系统错误";
	}

	public BaseResult(boolean success){
		this.success = success;
	}

	public BaseResult(boolean success, String msg){
		this.success = success;
		this.message = msg;
	}
	

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
