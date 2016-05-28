package org.guili.ecshop.business.weixin.bean;

import java.util.List;

/**
 * 分页数据
 * @author zhengdong.xiao
 *
 */
public class WeixinListVo {

	private List<WeiXinArticle> weiXinArticleList;
	private Long preId;
	private Long nextId;
	public List<WeiXinArticle> getWeiXinArticleList() {
		return weiXinArticleList;
	}
	public void setWeiXinArticleList(List<WeiXinArticle> weiXinArticleList) {
		this.weiXinArticleList = weiXinArticleList;
	}
	public Long getPreId() {
		return preId;
	}
	public void setPreId(Long preId) {
		this.preId = preId;
	}
	public Long getNextId() {
		return nextId;
	}
	public void setNextId(Long nextId) {
		this.nextId = nextId;
	}

	
	
}
