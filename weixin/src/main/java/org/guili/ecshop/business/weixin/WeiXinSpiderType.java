package org.guili.ecshop.business.weixin;

import org.apache.commons.lang.StringUtils;

/**
 * 爬虫类型
 * @author Administrator
 *
 */
public enum WeiXinSpiderType {
	//微信号抓取
	WEIXIN("WEIXIN", 0,"微信抓取"),
	WEIXIN_WEN("WEIXIN_WEN", 0,"微信文章抓取"),
	;
    // 成员变量
    private String name;
    private int companyId;//companyid
    private String companyName;

    // 构造方法
    private WeiXinSpiderType(String name, int companyId,String companyName) {
        this.name = name;
        this.companyId = companyId;
        this.companyName=companyName;
    }

    /**
     * 根据名称获取SpiderType
     * @param spiderType
     * @return
     */
    public static WeiXinSpiderType getSpiderTypeByName(String spiderType){
    	if(StringUtils.isEmpty(spiderType)){
    		return null;
    	}
    	spiderType=spiderType.toUpperCase();
    	if(spiderType.equals("WEIXIN")){
    		return WEIXIN;
    	} else{
    		return null;
    	}
    }
    // 普通方法
    public static String getName(int companyId) {
        for (WeiXinSpiderType c : WeiXinSpiderType.values()) {
            if (c.getCompanyId() == companyId) {
                return c.name;
            }
        }
        return null;
    }

	public String getName() {
		return name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
}
