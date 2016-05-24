package org.guili.ecshop.business.weixin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.guili.ecshop.business.weixin.bean.WeiXinArticle;
import org.guili.ecshop.business.weixin.bean.WeiXinHao;
import org.guili.ecshop.business.weixin.bean.WeiXinPackage;

/**
 * 商品服务接口
 * @ClassName:   IProductService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author:      guilige 
 * @date         2014-1-13 下午4:11:27 
 */
public interface IWeiXinSpiderService<E> {
	
	
	public WeiXinSpiderType [] getSpiderType();
	
	/**
	 * 抓取理财信息列表
	 * @param listurl 宠物信息列表url
	 * 	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public List<E> getWeixinListInfo(String listurl,Boolean isHttpClient,String hostAndPort) throws URISyntaxException, IOException;
	
	/**
	 * 获取单个p2p理财信息
	 * @param detailturl
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public E weixinDetailHttp(String detailUrl,Boolean isHttpClient)throws URISyntaxException, IOException;
	
	/**
	 * 抓取并保持信息
	 * @param listurl
	 */
	public void batCrawlWeixin(String listurl,Boolean isHttpClient);
	
	public E createNetLcProduct(String pagexml,String url) ;
	/**
	 * 抓取并持久化数据，返回需要存储的对象信息
	 * @param pagexml
	 * @param url
	 * @param foldername
	 * @param filename
	 * @return
	 */
	public E createNetLcProduct(String pagexml,String url,String foldername,String filename) ;
	
	public List<String> resolveUrls(String listurl)throws URISyntaxException, IOException;
	
	public WeiXinPackage resolveArticles(String listurl,Boolean isHttpClient)throws URISyntaxException, IOException;
	
	public  WeiXinArticle getOneArticle(String detailUrl,String hostAndPort);
	
	
}
