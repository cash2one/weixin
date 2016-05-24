package org.guili.ecshop.business.weixin.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信包装类
 * @author zhengdong.xiao
 *
 */
public class WeiXinPackage {
	List<WeiXinArticle> weiXinArticleList=new ArrayList<WeiXinArticle>();
	WeiXinHao weiXinHao=new WeiXinHao();
	
	public List<WeiXinArticle> getWeiXinArticleList() {
		return weiXinArticleList;
	}
	public void setWeiXinArticleList(List<WeiXinArticle> weiXinArticleList) {
		this.weiXinArticleList = weiXinArticleList;
	}
	public WeiXinHao getWeiXinHao() {
		return weiXinHao;
	}
	public void setWeiXinHao(WeiXinHao weiXinHao) {
		this.weiXinHao = weiXinHao;
	}
	
	
}
