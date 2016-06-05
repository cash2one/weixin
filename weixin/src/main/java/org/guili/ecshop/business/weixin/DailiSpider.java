package org.guili.ecshop.business.weixin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.guili.ecshop.bean.common.WeixinConstans;
import org.guili.ecshop.business.weixin.bean.WeiXinHao;
import org.guili.ecshop.business.weixin.bean.WeiXinHaoVO;
import org.guili.ecshop.exception.RemoteServiceException;
import org.guili.ecshop.util.AbstractResponseHandler;
import org.guili.ecshop.util.FileTools;
import org.guili.ecshop.util.HttpComponent;
import org.guili.ecshop.util.SpiderRegex;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

/**
 * 抓取可用的代理服务器
 * @author zhengdong.xiao
 *
 */
public class DailiSpider {
	
	private static Logger logger=Logger.getLogger(DailiSpider.class);
	protected static final Integer TIME_OUT=2500;
	public static final String USER_AGENT="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36";
	
	private static final String PROXY_IPS="E:/weixinspider/daili_ips_final.txt";
	private static final String REMOVE_PROXY_IPS="E:/weixinspider/daili_ips_remove.txt";
	//sourceIPs
	private static final String SOURCE_PROXY_IPS="E:/weixinspider/daili_ips.txt";
	
	/**
	 * 抓取公众账号主体时使用
	 * @param detailUrl
	 * @return
	 */
	public void weixinDetailHttpMore(String detailUrl){
		String pagexml="";
		//如果要求httpclient处理
		HttpComponent httpComponent=new HttpComponent();
		//设置代理服务器
		HttpGet httpGet = new HttpGet(detailUrl);
		//设置user-Agent
		httpGet.addHeader("User-Agent", USER_AGENT);
		httpGet.addHeader("Content-Type", "text/html;charset=UTF-8"); 
		try {
			 pagexml=httpComponent.execute(httpGet, pageHandler);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//容错
		if(StringUtils.isEmpty(pagexml)){
			return ;
		}
		
//		FileTools.write("E:/weixinspider/daili_list.txt", pagexml);
//		return null;
		this.createWeixins(pagexml, detailUrl);
	}
	
	
public void createWeixins(String pagexml,String url){
		
//		try {
//			pagexml=FileTools.read("E:/weixinspider/daili_list.txt");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		if(pagexml.equals("")){
			return ;
		}
		Document doc = Jsoup.parse(pagexml);
		Elements nodes=doc.select("#ip_list").select("tr");
		//找到ip port 写文件
		for(int i=0;i<nodes.size();i++){
			if(0==i){
				continue;
			}
			Element node=nodes.get(i);
			String ip =node.select("td").get(1).html();
			String port =node.select("td").get(2).html();
			System.out.println("ip:"+ip+":port:"+port);
//			FileTools.write("E:/weixinspider/daili_ips.txt", ip+":"+port);
			FileTools.appendToFile(SOURCE_PROXY_IPS, ip+":"+port+";");
		}
	}

	
	//处理响应handler
	public final PageHandler pageHandler = new PageHandler();
	private class PageHandler extends AbstractResponseHandler<String> {
        @Override
        public String handle(HttpEntity entity) throws IOException {
        	String charset = "UTF-8";
            String entityStr = EntityUtils.toString(entity,charset);

            if (StringUtils.isBlank(entityStr)) {
                throw new RemoteServiceException();
            }
            return entityStr;
        }
	}
	
	public void validateips(){
		//查询所有ip
		String allips="";
		try {
//			allips=FileTools.read("E:/weixinspider/daili_ips.txt");
			allips=FileTools.read(SOURCE_PROXY_IPS);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//取出ips和端口
		String[] ipAndPorts=allips.split(";");
		if(ipAndPorts==null || ipAndPorts.length==0){
			return;
		}
		
		for(String ipAndport:ipAndPorts){
			String[] ips=ipAndport.split(":");
			if(ips==null || ips.length==0){
				System.out.println("failed:"+ipAndport);
				continue;
			}
			this.getUrlTest(ips[0], Integer.parseInt(ips[1]));
		}
		
	}
	
	public void removeMoreips(){
		//查询所有ip
		String allips="";
		try {
//			allips=FileTools.read("E:/weixinspider/daili_ips.txt");
			allips=FileTools.read(SOURCE_PROXY_IPS);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//取出ips和端口
		String[] ipAndPorts=allips.split(";");
		if(ipAndPorts==null || ipAndPorts.length==0){
			return;
		}
		
		Set<String> keys=new HashSet<String>();
		for(String ipAndport:ipAndPorts){
			keys.add(ipAndport);
		}
		for(String key:keys){
			FileTools.appendToFile(PROXY_IPS, key+";");
		}
		
	}
	
	
	
	public void getUrlTest(String host,Integer port){
		String pagexml="";
		//如果要求httpclient处理
		HttpComponent httpComponent=new HttpComponent();
		httpComponent.setProxy(host, port);
		//设置代理服务器
		HttpGet httpGet = new HttpGet("http://www.licaime.com/help/lclianxi.htm");
		//设置user-Agent
		httpGet.addHeader("User-Agent", USER_AGENT);
		httpGet.addHeader("Content-Type", "text/html;charset=UTF-8"); 
		try {
			 pagexml=httpComponent.execute(httpGet, pageHandler);
		} catch (IOException e) {
			e.printStackTrace();
			//加入异常proxy ip
			FileTools.appendToFile(REMOVE_PROXY_IPS, host+":"+port+";");
			return;
		}
		//容错
		if(StringUtils.isEmpty(pagexml)){
			return ;
		}
		System.out.println("success!"+host);
		FileTools.appendToFile(PROXY_IPS, host+":"+port+";");
	}
	
	

	public static void main(String[] args) {
		DailiSpider weiXinHaoSpider=new DailiSpider();
//		for(int i=1;i<=4;i++){
//			String detailUrl="http://www.xicidaili.com/nn/"+i;
//			weiXinHaoSpider.weixinDetailHttpMore(detailUrl);
//		}
		weiXinHaoSpider.validateips();
//		weiXinHaoSpider.removeMoreips();
//		weiXinHaoSpider.createWeixins("", detailUrl);
//		weiXinHaoSpider.getUrlTest("61.135.217.14", 80);
		
//		BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>();
//		blockingQueue.add("aa");
//		blockingQueue.add("bb");
//		blockingQueue.add("cc");
//		blockingQueue.add("dd");
//		
//		for(int i=0;i<=10;i++){
//				String key =blockingQueue.poll();
//				System.out.println(key);
//				blockingQueue.add(key);
//				System.out.println(blockingQueue.size());
//		}
//		System.out.println("5745d707ffb2266fddeb0157".length());
		
	}
	
}
