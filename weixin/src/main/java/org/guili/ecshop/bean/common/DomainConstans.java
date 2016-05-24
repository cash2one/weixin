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
public final class DomainConstans{

	/** 接口配置. */
	public static ResourceBundle	domain			= ResourceBundle.getBundle("config/domain");

	/** 当前项目配置的 css的域名. */
	//public static final String		DOMAIN_CSS		= ResourceUtil.getValue(domain, "domain.css");
	//ResourceUtil
	public static final String		DOMAIN_CSS		= ResourceUtil.getValue(domain, "domain.css");

	/** 当前项目配置的 js的域名. */
	//public static final String		DOMAIN_JS		= ResourceBundleUtil.getValue(domain, "domain.js");
	public static final String		DOMAIN_JS		= ResourceUtil.getValue(domain, "domain.js");

	/** 当前项目配置的 image 的域名. */
	//public static final String		DOMAIN_IMAGE	= ResourceBundleUtil.getValue(domain, "domain.image");
	public static final String		DOMAIN_IMAGE		= ResourceUtil.getValue(domain, "domain.css");

	/** 当前项目配置的 image 的域名(PDP). */
	//public static final String		DOMAIN_RESOURCE	= ResourceBundleUtil.getValue(domain, "domain.resource");
	public static final String		DOMAIN_RESOURCE		= ResourceUtil.getValue(domain, "domain.resource");

	/** 商城的网址，一般用于不同环境 第三方数据传递 比如微博等. */
	//public static final String		DOMAIN_STORE	= ResourceBundleUtil.getValue(domain, "domain.store");
//	public static final String		DOMAIN_STORE		= ResourceUtil.getValue(domain, "domain.store");
	public static final String		DOMAIN_STORE		= ResourceUtil.getValue(domain, "domain.store");
	public static final String		CONTEXTPATH		= ResourceUtil.getValue(domain, "domain.contextPath");
	
	/** 网站地图配置. */
	public static ResourceBundle	siteMapdomain			= ResourceBundle.getBundle("config/file_config");
	public static final String		SITEMAP_DOMAIN		= ResourceUtil.getValue(siteMapdomain, "sitemapurl");
	
	public static final Integer		LOG_BATCH_SIZE		= 10;
	
	
	public static ResourceBundle	mongodb			= ResourceBundle.getBundle("config/mongodb");
	public static final String mongodb_host=ResourceUtil.getValue(mongodb, "mongodb.host");
	public static final Integer mongodb_port=Integer.parseInt(ResourceUtil.getValue(mongodb, "mongodb.port"));
	public static final String mongodb_userName=ResourceUtil.getValue(mongodb, "mongodb.userName");
	public static final String mongodb_password=ResourceUtil.getValue(mongodb, "mongodb.password");
	public static final String mongodb_databaseName=ResourceUtil.getValue(mongodb, "mongodb.databaseName");
	public static final String mongodb_total_colls=ResourceUtil.getValue(mongodb, "mongodb.total.colls");
	public static final String mongodb_logininfo_collectionName=ResourceUtil.getValue(mongodb, "mongodb.logininfo.collectionName");
	//微信信息collection
	public static final String mongodb_article_collectionName=ResourceUtil.getValue(mongodb, "mongodb.article.collectionName");
	public static final String mongodb_weixinhao_collectionName=ResourceUtil.getValue(mongodb, "mongodb.weixinhao.collectionName");

}
