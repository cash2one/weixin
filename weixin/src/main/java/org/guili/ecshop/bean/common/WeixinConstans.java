package org.guili.ecshop.bean.common;

/**
 * womaime init param for website 
 * 
 */
import java.util.ResourceBundle;

import org.guili.ecshop.util.ResourceUtil;

/**
 * 相关二级域名 配置
 */
public final class WeixinConstans{

	/** 接口配置. */
	public static ResourceBundle	weixin			= ResourceBundle.getBundle("config/weixin_config");

	
	/**
	 * head_path imagepath
	 */
	public static final String		head_path		= ResourceUtil.getValue(weixin, "head_path");
	
	/**
	 * daili_path
	 */
	public static final String		daili_path		= ResourceUtil.getValue(weixin, "daili_path");
	
	/**
	 * daili_path
	 */
	public static final String		remote_file_server_ip		= ResourceUtil.getValue(weixin, "remote_file_server_ip");
	
	/**
	 * daili_path
	 */
	public static final String		remote_file_server_username		= ResourceUtil.getValue(weixin, "remote_file_server_username");
	
	/**
	 * daili_path
	 */
	public static final String		remote_file_server_pwd		= ResourceUtil.getValue(weixin, "remote_file_server_pwd");
	
	/**
	 * daili_path
	 */
	public static final String		dest_file_path		= ResourceUtil.getValue(weixin, "dest_file_path");

}
