package org.guili.ecshop.bean.spider;

import java.util.Date;

/**
 * 商家信息
 * @author guili
 */
public class ShopStore {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 12L;
	public static final String STORETYPE_TMALL="1";	//天猫
	public static final String STORETYPE_NORMAL="2";//自营
	private long   id;		 	//店铺主键
	//店铺基本信息
	private String storeName;	//店铺名称
	private String ename; 		//店铺短名称
	private String storeUrl;	//店铺url
	private String storeType; 	//店铺类型
	private String storeDesc;	//店铺描述
	private Date   version;		//版本号
	private Date   createTime;	//创建时间
	private int    storeorder;	//店铺排序
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getStoreUrl() {
		return storeUrl;
	}
	public void setStoreUrl(String storeUrl) {
		this.storeUrl = storeUrl;
	}
	public String getStoreType() {
		return storeType;
	}
	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}
	public String getStoreDesc() {
		return storeDesc;
	}
	public void setStoreDesc(String storeDesc) {
		this.storeDesc = storeDesc;
	}
	public Date getVersion() {
		return version;
	}
	public void setVersion(Date version) {
		this.version = version;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getStoreorder() {
		return storeorder;
	}
	public void setStoreorder(int storeorder) {
		this.storeorder = storeorder;
	}
	
}
