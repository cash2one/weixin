package org.guili.ecshop.bean.spider;
import java.util.Date;
import java.util.List;

/**
 * 理财统计报表
 * @author guili
 */
public class LcProductTongji {
	private static final long serialVersionUID = 1000001L;
	
	private long   id;		 	//产品主键
	//商品基本信息
	private String title;	  	//报表标题  
	private String newlicai;  	//新银行理财产品   name:aa,name1:bb,name2:cc; name:aa,name1:bb,name2:cc;
	private String saletop;	  	//在售银行理财产品利率榜
	private String salerisk;  	//在售银行理财产品低风险利率榜
	private String presaletop;	//预售银行理财产品利率榜
	private String presalerisk;	//预售银行理财产品低风险利率榜
	private String nettop;		//互联网理财产品利率榜
	private String p2ptop;		//人人借贷利率榜
	private Date   version;	  	//版本号
	
	//各各理财产品list
	private List<LcProduct> newlicaiList;   //最新理财产品
	private List<LcProduct> saletopList;	//高利率在售理财产品
	private List<LcProduct> saleriskList;	//低风险高利率理财产品
	private List<LcProduct> presaletopList;	//高利率待售理财产品
	private List<LcProduct> presaleriskList;//低风险高利率待售理财产品
	
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
	public String getNewlicai() {
		return newlicai;
	}
	public void setNewlicai(String newlicai) {
		this.newlicai = newlicai;
	}
	public String getSaletop() {
		return saletop;
	}
	public void setSaletop(String saletop) {
		this.saletop = saletop;
	}
	public String getSalerisk() {
		return salerisk;
	}
	public void setSalerisk(String salerisk) {
		this.salerisk = salerisk;
	}
	public String getPresaletop() {
		return presaletop;
	}
	public void setPresaletop(String presaletop) {
		this.presaletop = presaletop;
	}
	public String getPresalerisk() {
		return presalerisk;
	}
	public void setPresalerisk(String presalerisk) {
		this.presalerisk = presalerisk;
	}
	public String getNettop() {
		return nettop;
	}
	public void setNettop(String nettop) {
		this.nettop = nettop;
	}
	public String getP2ptop() {
		return p2ptop;
	}
	public void setP2ptop(String p2ptop) {
		this.p2ptop = p2ptop;
	}
	public Date getVersion() {
		return version;
	}
	public void setVersion(Date version) {
		this.version = version;
	}
	
	public List<LcProduct> getNewlicaiList() {
		return newlicaiList;
	}
	public void setNewlicaiList(List<LcProduct> newlicaiList) {
		this.newlicaiList = newlicaiList;
	}
	public List<LcProduct> getSaletopList() {
		return saletopList;
	}
	public void setSaletopList(List<LcProduct> saletopList) {
		this.saletopList = saletopList;
	}
	public List<LcProduct> getSaleriskList() {
		return saleriskList;
	}
	public void setSaleriskList(List<LcProduct> saleriskList) {
		this.saleriskList = saleriskList;
	}
	public List<LcProduct> getPresaletopList() {
		return presaletopList;
	}
	public void setPresaletopList(List<LcProduct> presaletopList) {
		this.presaletopList = presaletopList;
	}
	public List<LcProduct> getPresaleriskList() {
		return presaleriskList;
	}
	public void setPresaleriskList(List<LcProduct> presaleriskList) {
		this.presaleriskList = presaleriskList;
	}
	public static void main(String[] args) {
	}
}
