package org.guili.ecshop.bean.spider;

import java.util.Date;

/**
 * 购物经验
 * @author guili
 */
public class ShoppingExperience {
	private long   	id;		 	//主键
	private String 	title; 		//经验标题
	private String 	content; 	//经验内容
	private int 	useful; 		//经验有用
	private int 	unuseful; 		//经验没用
	private String 	productStore;//经验商家
	private long 	storeId;		//经验商家
	private long 	categoryId;	//经验类别
	private Date    createTime;	//创建时间
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUseful() {
		return useful;
	}
	public void setUseful(int useful) {
		this.useful = useful;
	}
	public int getUnuseful() {
		return unuseful;
	}
	public void setUnuseful(int unuseful) {
		this.unuseful = unuseful;
	}
	public String getProductStore() {
		return productStore;
	}
	public void setProductStore(String productStore) {
		this.productStore = productStore;
	}
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
