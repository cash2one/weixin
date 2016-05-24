package org.guili.ecshop.bean.spider;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 互联网理财产品对象
 * @author guili
 */
public class NetLcProduct {
	private long   id;		 	//产品主键
	//商品基本信息
	private String name;		//互联网理财产品名称
	private String preName; 	//关联理财产品名称
	private Date   dateTime;		//收益对应时间
	private Double incomeReal;	//每万份收益
	private Double income;		//七天预期收益
	private String buyUrl; 		//产品购买地址
	private String productDetail;//产品描述
	private int    costMoney;		 //产品最低购买金额	
	private Date   version;		//版本号
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreName() {
		return preName;
	}
	public void setPreName(String preName) {
		this.preName = preName;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public Double getIncomeReal() {
		return incomeReal;
	}
	public void setIncomeReal(Double incomeReal) {
		this.incomeReal = incomeReal;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	public String getBuyUrl() {
		return buyUrl;
	}
	public void setBuyUrl(String buyUrl) {
		this.buyUrl = buyUrl;
	}
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	public int getCostMoney() {
		return costMoney;
	}
	public void setCostMoney(int costMoney) {
		this.costMoney = costMoney;
	}
	public Date getVersion() {
		return version;
	}
	public void setVersion(Date version) {
		this.version = version;
	}

	//test
	public static void main(String[] args) {
		NetLcProduct lcProduct=new NetLcProduct();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
	}
}
