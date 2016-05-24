package org.guili.ecshop.business.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.guili.ecshop.business.weixin.bean.WeiXinArticle;
import org.guili.ecshop.business.weixin.bean.WeiXinHao;
import org.guili.ecshop.util.common.XmlUtils;

/**
 * sitemap服务
 * @author zhengdong.xiao
 *
 */
public class WeixinSiteMapService {
	
	private static Logger logger=Logger.getLogger(WeixinSpiderParent.class);
	private IWeiXinService weiXinService;
	private IWeiXinArticleService weiXinArticleService;
	
	private static final String url = "http://data.zz.baidu.com/urls?site=www.51maogou.com&token=RdYcCBZRzcdB9wUO";//网站的服务器连接
	
	private static final int COUNT=10;
	private static final int ONE_PAGE=15;
	/**
	 * 实时推送百度
	 */
	public void onlinePush(){
		
		//带推送数据
		List<String> param =new ArrayList<String>();
		List<WeiXinHao> weixinHaos=weiXinService.selectNewHaosInMongo(COUNT);
		if(weixinHaos==null || weixinHaos.isEmpty()){
			return ;
		}
		for(WeiXinHao weixinHao:weixinHaos){
			if(weixinHao.getWeixin_id()==null || "".equals(weixinHao.getWeixin_id())){
				continue;
			}
			param.add("http://www.51maogou.com/weixin/one-"+weixinHao.getWeixin_id()+"-1.htm");
		}
		
		//2.微信文章
		//前一小时的文章
		Date preOneHour=new Date(System.currentTimeMillis()-3600000);
		List<WeiXinArticle>  weiXinArticles = weiXinArticleService.selectNewArticleInMongoByTag(preOneHour, 0, COUNT);
		if(weiXinArticles==null || weiXinArticles.size()==0){
			return;
		}
		for(WeiXinArticle weiXinArticle:weiXinArticles){
			param.add("http://www.51maogou.com/weixin/detail-"+weiXinArticle.getTitlehash()+".htm");
		}
		 
		 String json = post(url, param);//执行推送方法  
	     System.out.println("结果是:"+json);
	}
	
	
	/**
	 * 构建sitemap
	 * @param filePath
	 */
	public static  void buildSiteMap(String filePath){
		Document document=XmlUtils.createSitemap();
    	Element urlsetElement = document.addElement("urlset", "http://www.sitemaps.org/schemas/sitemap/0.9");
    	urlsetElement.addNamespace("xsi","http://www.w3.org/2001/XMLSchema-instance");
    	urlsetElement.addAttribute("xsi:schemaLocation","http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd");
    	XmlUtils.addOneElement(urlsetElement, "http://www.51maogou.com/", XmlUtils.HOURLYFREQ);
    	//页码信息
    	for(int i=1;i<20;i++){
    		XmlUtils.addOneElement(urlsetElement, "http://www.51maogou.com/weixin/list-"+i+"--1.htm", XmlUtils.HOURLYFREQ);
    		for(int j=2;j<=20;j++){
    			XmlUtils.addOneElement(urlsetElement, "http://www.51maogou.com/weixin/list-"+i+"--"+j+".htm", XmlUtils.WEEKLYFREQ);
    		}
    	}
    	XmlUtils.addOneElement(urlsetElement, "http://www.51maogou.com/weixin/list-20--1.htm", XmlUtils.HOURLYFREQ);
	    //写入文件
	    XmlUtils.generateXmlFile(document,filePath);
	}
	
	/**
	 * 每天生成一个微信号和微信文章文件
	 * @param filePath
	 */
	public  void buildHaoSiteMap(String filePath,Integer pagenum){
		Document document=XmlUtils.createSitemap();
    	Element urlsetElement = document.addElement("urlset", "http://www.sitemaps.org/schemas/sitemap/0.9");
    	urlsetElement.addNamespace("xsi","http://www.w3.org/2001/XMLSchema-instance");
    	urlsetElement.addAttribute("xsi:schemaLocation","http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd");
    	//页码信息
    	for(int i=1;i<=20;i++){
    		//循环取文章
    		for(int j=1;j<pagenum;j++){
    			List<WeiXinArticle> weiXinArticleList=weiXinArticleService.selectPageArticleInMongoByTag((long)i, null, (j-1)*ONE_PAGE, ONE_PAGE);
        		//微信公众号
        		if(weiXinArticleList==null || weiXinArticleList.isEmpty()){
        			continue;
        		}
        		//拼链接
        		for(WeiXinArticle weiXinArticle:weiXinArticleList){
        			//微信号
        			XmlUtils.addOneElement(urlsetElement, "http://www.51maogou.com/weixin/one-"+weiXinArticle.getWeixin_hao()+"-1.htm", XmlUtils.WEEKLYFREQ);
        			//微信文章
        			XmlUtils.addOneElement(urlsetElement, "http://www.51maogou.com/weixin/detail-"+weiXinArticle.getTitlehash()+".htm", XmlUtils.WEEKLYFREQ);
        		}
        		System.out.println("第"+i+"个标签,第"+j+"页");
        		try {
					Thread.currentThread().sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    	}
	    //写入文件
	    XmlUtils.generateXmlFile(document,filePath);
	}
	
	
	/** 
     * 百度链接实时推送 
     * @param PostUrl 
     * @param Parameters 
     * @return 
     */  
    public static String post(String PostUrl,List<String> Parameters){
        if(null == PostUrl || null == Parameters || Parameters.size() ==0){  
            return null;  
        }  
        String result="";  
        PrintWriter out=null;  
        BufferedReader in=null;  
        try {  
            //建立URL之间的连接  
            URLConnection conn=new URL(PostUrl).openConnection();  
            //设置通用的请求属性  
            conn.setRequestProperty("Host","data.zz.baidu.com");  
            conn.setRequestProperty("User-Agent", "curl/7.12.1");  
            conn.setRequestProperty("Content-Length", "83");  
            conn.setRequestProperty("Content-Type", "text/plain"); 
            //发送POST请求必须设置如下两行  
            conn.setDoInput(true);  
            conn.setDoOutput(true);  
            //获取conn对应的输出流  
            out=new PrintWriter(conn.getOutputStream());  
            //发送请求参数  
            String param = "";
            for(String s : Parameters){  
                param += s+"\n";  
            }
            out.print(param.trim());  
            //进行输出流的缓冲  
            out.flush();  
            //通过BufferedReader输入流来读取Url的响应  
            in=new BufferedReader(new InputStreamReader(conn.getInputStream()));  
            String line;  
            while((line=in.readLine())!= null){  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送post请求出现异常！"+e);  
            e.printStackTrace();  
        } finally{  
            try{  
                if(out != null){  
                    out.close();  
                }  
                if(in!= null){  
                    in.close();  
                }  
            }catch(IOException ex){  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }  
	
	/*****************getter and setter***************/
	public IWeiXinService getWeiXinService() {
		return weiXinService;
	}

	public void setWeiXinService(IWeiXinService weiXinService) {
		this.weiXinService = weiXinService;
	}


	public IWeiXinArticleService getWeiXinArticleService() {
		return weiXinArticleService;
	}

	public void setWeiXinArticleService(IWeiXinArticleService weiXinArticleService) {
		this.weiXinArticleService = weiXinArticleService;
	}
	
	 /** 
     * @param args 
     */  
    public static void main(String[] args) {
        String url = "http://data.zz.baidu.com/urls?site=www.51maogou.com&token=RdYcCBZRzcdB9wUO";//网站的服务器连接  
        List<String> param =new ArrayList<String>();
        param.add("http://www.51maogou.com/index.htm");
//        for(int i=1;i<=20;i++){
        	String tempurl="http://www.51maogou.com/weixin/detail--358720547.htm";
        	param.add(tempurl);
//        }
        String json = post(url, param);//执行推送方法  
        System.out.println("结果是:"+json);
    	
    	//测试sitemap
    	
//    	WeixinSiteMapService.buildSiteMap("E:/weixinspider/sitemap.xml");
//    	WeixinSiteMapService.buildHaoSiteMap("E:/weixinspider/sitemap_detail.xml",30);
    }
	
}
