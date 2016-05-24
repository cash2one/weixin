package org.guili.ecshop.bean.spider;

import java.util.List;

public class TopStoreInfo {
	private String storeTag;
	private List<TopStore> topStoreList;
	public String getStoreTag() {
		return storeTag;
	}
	public void setStoreTag(String storeTag) {
		this.storeTag = storeTag;
	}
	public List<TopStore> getTopStoreList() {
		return topStoreList;
	}
	public void setTopStoreList(List<TopStore> topStoreList) {
		this.topStoreList = topStoreList;
	}
}
