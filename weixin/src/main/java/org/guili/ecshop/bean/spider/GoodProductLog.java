package org.guili.ecshop.bean.spider;

import java.util.Date;

/**
 * 好产品
 * @ClassName:   GoodProduct 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author:      guilige 
 * @date         2014-1-2 下午4:32:47 
 */
public class GoodProductLog {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 0L;
	public static final String TYPE_LOW="1";
	public static final String TYPE_ZHE="2";
	public static final String TYPE_HD="3";
	//上下架状态
	public static final String SALE_INIT="0";//初始化
	public static final String SALE_ON="1";	 //上架
	public static final String SALE_OFF="2"; //下架
	
	private long   id;		 	//产品主键
	//商品基本信息
	private String productUrl;	//好产品链接
	private String productName; //好产品名称
	private Double salePrice;	//促销价格
	private Double price; 		//原始价格
	private Double zhekou;		//销售折扣
	private String goodsSellCount;//商品月销量
	private String goodsStock;	//商品库存
	private String goodsFee;	//商品邮费
	private String goodsImage;  //产品图片链接
	private String goodsAddress;//产品发货地址
	private String type;		//type 1 普通商品，2.秒杀商品  3.折扣产品
	private String miaoshatime; //当type=2时，该值有效。
	private String resourceStore;//来源商城
	private String productattr; //产品规格说明以&&分割(产品活动信息)
	private String productStore;//产品店铺名称(平台类产品)
	private String productDesc;	//产品描述(暂时未用)
	//商品附带信息	
	private String quanUrl;	 	//券链接
	private String quanDesc; 	//券描述
	private	String isSoldout;	//上架状态  0，初始化； 1 上架，2 卖光了。（如果是自动获取的商品自动上架）
	private String email;      	//发布者邮箱地址
	private String username;   	//推荐人
	private String shareReason;	//推荐原因
	private Date   createTime;	//创建时间
	private Date   shenheTime;	//审核时间
	//绑定商家和商家活动
	private long storeId;		//店铺id
	private long storeActivityId;//商家活动id
	private long categoryId ;  	//商家类别id
	private String shortUrl;  	//商品链接短链
	private String shortStore;	//商家链接短链
	private int 	pingjia;	  	//商品评价总数
	private String redirectUrl;	//taobao,tmall,juhuasuan产品跳转链接
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProductUrl() {
		return productUrl;
	}
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getZhekou() {
		return zhekou;
	}
	public void setZhekou(Double zhekou) {
		this.zhekou = zhekou;
	}
	public String getGoodsSellCount() {
		return goodsSellCount;
	}
	public void setGoodsSellCount(String goodsSellCount) {
		this.goodsSellCount = goodsSellCount;
	}
	public String getGoodsStock() {
		return goodsStock;
	}
	public void setGoodsStock(String goodsStock) {
		this.goodsStock = goodsStock;
	}
	public String getGoodsFee() {
		return goodsFee;
	}
	public void setGoodsFee(String goodsFee) {
		this.goodsFee = goodsFee;
	}
	public String getGoodsImage() {
		return goodsImage;
	}
	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}
	public String getGoodsAddress() {
		return goodsAddress;
	}
	public void setGoodsAddress(String goodsAddress) {
		this.goodsAddress = goodsAddress;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMiaoshatime() {
		return miaoshatime;
	}
	public void setMiaoshatime(String miaoshatime) {
		this.miaoshatime = miaoshatime;
	}
	public String getResourceStore() {
		return resourceStore;
	}
	public void setResourceStore(String resourceStore) {
		this.resourceStore = resourceStore;
	}
	public String getProductattr() {
		return productattr;
	}
	public void setProductattr(String productattr) {
		this.productattr = productattr;
	}
	public String getProductStore() {
		return productStore;
	}
	public void setProductStore(String productStore) {
		this.productStore = productStore;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getQuanUrl() {
		return quanUrl;
	}
	public void setQuanUrl(String quanUrl) {
		this.quanUrl = quanUrl;
	}
	public String getQuanDesc() {
		return quanDesc;
	}
	public void setQuanDesc(String quanDesc) {
		this.quanDesc = quanDesc;
	}
	public String getIsSoldout() {
		return isSoldout;
	}
	public void setIsSoldout(String isSoldout) {
		this.isSoldout = isSoldout;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getShareReason() {
		return shareReason;
	}
	public void setShareReason(String shareReason) {
		this.shareReason = shareReason;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getShenheTime() {
		return shenheTime;
	}
	public void setShenheTime(Date shenheTime) {
		this.shenheTime = shenheTime;
	}
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public long getStoreActivityId() {
		return storeActivityId;
	}
	public void setStoreActivityId(long storeActivityId) {
		this.storeActivityId = storeActivityId;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public String getShortStore() {
		return shortStore;
	}
	public void setShortStore(String shortStore) {
		this.shortStore = shortStore;
	}
	public int getPingjia() {
		return pingjia;
	}
	public void setPingjia(int pingjia) {
		this.pingjia = pingjia;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
}
