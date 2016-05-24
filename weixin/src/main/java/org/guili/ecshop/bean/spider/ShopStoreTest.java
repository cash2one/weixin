package org.guili.ecshop.bean.spider;

import java.util.Date;

/**
 * 商家信息
 * @author guili
 */
public class ShopStoreTest {
	private long   id;		 	//店铺主键
	//店铺基本信息
	private String storeName;	//店铺名称
	private Date   version;		//版本号
	private Date   createTime;	//创建时间
	
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
	
	
}
