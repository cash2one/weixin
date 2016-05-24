package org.guili.ecshop.bean.spider;
import java.util.ArrayList;
import java.util.List;

/**
 * 互联网理财产品对象
 * @author guili
 */
public class NetLcProductVo {
	//商品基本信息
	private String productName;		//互联网理财产品名称
	private List<LcProduct> myRecentProduct=new ArrayList<LcProduct>();
	private double recentOneMonthIncome;	//最近一月收益率
	private double recentThreeMonthIncome;	//最近三月收益率
	private double recentSixMonthIncome;	//最近半年月收益率
	private double recentOneMonthPrice;		//最近一月收益
	private double recentThreeMonthPrice;	//最近三月收益
	private double recentSixMonthPrice;		//最近半年收益
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public List<LcProduct> getMyRecentProduct() {
		return myRecentProduct;
	}
	public void setMyRecentProduct(List<LcProduct> myRecentProduct) {
		this.myRecentProduct = myRecentProduct;
	}
	public double getRecentOneMonthIncome() {
		return recentOneMonthIncome;
	}
	public void setRecentOneMonthIncome(double recentOneMonthIncome) {
		this.recentOneMonthIncome = recentOneMonthIncome;
	}
	public double getRecentThreeMonthIncome() {
		return recentThreeMonthIncome;
	}
	public void setRecentThreeMonthIncome(double recentThreeMonthIncome) {
		this.recentThreeMonthIncome = recentThreeMonthIncome;
	}
	public double getRecentSixMonthIncome() {
		return recentSixMonthIncome;
	}
	public void setRecentSixMonthIncome(double recentSixMonthIncome) {
		this.recentSixMonthIncome = recentSixMonthIncome;
	}
	public double getRecentOneMonthPrice() {
		return recentOneMonthPrice;
	}
	public void setRecentOneMonthPrice(double recentOneMonthPrice) {
		this.recentOneMonthPrice = recentOneMonthPrice;
	}
	public double getRecentThreeMonthPrice() {
		return recentThreeMonthPrice;
	}
	public void setRecentThreeMonthPrice(double recentThreeMonthPrice) {
		this.recentThreeMonthPrice = recentThreeMonthPrice;
	}
	public double getRecentSixMonthPrice() {
		return recentSixMonthPrice;
	}
	public void setRecentSixMonthPrice(double recentSixMonthPrice) {
		this.recentSixMonthPrice = recentSixMonthPrice;
	}
	
}
