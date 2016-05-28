package org.guili.ecshop.business.weixin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.guili.ecshop.business.weixin.bean.WeiXinArticle;
import org.guili.ecshop.business.weixin.bean.WeiXinHao;
import org.guili.ecshop.business.weixin.bean.WeiXinHaoVO;
import org.guili.ecshop.business.weixin.bean.WeiXinPackage;
import org.guili.ecshop.util.DailiUtils;
import org.guili.ecshop.util.FileTools;
import org.guili.ecshop.util.SpiderRegex;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

/**
 * 微信 公众号spider
 * @author Administrator
 *
 */
public class WeiXinArticleSpider extends WeixinSpiderParent {
	private static Logger logger=Logger.getLogger(WeiXinArticleSpider.class);
	private static final String FOLDER_PATH="E:/weixin/detail/";
	private static final int clicktime=9;
	private static final int clicksleep=3000;
	
	@Override
	public WeiXinSpiderType[] getSpiderType() {
		return new WeiXinSpiderType[]{WeiXinSpiderType.WEIXIN_WEN};
	}

	/**
	 * 解析url
	 * @param listurl
	 * @return
	 */
	@Override
	public List<String> resolveUrls(String listurl)throws URISyntaxException, IOException{
		List<String> oneUrlList=new ArrayList<String>();
//        //获取总页数
////		HttpComponent httpComponent=new HttpComponent();
////		//设置代理服务器
////		String proxyhost="117.185.124.74";
////		int proxyport=8080;
////		httpComponent.setProxy(proxyhost, proxyport);
////		HttpGet httpGet = new HttpGet(listurl);
////		//设置user-Agent
////		httpGet.addHeader("User-Agent", USER_AGENT);
////		httpGet.addHeader("Content-Type", "text/html;charset=UTF-8"); 
////		try {
////			String pagexml=httpComponent.execute(httpGet, pageHandler);
////		     oneUrlList.addAll(this.build(pagexml));
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
		return oneUrlList;
	}
	
	/**
	 * 解析文章
	 * @param listurl
	 * @param isHttpClient
	 * @return
	 */
	@Override
	public WeiXinPackage resolveArticles(String detailUrl,Boolean isHttpClient)throws URISyntaxException, IOException{
		//设置代理服务器
        DailiUtils dailiUtils=new DailiUtils();
        String ip_port=dailiUtils.getRandomIp();
        Map<String, String> randomcookie=dailiUtils.cookies;
        String ip=ip_port.split(":")[0];
    	String port=ip_port.split(":")[1];
		//多url的情况
		WebClient wc = new WebClient(BrowserVersion.FIREFOX_24,ip,Integer.parseInt(port));
//    	ProxyConfig proxyConfig=new ProxyConfig(ip, Integer.parseInt(port));
//        wc.getOptions().setProxyConfig(proxyConfig);
//        设置cookie
        Cookie cookie=new Cookie(".sogou.com", "SUID", randomcookie.get("SUID"));
        Cookie cookie1=new Cookie(".sogou.com", "SUV", randomcookie.get("SUV"));
        Cookie cookie2=new Cookie(".sogou.com", "SNUID", randomcookie.get("SNUID"));
        wc.getCookieManager().addCookie(cookie);
        wc.getCookieManager().addCookie(cookie2);
        wc.getCookieManager().addCookie(cookie1);
		//代理服务器设置
//		WebClient webClient = new WebClient(BrowserVersion.CHROME,"http://127.0.0.1",8087)
        wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true  
        wc.getOptions().setCssEnabled(false); //禁用css支持  
        wc.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常  
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
        wc.getOptions().setTimeout(5000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待
        wc.setAjaxController(new NicelyResynchronizingAjaxController());
        WebRequest innnerRequest = new WebRequest(new URL(detailUrl), HttpMethod.GET);
		innnerRequest.setCharset("utf-8"); 
        HtmlPage page = wc.getPage(innnerRequest);
      //点击事件
        for(int i=0;i<clicktime;i++){
        	 HtmlElement element= page.getHtmlElementById("wxmore");
        	 page =element.click();
        	 try {
				Thread.sleep(clicksleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        String pageXml=page.asXml();
		return createWeixinArticle(pageXml,detailUrl);
	}
	
	
	public WeiXinPackage createWeixinArticle(String pagexml,String url) {
//		FileTools.write("E:/weixinspider/weixin_article_list3.txt", pagexml);
//		try {
//			pagexml=FileTools.read("E:/weixin/weixin_article_list3.txt");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		if(pagexml.equals("")){
			return null;
		}
		WeiXinPackage weiXinPackage=new WeiXinPackage();
		List<WeiXinArticle> weiXinArticleList=new ArrayList<WeiXinArticle>();
		Document doc = Jsoup.parse(pagexml);
//		Element weixin_public=doc.select(".weixin-public").first();
		Elements articles=doc.select(".txt-box");
		Element haoinfo=articles.first();
		String weixinname=haoinfo.select("#weixinname").html().trim();
		String weixinhao=haoinfo.select("h4").text().trim();
		weixinhao=weixinhao.replace("微信号：", "").trim();
		String description=haoinfo.select(".sp-txt").get(0).html().trim();
		String renzheng="";
		if(haoinfo.select(".sp-txt").size()>1){
			renzheng=haoinfo.select(".sp-txt").get(1).html().trim();
		}
		//查询微信公众号信息
		WeiXinHao weixinObj=weiXinService.selectWeiXinByhash(new Long(weixinname.hashCode()));
		if(weixinObj==null){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		Calendar now=DateUtils.toCalendar(new Date());
		String year=now.get(Calendar.YEAR)+"年";
		String month=((now.get(Calendar.MONTH)+1)>10?(now.get(Calendar.MONTH)+1):"0"+(now.get(Calendar.MONTH)+1)) +"月";
		String day=now.get(Calendar.DATE)+"日";
		
		for(int i=1;i<articles.size();i++){
			Element article=articles.get(i);
			String detailurl=article.select("h4").select("a").attr("href");
			String title=article.select("h4").text().trim();
			String desc=article.select("p").get(0).text().trim();
			String articletime=article.select(".s-p").get(0).text().trim();
			WeiXinArticle weiXinArticle=new WeiXinArticle();
			weiXinArticle.setCreateTime(new Date());
			String dateformat="";
			if(articletime.contains("月") && articletime.contains("日")){
				dateformat=year+articletime+" "+"00:00";
			}else{
				dateformat=year+month+day+" "+articletime;
			}
			weiXinArticle.setTitle(title);
			weiXinArticle.setTitlehash(title.hashCode());
			weiXinArticle.setDescription(desc);
			weiXinArticle.setHao_id(weixinObj.getId());
			weiXinArticle.setTag_id(weixinObj.getTag_id());
			weiXinArticle.setHao_name(weixinhao);
			weiXinArticle.setStatus(1);
			weiXinArticle.setOpenid(weixinObj.getTouxiang());
			weiXinArticle.setHao_desc(description);
//			weiXinArticle.setWeixin_url("http://weixin.sogou.com"+detailurl);
			weiXinArticle.setRead_times(0);
			weiXinArticleList.add(weiXinArticle);
		}
		//倒序
		List<WeiXinArticle> newWeiXinArticleList=new ArrayList<WeiXinArticle>();
		if(weiXinArticleList!=null && weiXinArticleList.size()>0){
			for(int i=weiXinArticleList.size()-1;i>=0;i--){
				newWeiXinArticleList.add(weiXinArticleList.get(i));
			}
		}
		
		if(StringUtils.isEmpty(weixinObj.getWeixin_id())){
			WeiXinHao weixin=new WeiXinHao();
			weixin.setWeixin_id(weixinhao);
			weixin.setDescription(description);
			weixin.setId(weixinObj.getId());
			weiXinPackage.setWeiXinHao(weixin);
		}
		//组装对象
		weiXinPackage.setWeiXinArticleList(newWeiXinArticleList);
		
		return weiXinPackage;
	}
	
	@Override
	public WeiXinHaoVO createNetLcProduct(String pagexml, String url,
			String foldername, String filename) {
//		FileTools.write("E:/weixinspider/test_list3.txt", pagexml);
//		try {
//			pagexml=FileTools.read("E:/weixinspider/test_list3.txt");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		if(pagexml.equals("")){
			return null;
		}
		
		Document doc = Jsoup.parse(pagexml);
		Element cover=doc.select("#js_cover").first();
		if(cover!=null){
			Element media=doc.select("#media").select("script").first();
			String jshtml=media.html();
			if(StringUtils.isNotEmpty(jshtml)){
				String datasrc=jshtml.substring(jshtml.indexOf("=")+3,jshtml.indexOf(";")-1);
				cover.attr("data-src", datasrc);
			}
			cover.removeAttr("onerror").removeAttr("src");
			media.remove();
		}
		doc.select("link").removeAttr("href");
		Element node=doc.select("#js_article").first();
		StringBuffer sb=new StringBuffer("");
		
		sb.append(node.html());
		sb.append("<script type=\"text/javascript\" src=\"${domain_js}/js/weixin/common.js?${version_js}\"></script>");
		sb.append("<script type=\"text/javascript\" src=\"${domain_js}/js/weixin/reimg.js?${version_js}\"></script>");
		//存储文件
		this.saveFile(filename, FOLDER_PATH+foldername+"/",sb.toString() );
		return null;
	}
	/**
	 * 解析one url
	 * @param detailurl
	 * @return
	 */
	public void resolveOneUrl(String detailurl,String foldername,String filename)throws URISyntaxException, IOException{
		//多url的情况
		WebClient wc = new WebClient(BrowserVersion.FIREFOX_24);
		//代理服务器设置
//		WebClient webClient = new WebClient(BrowserVersion.CHROME,"http://127.0.0.1",8087)
        wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true  
        wc.getOptions().setCssEnabled(false); //禁用css支持  
        wc.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常  
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
        wc.getOptions().setTimeout(5000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待  
        //设置代理服务器
        DailiUtils dailiUtils=new DailiUtils();
        String ip_port="117.177.243.43:81";
        String ip=ip_port.split(":")[0];
    	String port=ip_port.split(":")[1];
    	ProxyConfig proxyConfig=new ProxyConfig(ip, Integer.parseInt(port));
        wc.getOptions().setProxyConfig(proxyConfig);
      //设置cookie
//        Cookie cookie=new Cookie("weixin.sogou.com", "SUID", "FB1D83752708930A0000000055E33BB2");
//        Cookie cookie1=new Cookie("weixin.sogou.com", "SUV", "00B3640975831DFB55E33BB3499F2964");
//        Cookie cookie2=new Cookie("weixin.sogou.com", "SNUID", "4DAB35C2B6B2A83D3B182B0FB74F1518");
//        wc.getCookieManager().addCookie(cookie);
//        wc.getCookieManager().addCookie(cookie2);
//        wc.getCookieManager().addCookie(cookie1);
        
        wc.setAjaxController(new NicelyResynchronizingAjaxController());
        WebRequest innnerRequest = new WebRequest(new URL(detailurl), HttpMethod.GET);
		innnerRequest.setCharset("utf-8"); 
        HtmlPage innerpage = wc.getPage(innnerRequest);
        String pagexml = innerpage.asXml();
//		HttpComponent httpComponent=new HttpComponent();
//		//设置代理服务器
//		String proxyhost="117.185.124.74";
//		int proxyport=8080;
//		httpComponent.setProxy(proxyhost, proxyport);
//		HttpGet httpGet = new HttpGet(listurl);
//		//设置user-Agent
//		httpGet.addHeader("User-Agent", USER_AGENT);
//		httpGet.addHeader("Content-Type", "text/html;charset=UTF-8"); 
//		try {
//			String pagexml=httpComponent.execute(httpGet, pageHandler);
//		     oneUrlList.addAll(this.build(pagexml));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
        //解析页面，并存储
		this.createNetLcProduct(pagexml, detailurl, foldername, filename);
	}
	
//	
//	@Override
//	public WeiXinHao createNetLcProduct(String pagexml,String url) {
////		FileTools.write("E:/weixinspider/test_list1.txt", pagexml);
//		try {
//			pagexml=FileTools.read("E:/weixinspider/test_detail4.txt");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		if(pagexml.equals("")){
//			return null;
//		}
//		
//		Document doc = Jsoup.parse(pagexml);
//		Element cover=doc.select("#js_cover").first();
//		if(cover!=null){
//			Element media=doc.select("#media").select("script").first();
//			String jshtml=media.html();
//			if(StringUtils.isNotEmpty(jshtml)){
//				String datasrc=jshtml.substring(jshtml.indexOf("=")+3,jshtml.indexOf(";")-1);
//				cover.attr("data-src", datasrc);
//			}
//			cover.removeAttr("onerror").removeAttr("src");
//			media.remove();
//		}
//		doc.select("link").removeAttr("href");
//		Element node=doc.select("#js_article").first();
//		StringBuffer sb=new StringBuffer("");
//		
//		sb.append(node.html());
////		<script type="text/javascript" src="${domain_js}/js/weixin/common.js?${version_js}"></script>
////		<script type="text/javascript" src="${domain_js}/js/weixin/reimg.js?${version_js}"></script>
//		sb.append("<script type=\"text/javascript\" src=\"${domain_js}/js/weixin/common.js?${version_js}\"></script>");
//		sb.append("<script type=\"text/javascript\" src=\"${domain_js}/js/weixin/reimg.js?${version_js}\"></script>");
//		
////		makeImg("E:/weixinspider/detail",pagexml)
//		this.saveFile("test_detail5.jsp", "E:/weixin/detail/",sb.toString() );
//		return null;
//	}
	

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

	
	public static void main(String[] args) {
		WeiXinArticleSpider weiXinHaoSpider=new WeiXinArticleSpider();
//		String detailUrl="http://weixin.sogou.com/pcindex/pc/pc_0/10.html";
//		String detailUrl="http://mp.weixin.qq.com/s?src=3&timestamp=1463064773&ver=1&signature=CLJTrOMG75JySpILCxOAvrTCh6wReh6UqmJT8DuI7TdihMgjWOwn7xoDBoTzeEMFBM3684TBLMzKPcCAElHDcnC2hY2U1-SAG3a1owH6Q9EtwQgQ1I*jEFpWF1IlKD-b*SR5c8xaAFygPdyBHCr1KNSZh9SNbwExTLs9OIWI5YM=";
//		weiXinHaoSpider.getOneArticle(detailUrl,"");
//		weiXinHaoSpider.createArticle(" ");
		
//		weiXinHaoSpider.weixinDetailHttpMore(detailUrl);
//		weiXinHaoSpider.createWeixins("", detailUrl);
		
		//正则取数字
//		SpiderRegex spiderRegex=new SpiderRegex();
//		String html="阅读&nbsp;129&nbsp;&nbsp;&nbsp;";
//		String spanRegex = "阅读&nbsp;(.*?)&nbsp;";
//		String[] results=spiderRegex.htmlregex(html, spanRegex, false);
		
	}

}
