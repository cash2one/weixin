package org.guili.ecshop.business.weixin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.guili.ecshop.util.FileTools;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 和讯抓取
 * @author Administrator
 *
 */
public class P2peyeSpider  {

	private static Logger logger=Logger.getLogger(P2peyeSpider.class);
	private static final int TIME_OUT=5000;
	private static final int pagenum=10;
	

	/**
	 * 解析列表url
	 * @param listurl
	 * @return
	 */
	public List<String> resolveOneUrl(String listurl)throws URISyntaxException, IOException{
		List<String> oneUrlList=new ArrayList<String>();
		if(listurl==null ||listurl.isEmpty()){
			return oneUrlList;
		}
		//多url的情况
		WebClient wc = new WebClient(BrowserVersion.INTERNET_EXPLORER_11);
        wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true  
        wc.getOptions().setCssEnabled(false); //禁用css支持  
        wc.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常  
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
        wc.getOptions().setTimeout(TIME_OUT); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待  
        wc.setAjaxController(new NicelyResynchronizingAjaxController());
        WebRequest innnerRequest = new WebRequest(new URL(listurl), HttpMethod.GET);
		innnerRequest.setCharset("utf-8"); 
        HtmlPage page = wc.getPage(innnerRequest);
        //模拟点击
//        HtmlAnchor anchor = (HtmlAnchor) page.getByXPath("//*[@id=\"look-more\"]").get(0);  
//        HtmlPage hpm = anchor.click();  
        
//        ScriptResult sr=page.executeJavaScript("window.scrollBy(0,600)");
//        wc.waitForBackgroundJavaScript(10000);
//        HtmlPage hpm=(HtmlPage)sr.getNewPage();
        String pagexml = page.asXml();
        //解析列表页
        FileTools.write("E:/weixinspider/weixinlist2.txt", pagexml);
//        oneUrlList.addAll(this.build(pagexml));
        wc.closeAllWindows();
		return oneUrlList;
	}
	
	public List<String> build(String pagexml){
		if(pagexml.equals("")){
			return null;
		}
//		try {
//			pagexml=FileTools.read("E:/p2pwenda/p2peye_p2p_list.txt");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		List<String> investUrls=new ArrayList<String>();
		Document doc = Jsoup.parse(pagexml);
		Element topnews=doc.select(".mod-listboxp").first();
		Elements elementsa=topnews.select(".mod-news");
		if(elementsa!=null && !elementsa.isEmpty()){
			for(Element invest:elementsa){
				System.out.println("detail url-->"+invest.select("a").get(0).attr("href"));
				String detailurl=invest.select("a").get(0).attr("href");
				investUrls.add(detailurl);
			}
		}
		return investUrls;
	}
	
	public static void main(String[] args) {
		P2peyeSpider yicaiSpider=new P2peyeSpider();
//		yicaiSpider.resolveUrls("http://www.p2peye.com/news/");
//		yicaiSpider.build("https://www.jimubox.com/Project/ProjectSet/jk/2015-06-06_13:00");
		try {
//			yicaiSpider.petDetailHttp("http://www.p2peye.com/article-7862-1.html");
			yicaiSpider.resolveOneUrl("http://weixin.sogou.com/pcindex/pc/pc_0/1.html");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		try {
//			String temp=FileTools.read("E:/weixinspider/detail.txt");
//			System.out.println(temp.length());
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		yicaiSpider.createNetLcProduct("1","http://www.yicai.com/news/2015/07/4662819.html");
	}
}
