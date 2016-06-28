package org.guili.ecshop.controller.product;
import java.util.Calendar;
import java.util.Iterator;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.guili.ecshop.business.weixin.IWeiXinArticleService;
import org.guili.ecshop.business.weixin.IWeiXinService;
import org.guili.ecshop.business.weixin.IWeiXinSpiderService;
import org.guili.ecshop.business.weixin.WeiXinArticleSpider;
import org.guili.ecshop.business.weixin.WeiXinSpiderType;
import org.guili.ecshop.business.weixin.WeiXinSpiders;
import org.guili.ecshop.business.weixin.WeixinSiteMapService;
import org.guili.ecshop.business.weixin.bean.WeixinListVo;
import org.guili.ecshop.controller.common.BaseProfileController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 产品控制controller
 * @author guili
 *
 */
@Controller
public class WeiXinBackController extends BaseProfileController{
	
	@Resource(name="weixinService")
	private IWeiXinService weiXinService;
	@Resource(name="weiXinArticleService")
	private IWeiXinArticleService weiXinArticleService;
	
	@Resource(name="weixinSiteMapService")
	private WeixinSiteMapService weixinSiteMapService;
	private static final int TOTAL_NUM=1000;
	private static final int ONE_PAGE=25;
	private static final int Article_Relation_Num=10;
	private static final int TIME_OUT=5000;
	private static final String PUBLIC_KEY="Xiaozd12";
	
	//推送消息时间
	private static final int MIN_HOUR=8;
	private static final int MAX_HOUR=23;
	//最新的数量
	private static final int NEARLY_COUNT=10;
	
	
	@RequestMapping(value={ "/weixin/spider.htm"},method={RequestMethod. GET})
	public void batAddWenda(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,String key) throws Exception{
		if(StringUtils.isEmpty(key) || !key.equals(PUBLIC_KEY)){
			return;
		}
		
//		//微信
		IWeiXinSpiderService wentiSpiderService=WeiXinSpiders.getHandler(WeiXinSpiderType.WEIXIN);
		
		String urlpre="http://weixin.sogou.com/pcindex/pc/";
		String urlend=".html";
		
		//循环抓取数据
		while(true){
			for(int i=0;i<=19;i++){
//				for(int i=0;i<=2;i++){
					String urlmid="pc_"+i;
					for(int j=0;j<=2;j++){
						String innerurl="";
						if(j==0){
							innerurl=urlpre+urlmid+"/"+urlmid+urlend;
						}else{
							innerurl=urlpre+urlmid+"/"+j+urlend;
						}
						System.out.println(innerurl);
						if(StringUtils.isEmpty(innerurl)){
							return;
						}
						Thread.sleep(TIME_OUT);
						//抓取数据
						wentiSpiderService.batCrawlWeixin(innerurl, true);
					}
			}
			
			//推送数据到baidu
			pushDataToBaidu();
		}
		
	}
	
	//推送数据到baidu
	private void pushDataToBaidu(){
		//如果当前时间是8点到24点允许推送消息
		Calendar now=Calendar.getInstance();
		int hour=now.get(Calendar.HOUR_OF_DAY);
		if(hour>=MIN_HOUR && hour<=MAX_HOUR){
			//实时推送baidu收录
			try {
				weixinSiteMapService.onlinePush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
		/**
		 * 动态扩展 proxy ip
		 * @param request
		 * @param response
		 * @param modelMap
		 * @param hash
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value={ "/push.htm"},method={RequestMethod. GET})
		public String push(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap
				,@RequestParam(value = "key", required = false) String key) throws Exception{
			//参数异常
			if(key==null ||  !key.equals(PUBLIC_KEY)){
				return "redirect:/index.htm";
			}
			//实时推送百度
			weixinSiteMapService.onlinePush();
			return "weixin/weixin_proxy";
		}
		
		/**
		 * 动态扩展 proxy ip
		 * @param request
		 * @param response
		 * @param modelMap
		 * @param hash
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value={ "/proxy.htm"},method={RequestMethod.POST})
		public String proxy(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap
				,@RequestParam(value = "proxy_ips", required = false) String proxy_ips,@RequestParam(value = "is_add", required = false) boolean is_add) throws Exception{
			
			//参数异常
			if(proxy_ips==null ){
				return "redirect:/index.htm";
			}
			//增加proxy ip
			if(is_add){
				String[] proxyIps=proxy_ips.split(";");
				if(proxyIps==null || proxyIps.length==0){
					return "redirect:/index.htm";
				}
				//新增proxy ips
				for(String ipAndport:proxyIps){
					WeiXinArticleSpider.blockingQueue.add(ipAndport);
				}
				
			}else{
				//移除无用的proxy ip
				String[] proxyIps=proxy_ips.split(";");
				if(proxyIps==null || proxyIps.length==0){
					return "redirect:/index.htm";
				}
				//新增proxy ips
				for(String ipAndport:proxyIps){
					//blockingQueue.add(ipAndport);
					WeiXinArticleSpider.blockingQueue.remove(ipAndport);
				}
			}
			return "redirect:/index.htm";
		}
		
		/**
		 * 动态扩展 proxy ip
		 * @param request
		 * @param response
		 * @param modelMap
		 * @param hash
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value={ "/getProxy.htm"},method={RequestMethod. GET})
		public String proxy(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap
				,@RequestParam(value = "key", required = false) String key) throws Exception{
			
			//参数异常
			if(key==null ||  !key.equals(PUBLIC_KEY)){
				return "redirect:/index.htm";
			}
			//增加proxy ip
			StringBuffer proxyIps=new StringBuffer();
			Iterator<String> iterator= WeiXinArticleSpider.blockingQueue.iterator();
			while(iterator.hasNext()){
				String ip=iterator.next();
				proxyIps.append(ip+";");
			}
			request.setAttribute("proxyips",proxyIps.toString());
			request.setAttribute("proxyips_size", WeiXinArticleSpider.blockingQueue.size());
			
			return "weixin/weixin_proxy";
		}
		
		//测试微信公众号详细页
		@RequestMapping(value={ "/weixin/createSiteMap.htm"},method={RequestMethod. GET})
		public String createSiteMap(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap
				,String key) throws Exception{
			
			if(StringUtils.isEmpty(key) || !key.equals(PUBLIC_KEY)){
				return "weixin/weixinchuansong";
			}
			weixinSiteMapService.buildHaoSiteMap("E:/weixinspider/sitemap_detail2.xml",40);
			
			return "weixin/weixin_proxy";
		}
		
		//微信后台列表页
		@RequestMapping(value={ "/weixin/to_add_list.htm"},method={RequestMethod. GET})
		public String to_add_list(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,Long nextId,String key) throws Exception{
			//仅后台可访问
			if(StringUtils.isEmpty(key) || !key.equals(PUBLIC_KEY)){
				return "redirect:/errors/404.htm";
			}
			
//			WeixinListVo weixinListVo=weiXinArticleService.selectOnePageArticleInMongoByTag(null,nextId, null,null);
			WeixinListVo weixinListVo=weiXinArticleService.getGoodArticles(nextId, 0);
			 //如果返回数据为null指向404
	        if(weixinListVo==null){
	        	//测试页面
				return "redirect:/errors/404.htm";
	        }
	        request.setAttribute("weiXinArticleList", weixinListVo.getWeiXinArticleList());
	        
	        if(weixinListVo.getNextId()!=null){
	        	request.setAttribute("nextId", weixinListVo.getNextId());
	        }
			//测试页面
			return "weixin/weixinlist_back";
		}
		
		//微信后台列表页
		@RequestMapping(value={ "/weixin/add_one.htm"},method={RequestMethod. GET})
		public String add_one(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap
						,Integer titlehash,String key) throws Exception{
			//仅后台可访问
			if(StringUtils.isEmpty(key) || !key.equals(PUBLIC_KEY)){
				return "redirect:/errors/404.htm";
			}
			//更新文章状态
			weiXinArticleService.updateOneArticle(titlehash);
			//测试页面
			return "error/ok";
		}
		
}
