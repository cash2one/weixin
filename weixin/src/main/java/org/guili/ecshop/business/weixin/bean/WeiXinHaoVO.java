package org.guili.ecshop.business.weixin.bean;
import java.io.Serializable;
import java.util.Date;
/**
 * 公众号vo
 * @author guili
 */
public class WeiXinHaoVO {
	
	private WeiXinHao weiXinHao;		//文章对应公众号信息
	private String  	detailUrl;		//单个文章链接
	private String 		article_pre;		//文章预览
	private Integer  	readTimes;		//单个文章阅读次数
	
	public WeiXinHao getWeiXinHao() {
		return weiXinHao;
	}
	public void setWeiXinHao(WeiXinHao weiXinHao) {
		this.weiXinHao = weiXinHao;
	}
	public String getDetailUrl() {
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	public String getArticle_pre() {
		return article_pre;
	}
	public void setArticle_pre(String article_pre) {
		this.article_pre = article_pre;
	}
	public Integer getReadTimes() {
		return readTimes;
	}
	public void setReadTimes(Integer readTimes) {
		this.readTimes = readTimes;
	}
	
	
	
}
