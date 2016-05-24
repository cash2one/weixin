package org.guili.ecshop.bean.spider.yinhang;

import java.util.List;
/**
 * 招商银行记录集
 * @author guili
 *
 */
public class ZhaoHangRecords {
	private int result;
	private int totalRecord;
	private int totalPage;
	private List<ZhaoHangRecord> items;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<ZhaoHangRecord> getItems() {
		return items;
	}
	public void setItems(List<ZhaoHangRecord> items) {
		this.items = items;
	}
}
