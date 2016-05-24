package org.guili.ecshop.bean.exception;

public enum PingLunError {

	INPUT_PARAM_ERROR("1000","输入参数错误！"),
	PINGLUN_EXISTS("1001","评论已存在！"),
	PINGLUN_NOTEXISTS("1002","评论或用户不存在！"),
	USERNAME_ISERROR("1003","当前用户未登录！"),
	USER_HAS_PINGLUN("1004","该问题用户已评论！"),
	;
	
	PingLunError(String code,String msg){
		this.code=code;
		this.msg=msg;
	}
	private String code;
	private String msg;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
