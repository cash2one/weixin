package org.guili.ecshop.business.weixin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.guili.ecshop.bean.common.WeixinConstans;
import org.guili.ecshop.business.weixin.bean.WeiXinArticle;
import org.guili.ecshop.business.weixin.bean.WeiXinHao;
import org.guili.ecshop.business.weixin.bean.WeiXinHaoVO;
import org.guili.ecshop.business.weixin.bean.WeiXinPackage;
import org.guili.ecshop.exception.RemoteServiceException;
import org.guili.ecshop.util.AbstractResponseHandler;
import org.guili.ecshop.util.FileTools;
import org.guili.ecshop.util.HttpComponent;
import org.guili.ecshop.util.SpiderRegex;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Lists;

/**
 * 对所有微信抓取的抽象
 * @author Administrator
 */
public abstract class WeixinSpiderParent implements IWeiXinSpiderService<WeiXinHaoVO> {

	private static Logger logger=Logger.getLogger(WeixinSpiderParent.class);
	protected IWeiXinService weiXinService;
	protected IWeiXinArticleService weiXinArticleService;
	protected  final String SPLIT="<br/>";
	protected static final Integer TIME_OUT=3000;
	public static final String USER_AGENT="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36";
//	private static final String MA_IMAG_URL=WeixinConstans.ma_path;
	private static final String dailiips_path=WeixinConstans.daili_path;
	
	//http请求组件封装
	public HttpComponent httpComponent;
	
	private static final int timeout=3500;
	
	public  static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>();
	
	{
		//查询所有ip
		String allips="";
		try {
			allips=FileTools.read(dailiips_path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//取出ips和端口
		String[] ipAndPorts=allips.split(";");
		if(ipAndPorts!=null || ipAndPorts.length>=0){
			//初始化blockingqueue
			for(String ipAndport:ipAndPorts){
				blockingQueue.add(ipAndport);
			}
		}
	}
	
	
	/**
	 * 批量抓取微信数据
	 * @param listurl
	 * isHttpClient 使用httpclient:true，
	 */
	@Override
	public void batCrawlWeixin(String listurl,Boolean isHttpClient){
		Date start=new Date(); 
		if(listurl.isEmpty()){
			return;
		}
		//微信公众号
		List<WeiXinHaoVO>  weiXinHaoList=new ArrayList<WeiXinHaoVO>();
		//代理服务器ip
		String hostAndPort =blockingQueue.poll();
		blockingQueue.add(hostAndPort);
		try {
			weiXinHaoList=this.getWeixinListInfo(listurl,isHttpClient,hostAndPort);
		} catch (URISyntaxException e) {
			logger.error("batCrawlPet URISyntaxException listurl is not a url:"+e.getStackTrace());
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(weiXinHaoList==null ||weiXinHaoList.isEmpty()){
			logger.info("batPetSaleInfo.isEmpty !!");
			return;
		}
		//批量查询所有微信文章，把文章和微信号一并保存,组合详细页返回的微信号
		
		List<WeiXinArticle> weiXinArticleList=Lists.newArrayList();
		//循环获取文章
		for(int i=0;i<weiXinHaoList.size();i++){
			//获取单个微信公众号文章信息,并返回hao_id
			
			try {
				String detailUrl=weiXinHaoList.get(i).getDetailUrl();
				WeiXinArticle weiXinArticle=this.getOneArticle(detailUrl,hostAndPort);
				Thread.sleep(TIME_OUT);
				
				//组合数据
				WeiXinHao innerWeiXinHao=weiXinHaoList.get(i).getWeiXinHao();
				weiXinArticle.setTag_id(innerWeiXinHao.getTag_id());
				weiXinArticle.setHao_name(innerWeiXinHao.getName());
				weiXinArticle.setHao_name_hash(innerWeiXinHao.getNamehash()+"");
				//文章预览
				weiXinArticle.setArticle_pre(weiXinHaoList.get(i).getArticle_pre());
				//文章打开次数
				weiXinArticle.setRead_times(weiXinHaoList.get(i).getReadTimes());
				
				//设置微信号code
				weiXinHaoList.get(i).getWeiXinHao().setWeixin_id(weiXinArticle.getWeixin_hao());
				weiXinHaoList.get(i).getWeiXinHao().setDescription(weiXinArticle.getDescription());
				
				//拿到公众号文章数据
				weiXinArticleList.add(weiXinArticle);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(weiXinArticleList==null || weiXinArticleList.isEmpty()){
			System.out.println("weiXinArticleList is empty!");
			return;
		}
		//批量写入文章
		weiXinArticleService.batAddWeiXinArticle(weiXinArticleList);
		
		//批量写入微信号
		weiXinService.batAddWeiXin(weiXinHaoList);
		System.out.println("spider cost total time:"+(new Date().getTime()-start.getTime()));
		
	}
	
	
	/**
	 * 解析文章
	 * @param listurl
	 * @param isHttpClient
	 * @return
	 */
	@Override
	public WeiXinPackage resolveArticles(String listurl,Boolean isHttpClient)throws URISyntaxException, IOException{
		return null;
	}
	
	@Override
	public List<WeiXinHaoVO> getWeixinListInfo(String listurl,Boolean isHttpClient,String hostAndPort)
			throws URISyntaxException, IOException {
		logger.info("LujinsuoSpider-->getPetListInfo:url:"+listurl);
		List<String> listurls=resolveUrls(listurl);
		List<WeiXinHaoVO> netLcProductList=new ArrayList<WeiXinHaoVO>();
		if(listurls==null || listurls.isEmpty()){
			return null;
		}
		netLcProductList=this.weixinDetailHttpMore(listurl,hostAndPort);
		
		return netLcProductList;
	}
	
	/**
	 * 抓取公众账号主体时使用
	 * @param detailUrl
	 * @return
	 */
	public List<WeiXinHaoVO> weixinDetailHttpMore(String detailUrl,String hostAndPort){
		String pagexml="";
		//如果要求httpclient处理
		HttpComponent httpComponent=getHttpComponent(hostAndPort);
		
		HttpGet httpGet = new HttpGet(detailUrl);
		//设置user-Agent
		httpGet.addHeader("User-Agent", USER_AGENT);
		httpGet.addHeader("Content-Type", "text/html;charset=UTF-8");
		httpGet.addHeader("Referer", "http://weixin.sogou.com/");
		try {
			 pagexml=httpComponent.execute(httpGet, pageHandler);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//容错
		if(StringUtils.isEmpty(pagexml)){
			return null;
		}
		
//		FileTools.write("E:/weixinspider/test_list2.txt", pagexml);
//		return null;
		return this.createWeixins(pagexml, detailUrl);
	}
	
	private HttpComponent getHttpComponent(String hostAndPort){
		//如果要求httpclient处理
		HttpComponent httpComponent=new HttpComponent();
		//设置代理服务器
		try {
			String[] hosts=hostAndPort.split(":");
			if(hosts!=null && hosts.length>0){
				String proxyhost=hosts[0];
				int proxyport=Integer.parseInt(hosts[1]);
				httpComponent.setProxy(proxyhost, proxyport);
			}
		} catch (Exception e) {
			logger.info("proxy error !");
		}
		
		return httpComponent;
	}
	
	public List<WeiXinHaoVO> createWeixins(String pagexml,String url){
		
//		try {
//			pagexml=FileTools.read("E:/weixinspider/test_list2.txt");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		if(pagexml.equals("")){
			return null;
		}
		Document doc = Jsoup.parse(pagexml);
		Elements nodes=doc.select("li");
		List<WeiXinHaoVO> weixinHaoList=new ArrayList<WeiXinHaoVO>();
		//
		for(Element node:nodes){
			try {
				Element onbox =node.select(".pos-wxrw").first();
//				String openid=onbox.select("a").first().attr("href");
				
				Elements element_ps=onbox.select("a").first().select("p");
				String head_img=element_ps.get(0).select("img").attr("src");
				String ma_img=onbox.select(".pos-box").first().select("img").get(0).attr("src");
				String name=element_ps.get(1).html().trim();
				Long tag_id=this.getTagId(url);
				int index=head_img.lastIndexOf("/");
				String touxiang=head_img.substring(index+1);
				String ma = ma_img.substring(index+1);
				//文章预览
				String article_pre=node.select(".wx-news-info2").select("a").get(1).text();
				//文章阅读数
				Integer readTimes=0;
				String yueduhtml = node.select(".s-p").first().html();
				String spanRegex = "阅读&nbsp;(.*?)&nbsp;";
				SpiderRegex spiderRegex=new SpiderRegex();
				String[] results=spiderRegex.htmlregex(yueduhtml, spanRegex, false);
				if(results!=null && results.length>0){
					if("100000+".equals(results[0])){
						readTimes=100000;
					}else{
						readTimes=Integer.parseInt(results[0]);
					}
					
				}
				
				String detailUrl=node.select(".wx-news-info2").select("h4").select("a").attr("href");
				//download二维码和头像图片
				try {
					//保存本地文件夹
					String filename=name.hashCode()+".jpg";
					this.makeImg(head_img, WeixinConstans.head_path,name.hashCode()+"", ".jpg");
					
					//复制到nginx所在机器,当前nginx和tomcat在同一台
//					FileTools.writeFileToRemote(WeixinConstans.remote_file_server_ip
//												, WeixinConstans.remote_file_server_username
//												, WeixinConstans.remote_file_server_pwd, filename, WeixinConstans.head_path, WeixinConstans.dest_file_path);
				} catch (Exception e) {
					logger.info("headimage is empty!");
				}
				WeiXinHao weiXinHao=new WeiXinHao();
				weiXinHao.setCreateTime(new Date());
				weiXinHao.setMa(ma);
				weiXinHao.setTouxiang(touxiang);
				weiXinHao.setName(name);
				weiXinHao.setTag_id(tag_id);
				weiXinHao.setNamehash(new Long(weiXinHao.getName().hashCode()));
//				weiXinHao.setOpenid(openid);
				weiXinHao.setStatus(1);
				
				WeiXinHaoVO weiXinHaoVO=new WeiXinHaoVO();
				weiXinHaoVO.setWeiXinHao(weiXinHao);
				weiXinHaoVO.setDetailUrl(detailUrl);
				weiXinHaoVO.setArticle_pre(article_pre);
				weiXinHaoVO.setReadTimes(readTimes);
				weixinHaoList.add(weiXinHaoVO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return weixinHaoList;
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

	// 生成图片函数  
    public  void makeImg(String imgUrl,String foldertoSave,String fileName,String endname) {
        try {  
            // 创建流  
            BufferedInputStream in = new BufferedInputStream(new URL(imgUrl)  
                    .openStream());  
            // 生成图片名  
//            int index = imgUrl.lastIndexOf("/"); 
            String sName = fileName+endname;
            // 存放地址  
            File img = new File(foldertoSave+sName);  
            // 生成图片  
            BufferedOutputStream out = new BufferedOutputStream(  
                    new FileOutputStream(img));  
            byte[] buf = new byte[2048];  
            int length = in.read(buf);  
            while (length != -1) {
                out.write(buf, 0, length);  
                length = in.read(buf);  
            }  
            in.close();  
            out.close();  
            logger.info("add sName success!");
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
    
    /**
     * 保存文本文件
     * @param filename
     * @param foldertoSave
     * @param filecontent
     */
    public void saveFile(String filename,String foldertoSave,String filecontent){
    	FileOutputStream fos = null;
    	OutputStreamWriter osw=null;
    	if(!foldertoSave.endsWith("/")){
    		foldertoSave=foldertoSave+"/";
    	}
    	//创建文件夹
    	File folder=new File(foldertoSave);
    	if(!folder.exists() || !folder.isDirectory()){
    		folder.mkdir();
    	}
    	
		try {
			String savefile=foldertoSave+filename;
			fos = new FileOutputStream(savefile);
			osw = new OutputStreamWriter(fos,"UTF-8");
			osw.write(filecontent);
			osw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(osw!=null){
					osw.close();
				}
				if(fos!=null){
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
    }
	
    
    
    /**
	 * 根据url，设置tagid
	 * @param url
	 * @return
	 */
	private Long getTagId(String url){
		//热门
		if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_0")){
			return 1L;
		}
		//潮人帮
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_10")){
				return 11L;
			}
			//辣妈帮
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_11")){
				return 12L;
			}
			//点赞党
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_12")){
				return 13L;
			}
			//旅行家
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_13")){
				return 14L;
			}
			//职场人
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_14")){
				return 15L;
			}
			//美食家
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_15")){
				return 16L;
			}
			//古今通
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_16")){
				return 17L;
			}
			//学霸族
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_17")){
				return 18L;
			}
			//星座控
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_18")){
				return 19L;
			}
			//体育迷
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_19")){
				return 20L;
			}
			//推荐
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_1")){
				return 2L;
			}
			//段子手
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_2")){
				return 8L;
			}
			//养生堂
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_3")){
				return 3L;
			}
			//私房话
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_4")){
				return 4L;
			}
			//八卦精
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_5")){
				return 5L;
			}
			//爱生活
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_6")){
				return 6L;
			}
			//财经迷
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_7")){
				return 7L;
			}
			//汽车迷
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_8")){
				return 9L;
			}
			//科技咖
			if(url.contains("http://weixin.sogou.com/pcindex/pc/pc_9")){
				return 10L;
			}
		
		//热门
		if(url.equals("http://weixin.sogou.com/")){
			return 1L;
		}
		return 1L;
	}
	/*****************getter and setter***************/
	public IWeiXinService getWeiXinService() {
		return weiXinService;
	}

	public void setWeiXinService(IWeiXinService weiXinService) {
		this.weiXinService = weiXinService;
	}

	public void setHttpComponent(HttpComponent httpComponent) {
		this.httpComponent = httpComponent;
	}

	public IWeiXinArticleService getWeiXinArticleService() {
		return weiXinArticleService;
	}

	public void setWeiXinArticleService(IWeiXinArticleService weiXinArticleService) {
		this.weiXinArticleService = weiXinArticleService;
	}
	
	/**
	 * 抓取单个公众号文章
	 * @param detailUrl
	 * @return
	 */
	@Override
	public  WeiXinArticle getOneArticle(String detailUrl,String hostAndPort){
		String pagexml="";
		//如果要求httpclient处理
		HttpComponent httpComponent=getHttpComponent(hostAndPort);
		
		HttpGet httpGet = new HttpGet(detailUrl);
		//设置user-Agent
		httpGet.addHeader("User-Agent", USER_AGENT);
		httpGet.addHeader("Content-Type", "text/html;charset=UTF-8"); 
		httpGet.addHeader("Referer", "http://weixin.sogou.com/");
		try {
			 pagexml=httpComponent.execute(httpGet, pageHandler);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//容错
		if(StringUtils.isEmpty(pagexml)){
			return null;
		}
		
//		FileTools.write("E:/weixinspider/test_detail2.txt", pagexml);
		//解析文件并返回所需数据
//		return null;
		return createArticle(pagexml);
	}
	
	public WeiXinArticle createArticle(String pagexml){
		if(StringUtils.isEmpty(pagexml)){
			return null;
		}
//		try {
//			pagexml=FileTools.read("E:/weixinspider/test_detail1.txt");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		Document doc = Jsoup.parse(pagexml);
		Element qrcode =doc.select("#js_profile_qrcode").first();
		Elements haoInfos=qrcode.select(".profile_meta_value");
		
		String hao_name =qrcode.select(".profile_nickname").first().html();
		String hao_code =haoInfos.first().text();
		String hao_desc=haoInfos.get(1).text();
		String article_title=doc.select(".rich_media_title").get(0).html();
		Elements descs=doc.select("#js_content").first().select("p");
		String article_desc="";
		//文档描述
		if(descs.size()>3){
			article_desc = descs.get(2).text()+" "+descs.get(3).text();
		}
		//设置默认haocode
		if(StringUtils.isEmpty(hao_code)){
			hao_code=hao_name.hashCode()+"";
		}
		
		//移除富媒体
		doc.select(".rich_media_area_extra").remove();
		//抓取正文
		StringBuffer content=new StringBuffer("<div class=\"rich_media\" id=\"js_article\">");
		content.append(doc.select("#js_article").first().html());
		content.append("</div>");
		
		WeiXinArticle weiXinArticle=new WeiXinArticle();
		weiXinArticle.setCreateTime(new Date());
		weiXinArticle.setDescription(article_desc);
		weiXinArticle.setHao_desc(hao_desc);
		weiXinArticle.setHao_name(hao_name);
		weiXinArticle.setWeixin_hao(hao_code);
		weiXinArticle.setContent(content.toString());
		weiXinArticle.setTitle(article_title);
		weiXinArticle.setTitlehash(article_title.hashCode());
		weiXinArticle.setStatus(1);
		
		return weiXinArticle;
	}
	
}
