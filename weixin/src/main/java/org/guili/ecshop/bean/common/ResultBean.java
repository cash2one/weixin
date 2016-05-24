package org.guili.ecshop.bean.common;

import java.io.Serializable;

public class ResultBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 111L;
	private String code="0";
	private String msg="success";
	private Object resultObject;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getResultObject() {
		return resultObject;
	}
	public void setResultObject(Object resultObject) {
		this.resultObject = resultObject;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
