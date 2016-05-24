package org.guili.ecshop.bean.spider;

import java.util.Date;

/**
 * 商家活动信息
 * @author guili
 */
public class StoreActivity {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 12L;
	
	private long   id;		 		//店铺活动主键
	//店铺活动基本信息
	private long storeId;			//店铺id
	private String storeName; 		//店铺名称
	private String activityName;	//活动名称
	private String activityUrl; 	//活动链接
	private String activityImageUrl;//活动图片路径
	private String activityDesc;	//活动描述
	private Date   createTime;		//创建时间
	private Date   version;			//版本号
	private String actShortName;	//活动名称短链接
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityUrl() {
		return activityUrl;
	}
	public void setActivityUrl(String activityUrl) {
		this.activityUrl = activityUrl;
	}
	public String getActivityImageUrl() {
		return activityImageUrl;
	}
	public void setActivityImageUrl(String activityImageUrl) {
		this.activityImageUrl = activityImageUrl;
	}
	public String getActivityDesc() {
		return activityDesc;
	}
	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getVersion() {
		return version;
	}
	public void setVersion(Date version) {
		this.version = version;
	}
	public String getActShortName() {
		return actShortName;
	}
	public void setActShortName(String actShortName) {
		this.actShortName = actShortName;
	}
	
}
