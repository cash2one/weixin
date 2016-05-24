package org.guili.ecshop.business.weixin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.guili.ecshop.business.weixin.bean.WeiXinHao;
import org.guili.ecshop.business.weixin.bean.WeiXinHaoVO;
import org.guili.ecshop.exception.RemoteServiceException;
import org.guili.ecshop.util.AbstractResponseHandler;
import org.guili.ecshop.util.FileTools;

/**
 * 微信 公众号spider
 * @author Administrator
 *
 */
public class WeiXinHaoSpider extends WeixinSpiderParent {
	private static Logger logger=Logger.getLogger(WeiXinHaoSpider.class);
	
	@Override
	public WeiXinSpiderType[] getSpiderType() {
		return new WeiXinSpiderType[]{WeiXinSpiderType.WEIXIN};
	}

	/**
	 * 解析url
	 * @param listurl
	 * @return
	 */
	@Override
	public List<String> resolveUrls(String listurl){
		List<String> oneUrlList=new ArrayList<String>();
		if(listurl==null ||listurl.isEmpty()){
			return oneUrlList;
		}
		oneUrlList.add(listurl);
		return oneUrlList;
	}
	
	@Override
	public WeiXinHaoVO weixinDetailHttp(String detailUrl, Boolean isHttpClient)
			throws URISyntaxException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WeiXinHaoVO createNetLcProduct(String pagexml, String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WeiXinHaoVO createNetLcProduct(String pagexml, String url,
			String foldername, String filename) {
		// TODO Auto-generated method stub
		return null;
	}

}
