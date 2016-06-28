package org.guili.ecshop.business.weixin.bean;

/**
* 文章类别
* @author zhengdong.xiao
*
*/
public  enum ArticleType{
	ArticleType_NORMAL("NORMAL",1),
	ArticleType_SPECIAL("SPECIAL",2);
	
	String name;
	Integer value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
	ArticleType(String name,Integer value){
		this.name=name;
		this.value=value;
	}
	
}